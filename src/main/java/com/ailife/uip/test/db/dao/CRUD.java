package com.ailife.uip.test.db.dao;

import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public interface CRUD {

	public <T> void save(T t);

	public <T> void batchSave(Class<T> clz, List<T> list);

	public <T> void delete(T t);

}
