package com.ailife.uip.test.db.util;

import com.ailife.uip.test.db.dao.IStaticDataDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author chenmm
 */
public class StaticDataUtil {

	@Autowired
	private static IStaticDataDAO staticDataDAO;

	public static Map<String, String> getStaticData(String dataType) {
		return staticDataDAO.getStaticData(dataType);
	}

	public static String getStaticDataValue(String dataType, String dataName) {
		return staticDataDAO.getStaticDataValue(dataType, dataName);
	}

	public enum DATATYPE{
		PUBLIC_PARAM
	}

}
