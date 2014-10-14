package com.ailife.uip.test.config;

import com.ailife.uip.test.util.LogUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * Created by chenmm on 9/29/2014.
 */
@ConfigurationProperties(prefix = DocAutoConfiguration.CONFIGURATION_PREFIX)
public class DocProperties {

	private String code;
	private String basicParamPath;

	@PostConstruct
	public void log() {
		LogUtil.debug(this.getClass(), "Initial Doc Properties." + this.toString());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBasicParamPath() {
		return basicParamPath;
	}

	public void setBasicParamPath(String basicParamPath) {
		this.basicParamPath = basicParamPath;
	}

	@Override
	public String toString() {
		return "DocProperties{" +
				"code='" + code + '\'' +
				", basicParamPath='" + basicParamPath + '\'' +
				'}';
	}
}
