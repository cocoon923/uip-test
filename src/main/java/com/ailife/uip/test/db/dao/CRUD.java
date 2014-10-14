package com.ailife.uip.test.db.dao;

import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public interface CRUD {

	public <T> void insert(T t);

	public <T> void batchInsert(Class<T> clz, List<T> list);

}
