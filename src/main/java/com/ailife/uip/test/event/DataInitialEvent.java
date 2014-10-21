package com.ailife.uip.test.event;

import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public class DataInitialEvent<T> extends ApplicationEvent {

	private Class<T> clz;

	public DataInitialEvent(List<T> source, Class<T> clz) {
		super(source);
		this.clz = clz;
	}

	@Override
	public List<T> getSource() {
		return (List<T>) super.getSource();
	}

	public Class<T> getClz() {
		return clz;
	}
}
