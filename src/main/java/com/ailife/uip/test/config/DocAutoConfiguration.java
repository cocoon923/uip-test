package com.ailife.uip.test.config;

import com.ailife.uip.test.db.customization.UIPDataSourceInitializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenmm on 10/13/2014.
 */
@Configuration
@EnableConfigurationProperties(DocProperties.class)
public class DocAutoConfiguration {

	public static final String CONFIGURATION_PREFIX = "spring.doc";

	@Configuration
	@ConditionalOnMissingBean(UIPDataSourceInitializer.class)
	protected static class UIPDataSourceInitializerConfiguration {

		@Bean
		public UIPDataSourceInitializer uipDataSourceInitializer() {
			return new UIPDataSourceInitializer();
		}

	}

}
