package com.ailife.uip.test.db.dao;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by chenmm on 10/11/2014.
 */
public abstract class BaseDAO {

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplatel() {
		return namedParameterJdbcTemplate;
	}

	public <T> void insert(T t) {
		this.getNamedParameterJdbcTemplatel().update(getInsertSQL(t.getClass()), new BeanPropertySqlParameterSource(t));
	}

	public <T> void batchInsert(Class<T> clz, List<T> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		int size = list.size();
		SqlParameterSource[] sqlParameterSources = new SqlParameterSource[size];
		for (int i = 0; i < size; i++) {
			sqlParameterSources[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		this.getNamedParameterJdbcTemplatel().batchUpdate(getInsertSQL(clz), sqlParameterSources);
	}

	private String getInsertSQL(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		StringBuffer sbField = new StringBuffer();
		StringBuffer sbParam = new StringBuffer();
		sbField.append("INSERT INTO ").append("UIP_" + clz.getSimpleName().toUpperCase()).append(" (");
		sbParam.append(") VALUES (");
		for (Field field : fields) {
			if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
				continue;
			}
			String fieldName = field.getName();
			String underscoreFieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, fieldName);
			sbField.append(underscoreFieldName).append(",");
			sbParam.append(":").append(fieldName).append(",");
		}
		return sbField.substring(0, sbField.length() - 1) + sbParam.substring(0, sbParam.length() - 1) + ")";
	}


}
