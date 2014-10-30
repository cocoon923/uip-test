package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.dao.IInterDAO;
import com.ailife.uip.test.db.entity.Inter;
import com.ailife.uip.test.db.rowmapper.UIPRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenmm on 10/27/2014.
 */
@Repository
public class InterDAOImpl extends BaseDAO implements IInterDAO {


	@Override
	public RowMapper getRowMapper() {
		return new UIPRowMapper<Inter>(Inter.class);
	}

	@Override
	public void save(Inter inter) {
		super.save(Inter.class, inter);
	}

	@Override
	public void batchSave(List<Inter> list) {
		super.batchSave(Inter.class, list);
	}

	@Override
	public void delete(Inter inter) {
		super.delete(Inter.class, inter);
	}

	@Override
	public Inter queryById(long seq) {
		return super.queryById(Inter.class, seq);
	}
}
