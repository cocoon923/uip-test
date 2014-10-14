package com.ailife.uip.test.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by chenmm on 9/25/2014.
 */
public class DocInitialEvent<T> extends ApplicationEvent {

	public DocInitialEvent(T source) {
		super(source);
	}

	@Override
	public T getSource() {
		return (T) super.getSource();
	}
}
