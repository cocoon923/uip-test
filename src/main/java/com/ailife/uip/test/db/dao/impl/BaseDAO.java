package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.entity.Inter;
import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.rowmapper.UIPRowMapper;
import com.ailife.uip.test.util.LogUtil;
import com.ailife.uip.test.util.ReflectionUtil;
import com.ailife.uip.test.util.StringUtils;
import com.ailife.uip.test.util.Symbol;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by chenmm on 10/11/2014.
 */
public abstract class BaseDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public abstract RowMapper getRowMapper();

	protected <T> void save(Class<T> clz, T t) {
		String insertSQL = getInsertSQL(clz);
		LogUtil.debug(this.getClass(), insertSQL);
		this.getNamedParameterJdbcTemplate().update(insertSQL, new BeanPropertySqlParameterSource(t));
	}

	protected <T> void batchSave(Class<T> clz, List<T> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		int size = list.size();
		SqlParameterSource[] sqlParameterSources = new SqlParameterSource[size];
		for (int i = 0; i < size; i++) {
			sqlParameterSources[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		String insertSQL = getInsertSQL(clz);
		LogUtil.debug(this.getClass(), insertSQL);
		this.getNamedParameterJdbcTemplate().batchUpdate(insertSQL, sqlParameterSources);
	}

	protected <T> T queryById(Class<T> clz, long seq) {
		try {
			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			parameterSource.addValue("seq", seq);
			RowMapper<T> rowMapper = new UIPRowMapper<T>(clz);
			String queryByIdSQL = getQueryByIdSQL(clz);
			LogUtil.debug(this.getClass(), queryByIdSQL);
			List<T> list = this.getNamedParameterJdbcTemplate().query(queryByIdSQL, parameterSource, rowMapper);
			if (list != null && list.size() == 1) {
				return list.get(0);
			}
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e, "Query by id error!");
		}
		return null;
	}

	protected <T> void delete(Class<T> clz, T t) {

	}

	private String getQueryByIdSQL(Class<?> clz) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM").append(Symbol.BLANK).append("uip" + Symbol.UNDERLINE);
		sb.append(StringUtils.caseFormatTransfer(CaseFormat.UPPER_CAMEL, CaseFormat.LOWER_UNDERSCORE, clz.getSimpleName()));
		sb.append(Symbol.BLANK).append("WHERE seq=:seq");
		return sb.toString();
	}

	private static String getInsertSQL(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		StringBuilder sbField = new StringBuilder();
		StringBuilder sbParam = new StringBuilder();
		sbField.append("INSERT INTO").append(Symbol.BLANK).append("uip" + Symbol.UNDERLINE + clz.getSimpleName().toLowerCase()).append(Symbol.BLANK).append(Symbol.PARENLEFT);
		sbParam.append(Symbol.PARENRIGHT).append(Symbol.BLANK).append("VALUES").append(Symbol.BLANK).append(Symbol.PARENLEFT);
		for (Field field : fields) {
			if (ReflectionUtil.isBeanField(field) && ReflectionUtil.isPrimitiveOrString(field)) {
				String fieldName = field.getName();
				String underscoreFieldName = StringUtils.caseFormatTransfer(CaseFormat.LOWER_CAMEL, CaseFormat.LOWER_UNDERSCORE, fieldName);
				sbField.append(underscoreFieldName).append(Symbol.COMMA);
				sbParam.append(Symbol.COLON).append(fieldName).append(Symbol.COMMA);
			}
		}
		return sbField.substring(0, sbField.length() - 1) + sbParam.substring(0, sbParam.length() - 1) + Symbol.PARENRIGHT;
	}

	public static void main(String[] args) {
		System.out.println(BaseDAO.getInsertSQL(Param.class));
	}

}
