package com.ailife.uip.test.db.service.impl;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.db.dao.IStaticDataDAO;
import com.ailife.uip.test.db.service.IParamService;
import com.ailife.uip.test.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenmm
 */
@Service
public class ParamServiceImpl implements IParamService {

	@Autowired
	private IParamDAO paramDAO;

	@Autowired
	private IStaticDataDAO staticDataDAO;

	@Override
	public IParamDAO getIParamDAO() {
		return paramDAO;
	}

	@Override
	public boolean isPublicParamInitial(String paramType) {
		boolean isInitial = false;
		if (StringUtils.isNotNullorEmpty(paramType)) {
			isInitial = paramDAO.findParamCountByParentSeq(Long.parseLong(paramType)) > 0;
		}
		return isInitial;
	}

}
