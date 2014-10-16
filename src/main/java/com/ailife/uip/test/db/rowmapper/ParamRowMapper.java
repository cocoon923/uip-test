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
		param.setSeq(rs.getLong("seq"));
		param.setParamName(rs.getString("param_name"));
		param.setParamCode(rs.getString("param_code"));
		param.setParamValue(rs.getString("param_value"));
		param.setIsNull(rs.getString("is_null"));
		param.setSort(rs.getInt("sort"));
		param.setParamType(rs.getString("param_type"));
		param.setRemark(rs.getString("remark"));
		param.setParentSeq(rs.getLong("parent_seq"));
		return param;
	}
}
