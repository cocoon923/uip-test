package com.ailife.uip.test.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * Created by chenmm on 10/14/2014.
 */
@ConfigurationProperties(prefix = EmailAutoConfiguration.CONFIGURATION_PREFIX)
public class EmailProperties implements InitializingBean {

	private String fromEmail;
	private String fromPassword;
	private final Smtp smtp = new Smtp();
	private final Store store = new Store();

	private Properties sessionProperties = null;

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	public Smtp getSmtp() {
		return smtp;
	}

	public Store getStore() {
		return store;
	}

	public Properties getSessionProperties() {
		if (this.sessionProperties == null) {
			this.setSessionProperties(new Properties());
		}
		return sessionProperties;
	}

	public void setSessionProperties(Properties sessionProperties) {
		this.sessionProperties = sessionProperties;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", this.getSmtp().getHost());
		props.put("mail.store.protocol", this.getStore().getProtocol());
		props.put("mail.smtp.port", this.getSmtp().getPort());
		props.put("mail.smtp.auth", this.getSmtp().isAuth());
		this.setSessionProperties(props);
	}

	public static class Store {

		private String protocol = "smtp";

		public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
	}

	public static class Smtp {

		private String host = "mail.asiainfo.com";
		private int port = 25;
		private boolean auth = true;

		public boolean isAuth() {
			return auth;
		}

		public void setAuth(boolean auth) {
			this.auth = auth;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}

}
