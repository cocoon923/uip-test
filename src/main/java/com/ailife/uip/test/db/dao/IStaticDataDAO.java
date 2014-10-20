package com.ailife.uip.test.db.dao;

import java.util.Map;

/**
 * @author chenmm
 */
public interface IStaticDataDAO {

	public Map<String, String> getStaticData(String dataType);

	public String getStaticDataValue(String dataType, String dataName);

}
