package com.ailife.uip.test.db.dao;

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

	private static final String SELECT_ALL = "SELECT * FROM UIP_PARAM";
	private static final String SELECT_BY_BATCH_SEQS = "SELECT * FROM UIP_PARAM WHERE SEQ IN (:seqs)";

	@Autowired
	private ParamRowMapper paramRowMapper;

	@Override
	public List<Param> selectAll() {
		return this.getNamedParameterJdbcTemplatel().query(SELECT_ALL, paramRowMapper);
	}

	@Override
	public List<Param> selectByBatchSeqs(final String[] seqs) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seqs", Arrays.asList(seqs));
		return this.getNamedParameterJdbcTemplatel().query(SELECT_BY_BATCH_SEQS, parameterSource, paramRowMapper);
	}
}
