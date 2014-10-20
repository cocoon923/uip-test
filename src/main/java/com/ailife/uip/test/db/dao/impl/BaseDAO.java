package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.util.Symbol;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

	public <T> void save(T t) {
		this.getNamedParameterJdbcTemplate().update(getInsertSQL(t.getClass()), new BeanPropertySqlParameterSource(t));
	}

	public <T> void batchSave(Class<T> clz, List<T> list) {
		if (list == null || list.size() <= 0) {
			return;
		}
		int size = list.size();
		SqlParameterSource[] sqlParameterSources = new SqlParameterSource[size];
		for (int i = 0; i < size; i++) {
			sqlParameterSources[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		this.getNamedParameterJdbcTemplate().batchUpdate(getInsertSQL(clz), sqlParameterSources);
	}

	public <T> void delete(T t) {

	}

	private String getInsertSQL(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		StringBuffer sbField = new StringBuffer();
		StringBuffer sbParam = new StringBuffer();
		sbField.append("INSERT INTO").append(Symbol.BLANK).append("uip" + Symbol.UNDERLINE + clz.getSimpleName().toLowerCase()).append(Symbol.BLANK).append(Symbol.PARENLEFT);
		sbParam.append(Symbol.PARENRIGHT).append(Symbol.BLANK).append("VALUES").append(Symbol.BLANK).append(Symbol.PARENLEFT);
		for (Field field : fields) {
			if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
				continue;
			}
			String fieldName = field.getName();
			String underscoreFieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
			sbField.append(underscoreFieldName).append(Symbol.COMMA);
			sbParam.append(Symbol.COLON).append(fieldName).append(Symbol.COMMA);
		}
		return sbField.substring(0, sbField.length() - 1) + sbParam.substring(0, sbParam.length() - 1) + Symbol.PARENRIGHT;
	}


}
