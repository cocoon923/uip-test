package com.ailife.uip.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by chenmm on 10/13/2014.
 */
public class DataInitialEvent<T> extends ApplicationEvent {

	public DataInitialEvent(T source) {
		super(source);
	}

	@Override
	public T getSource() {
		return (T) super.getSource();
	}

}
