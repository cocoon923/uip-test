package com.ailife.uip.test.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by chenmm on 9/25/2014.
 */
@Component
public class DocInitialEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void monitor(File file) {
		DocInitialEvent event = new DocInitialEvent<File>(file);
		this.applicationEventPublisher.publishEvent(event);
	}

}
