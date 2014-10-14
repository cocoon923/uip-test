package com.ailife.uip.test.db.rowmapper;

import com.ailife.uip.test.file.entity.Param;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author chenmm
 */
@Repository
public class ParamRowMapper implements RowMapper<Param> {

	@Override
	public Param mapRow(ResultSet rs, int rowNum) throws SQLException {
		Param param = new Param();
		param.setSeq(rs.getLong("SEQ"));
		param.setParamName(rs.getString("PARAM_NAME"));
		param.setParamCode(rs.getString("PARAM_CODE"));
		param.setParamValue(rs.getString("PARAM_VALUE"));
		param.setIsNull(rs.getString("IS_NULL"));
		param.setSort(rs.getInt("SORT"));
		param.setParamType(rs.getString("PARAM_TYPE"));
		param.setRemark(rs.getString("REMARK"));
		return param;
	}
}
