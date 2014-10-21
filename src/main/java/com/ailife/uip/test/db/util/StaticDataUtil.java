package com.ailife.uip.test.db.util;

import com.ailife.uip.test.db.dao.IStaticDataDAO;
import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.service.IParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author chenmm
 */
@Repository
public class StaticDataUtil {

	private static StaticDataConfiguration staticDataConfiguration;

	public static Map<String, String> getStaticData(String dataType) {
		return staticDataConfiguration.getStaticDataDAO().getStaticData(dataType);
	}

	public static String getStaticDataValue(String dataType, String dataName) {
		return staticDataConfiguration.getStaticDataDAO().getStaticDataValue(dataType, dataName);
	}

	public static Param getReqRootParam() {
		return staticDataConfiguration.getParamService().getReqRootParam();
	}

	public static Param getRespRootParam() {
		return staticDataConfiguration.getParamService().getRespRootParam();
	}

	@Repository
	private static class StaticDataConfiguration {

		@Autowired
		private IStaticDataDAO staticDataDAO;

		@Autowired
		private IParamService paramService;

		@PostConstruct
		public void initial() {
			StaticDataUtil.staticDataConfiguration = this;
		}

		public IStaticDataDAO getStaticDataDAO() {
			return staticDataDAO;
		}

		public IParamService getParamService() {
			return paramService;
		}
	}

}
