package com.ailife.uip.test.db.dao.impl;

import com.ailife.uip.test.db.dao.IItemRelatDAO;
import com.ailife.uip.test.db.entity.ItemRelat;
import com.ailife.uip.test.db.rowmapper.UIPRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenmm6 on 2014/10/30.
 */
@Repository
public class ItemRelatDAOImpl extends BaseDAO implements IItemRelatDAO {

	@Override
	public RowMapper getRowMapper() {
		return new UIPRowMapper<ItemRelat>(ItemRelat.class);
	}

	@Override
	public void save(ItemRelat itemRelat) {
		super.save(ItemRelat.class, itemRelat);
	}

	@Override
	public void batchSave(List<ItemRelat> list) {
		super.batchSave(ItemRelat.class, list);
	}

	@Override
	public void delete(ItemRelat itemRelat) {
		super.delete(ItemRelat.class, itemRelat);
	}

	@Override
	public ItemRelat queryById(long seq) {
		return super.queryById(ItemRelat.class, seq);
	}
}
