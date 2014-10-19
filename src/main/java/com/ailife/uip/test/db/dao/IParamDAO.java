package com.ailife.uip.test.db.dao;

import com.ailife.uip.test.file.entity.Param;

import java.util.List;

/**
 * Created by chenmm on 10/11/2014.
 */
public interface IParamDAO extends CRUD {

	public List<Param> selectByBatchSeqs(String[] seqs);

	public List<Param> findParamByParentSeq(long parentSeq);

	public int findParamCountByParentSeq(Long parentSeq);

}
