package com.ailife.uip.test.db.service;

import com.ailife.uip.test.db.dao.IParamDAO;

/**
 * @author chenmm
 */
public interface IParamService {

	public IParamDAO getIParamDAO();

	public boolean isPublicParamInitial(String paramType);

}
