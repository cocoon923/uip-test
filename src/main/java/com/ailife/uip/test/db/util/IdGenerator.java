package com.ailife.uip.test.db.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenmm on 10/16/2014.
 */
@Repository
public class IdGenerator {

	private static final String UIP_SEQ = "UIP_SEQ";
	private static final String NEXTVAL = "{?= call nextval(?)}";
	private static final String CURRVAL = "{?= call currval(?)}";
	private static final String RESULT = "result";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public long getNewId() {
		List<SqlParameter> sqlParameterList = new ArrayList<SqlParameter>();
		sqlParameterList.add(new SqlOutParameter(RESULT, Types.BIGINT));
		sqlParameterList.add(new SqlParameter(Types.VARCHAR));
		Map<String, Object> callMap = this.namedParameterJdbcTemplate.getJdbcOperations().call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement callableStatement = con.prepareCall(NEXTVAL);
				callableStatement.registerOutParameter(1, Types.BIGINT);
				callableStatement.setString(2, UIP_SEQ);
				return callableStatement;
			}
		}, sqlParameterList);
		return (Long) callMap.get(RESULT);
	}

	public long getMaxId() {
		List<SqlParameter> sqlParameterList = new ArrayList<SqlParameter>();
		sqlParameterList.add(new SqlOutParameter(RESULT, Types.BIGINT));
		sqlParameterList.add(new SqlParameter(Types.VARCHAR));
		Map<String, Object> callMap = this.namedParameterJdbcTemplate.getJdbcOperations().call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement callableStatement = con.prepareCall(CURRVAL);
				callableStatement.registerOutParameter(1, Types.BIGINT);
				callableStatement.setString(2, UIP_SEQ);
				return callableStatement;
			}
		}, sqlParameterList);
		return (Long) callMap.get(RESULT);
	}

}
