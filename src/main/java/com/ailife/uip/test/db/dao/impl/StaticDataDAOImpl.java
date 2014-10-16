package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.dao.IStaticDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenmm on 10/16/2014.
 */
@Repository
public class StaticDataDAOImpl implements IStaticDataDAO {

	private static final String GET_STATIC_DATA = "SELECT data_name, data_value FROM uip_static_data WHERE data_type = :dataType AND status = 'U'";
	private static final String GET_STATIC_DATA_VALUE = "SELECT data_value FROM uip_static_data WHERE data_type = :dataType AND data_name = :dataName AND status = 'U'";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Map<String, String> getStaticData(String dataType) {
		Map<String, String> result = new HashMap<String, String>();
		List<Map<String, Object>> datas = this.namedParameterJdbcTemplate.queryForList(GET_STATIC_DATA, Collections.singletonMap("dataType", dataType));
		for (Map<String, Object> data : datas) {
			result.put((String) data.get("data_name"), (String) data.get("data_value"));
		}
		return result;
	}

	@Override
	public String getStaticDataValue(String dataType, String dataName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("dataType", dataType);
		params.put("dataName", dataName);
		return this.namedParameterJdbcTemplate.queryForObject(GET_STATIC_DATA_VALUE, params, String.class);
	}

}
