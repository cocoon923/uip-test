package com.ailife.uip.test.db.service;

import com.ailife.uip.test.db.entity.Param;

import java.util.List;

/**
 * @author chenmm
 */
public interface IParamService {

	public boolean isPublicParamInitial(String paramType);

	public void batchSave(List<Param> paramList);

	public Param queryBySeq(long seq);

	public List<Param> queryByParentSeq(long parentSeq);

	public Param getReqRootParam();

	public Param getRespRootParam();

}
