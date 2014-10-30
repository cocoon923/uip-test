package com.ailife.uip.test.db.rowmapper;

import com.ailife.uip.test.util.LogUtil;
import com.ailife.uip.test.util.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

/**
 * Created by chenmm6 on 2014/10/29.
 */
public class UIPRowMapper<T> implements RowMapper<T> {

	private Class<T> clz;

	public UIPRowMapper(Class<T> clz) {
		this.clz = clz;
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			T t = this.clz.newInstance();
			Field[] fields = this.clz.getDeclaredFields();
			for (Field field : fields) {
				String methodName = getMethodName(field.getName());
				String setMethodName = (new StringBuilder()).append("set").append(methodName).toString();
				String fieldTypeName = field.getType().getName();
				if (String.class.getName().equals(fieldTypeName)) {
					Method method = clz.getMethod(setMethodName, String.class);
					String value = rs.getString(getColumnLabel(methodName));
					method.invoke(t, value);
				} else if (Integer.class.getName().equals(fieldTypeName)) {
					Method method = clz.getMethod(setMethodName, Integer.class);
					Integer value = rs.getInt(getColumnLabel(methodName));
					method.invoke(t, value);
				} else if (Long.class.getName().equals(fieldTypeName)) {
					Method method = clz.getMethod(setMethodName, Long.class);
					Long value = rs.getLong(getColumnLabel(methodName));
					method.invoke(t, value);
				}
			}
			return t;
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e, "Row mapper error");
		}
		return null;
	}

	private String getColumnLabel(String methodName) {
		return StringUtils.caseFormatTransfer(UPPER_CAMEL, LOWER_UNDERSCORE, methodName);
	}

	private String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

}
