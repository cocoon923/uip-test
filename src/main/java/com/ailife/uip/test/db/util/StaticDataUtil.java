package com.ailife.uip.test.db.util;

import com.ailife.uip.test.db.dao.IStaticDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author chenmm
 */
@Repository
public class StaticDataUtil {

	@Autowired
	public void init(IStaticDataDAO staticDataDAO) {
		StaticDataUtil.staticDataDAO = staticDataDAO;
	}

	private static IStaticDataDAO staticDataDAO;

	public static Map<String, String> getStaticData(String dataType) {
		return staticDataDAO.getStaticData(dataType);
	}

	public static String getStaticDataValue(String dataType, String dataName) {
		return staticDataDAO.getStaticDataValue(dataType, dataName);
	}

	public enum DATATYPE {
		PUBLIC_PARAM
	}

}
