package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.rowmapper.RowMapperHelper;
import com.ailife.uip.test.util.LogUtil;
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

	public <T> T queryById(Class<T> clz, long seq) {
		try {
			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			parameterSource.addValue("seq", seq);
			String rowMapperName = clz.getSimpleName() + RowMapper.class.getSimpleName();
			Class<?> tClass = Class.forName(RowMapperHelper.getPackage() + Symbol.PERIOD+ rowMapperName);
			RowMapper<T> rowMapper = (RowMapper<T>) tClass.newInstance();
			List<T> list = this.getNamedParameterJdbcTemplate().query(getQueryByIdSQL(clz), parameterSource, rowMapper);
			if (list != null && list.size() == 1) {
				return list.get(0);
			}
		} catch (Exception e) {
			LogUtil.error(this.getClass(), "Query by id error!", e);
		}
		return null;
	}

	public <T> void delete(T t) {

	}

	private String getQueryByIdSQL(Class<?> clz) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM").append(Symbol.BLANK).append("uip" + Symbol.UNDERLINE);
		sb.append(StringUtils.caseFormatTransfer(CaseFormat.UPPER_CAMEL, CaseFormat.LOWER_UNDERSCORE, clz.getSimpleName()));
		sb.append(Symbol.BLANK).append("WHERE seq=:seq");
		return sb.toString();
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
			String underscoreFieldName = StringUtils.caseFormatTransfer(CaseFormat.LOWER_CAMEL, CaseFormat.LOWER_UNDERSCORE, fieldName);
			sbField.append(underscoreFieldName).append(Symbol.COMMA);
			sbParam.append(Symbol.COLON).append(fieldName).append(Symbol.COMMA);
		}
		return sbField.substring(0, sbField.length() - 1) + sbParam.substring(0, sbParam.length() - 1) + Symbol.PARENRIGHT;
	}

}
