package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.db.rowmapper.ParamRowMapper;
import com.ailife.uip.test.file.entity.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chenmm on 10/11/2014.
 */
@Repository
public class ParamDAOImpl extends BaseDAO implements IParamDAO {

	private static final String SELECT_ALL = "SELECT * FROM uip_param";
	private static final String SELECT_BY_BATCH_SEQS = "SELECT * FROM uip_param WHERE seq IN (:seqs)";
	private static final String QUERY_BY_PARENTSEQ = "SELECT * FROM uip_param WHERE parent_seq = :parentSeq";
	private static final String QUERY_COUNT_BY_PARENTSEQ = "SELECT COUNT(*) FROM uip_param WHERE parent_seq = :parentSeq";

	@Autowired
	private ParamRowMapper paramRowMapper;

	@Override
	public List<Param> selectByBatchSeqs(final String[] seqs) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seqs", Arrays.asList(seqs));
		return this.getNamedParameterJdbcTemplate().query(SELECT_BY_BATCH_SEQS, parameterSource, paramRowMapper);
	}

	@Override
	public List<Param> findParamByParentSeq(long parentSeq) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("parentSeq", parentSeq);
		return this.getNamedParameterJdbcTemplate().query(QUERY_BY_PARENTSEQ, parameterSource, paramRowMapper);
	}

	@Override
	public int findParamCountByParentSeq(Long parentSeq) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("parentSeq", parentSeq);
		return this.getNamedParameterJdbcTemplate().queryForObject(QUERY_COUNT_BY_PARENTSEQ, parameterSource, Integer.class);
	}
}
