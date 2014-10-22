package com.ailife.uip.test.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by chenmm on 9/22/2014.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "com.ailife.uip.test")
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication uipApplication = new SpringApplication(Application.class);
//		uipApplication.setResourceLoader(new DefaultResourceLoader());
		uipApplication.run(args);
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

}
