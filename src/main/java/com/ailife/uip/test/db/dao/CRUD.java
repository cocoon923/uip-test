package com.ailife.uip.test.db.dao;

import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public interface CRUD<T> {

	public void save(T t);

	public void batchSave(List<T> list);

	public void delete(T t);

	public T queryById(long seq);

}
