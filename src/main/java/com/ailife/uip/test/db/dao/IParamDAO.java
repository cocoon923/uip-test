package com.ailife.uip.test.db.dao;

import com.ailife.uip.test.db.entity.Param;

import java.util.List;

/**
 * Created by chenmm on 10/11/2014.
 */
public interface IParamDAO extends CRUD {

	public List<Param> queryParamByParentSeq(long parentSeq);

	public int queryParamCountByParentSeq(Long parentSeq);

	public List<Param> queryReqParamByParentSeq(long parentSeq);

	public List<Param> queryRespParamByParentSeq(long parentSeq);

}
