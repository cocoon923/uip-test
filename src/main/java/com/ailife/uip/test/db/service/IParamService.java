package com.ailife.uip.test.db.service;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.file.entity.Param;

import java.util.List;

/**
 * @author chenmm
 */
public interface IParamService {

	public IParamDAO getIParamDAO();

	public boolean isPublicParamInitial(String paramType);

	public void batchSave(List<Param> paramList);

}
