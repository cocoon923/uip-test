package com.ailife.uip.test.config;

import com.ailife.uip.test.email.EmailImpl;
import com.ailife.uip.test.email.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Created by chenmm on 10/14/2014.
 */
@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailAutoConfiguration {

	public static final String CONFIGURATION_PREFIX = "mail";

	@Configuration
	@ConditionalOnBean(SessionAutoConfiguration.class)
	protected static class IEmailAutoConfiguration {

		@Bean
		protected IEmail emailInitializer() {
			return new EmailImpl();
		}

	}

	@Configuration
	@ConditionalOnBean(Authenticator.class)
	protected static class SessionAutoConfiguration {

		@Autowired
		private Authenticator authenticator;

		@Autowired
		private EmailProperties emailProperties;

		@Bean
		protected Session session() {
			return Session.getDefaultInstance(emailProperties.getSessionProperties(), authenticator);
		}

	}

	@Configuration
	protected static class AuthenticatorAutoConfiguration {

		@Autowired
		private EmailProperties emailProperties;

		@Bean
		protected Authenticator authenticator() {
			return new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailProperties.getFromEmail(), emailProperties.getFromPassword());
				}
			};
		}

	}

}
