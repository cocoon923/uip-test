package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.rowmapper.UIPRowMapper;
import com.ailife.uip.test.db.util.StaticDataUtil;
import com.ailife.uip.test.util.DATATYPE;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenmm on 10/11/2014.
 */
@Repository
public class ParamDAOImpl extends BaseDAO implements IParamDAO {

	private static final String QUERY_BY_PARENTSEQ = "SELECT * FROM uip_param WHERE parent_seq = :parentSeq";
	private static final String QUERY_COUNT_BY_PARENTSEQ = "SELECT COUNT(*) FROM uip_param WHERE parent_seq = :parentSeq";
	private static final String QUERY_BY_PARENTSEQ_PARAMTYPE = "SELECT * FROM uip_param WHERE parent_seq = :parentSeq AND param_type = :paramType";

	@Override
	public UIPRowMapper<Param> getRowMapper() {
		return new UIPRowMapper<Param>(Param.class);
	}

	@Override
	public List<Param> queryParamByParentSeq(long parentSeq) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("parentSeq", parentSeq);
		return this.getNamedParameterJdbcTemplate().query(QUERY_BY_PARENTSEQ, parameterSource, getRowMapper());
	}

	@Override
	public int queryParamCountByParentSeq(Long parentSeq) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("parentSeq", parentSeq);
		return this.getNamedParameterJdbcTemplate().queryForObject(QUERY_COUNT_BY_PARENTSEQ, parameterSource, Integer.class);
	}

	@Override
	public List<Param> queryReqParamByParentSeq(long parentSeq) {
		String paramType = StaticDataUtil.getStaticDataValue(DATATYPE.PARAM_TYPE.toString(), "REQUEST");
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("parentSeq", parentSeq);
		parameterSource.addValue("paramType", paramType);
		return this.getNamedParameterJdbcTemplate().query(QUERY_BY_PARENTSEQ_PARAMTYPE, parameterSource, getRowMapper());
	}

	@Override
	public List<Param> queryRespParamByParentSeq(long parentSeq) {
		String paramType = StaticDataUtil.getStaticDataValue(DATATYPE.PARAM_TYPE.toString(), "RESPONSE");
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("parentSeq", parentSeq);
		parameterSource.addValue("paramType", paramType);
		return this.getNamedParameterJdbcTemplate().query(QUERY_BY_PARENTSEQ_PARAMTYPE, parameterSource, getRowMapper());
	}

}
