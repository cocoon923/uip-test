package com.ailife.uip.test.config;

import com.ailife.uip.test.db.customization.UIPDataSourceInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by chenmm on 10/13/2014.
 */
@Configuration
@EnableConfigurationProperties(DocProperties.class)
public class DocAutoConfiguration {

	public static final String CONFIGURATION_PREFIX = "doc.inter";

	@Configuration
	@ConditionalOnMissingBean(UIPDataSourceInitializer.class)
	protected static class UIPDataSourceInitializerConfiguration {

//		@Autowired
//		private DataSource dataSource;

		@Bean
		public UIPDataSourceInitializer uipDataSourceInitializer() {
			return new UIPDataSourceInitializer();
		}

//		@Bean(name = "transactionManager")
//		public PlatformTransactionManager platformTransactionManager() {
//			return new DataSourceTransactionManager(dataSource);
//		}

	}

}
