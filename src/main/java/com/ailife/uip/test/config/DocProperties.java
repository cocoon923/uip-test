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
	private String rootParamPath;
	private String requestParamPath;
	private String responseParamPath;
	private String docPath;

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

	public String getRootParamPath() {
		return rootParamPath;
	}

	public void setRootParamPath(String rootParamPath) {
		this.rootParamPath = rootParamPath;
	}

	public String getRequestParamPath() {
		return requestParamPath;
	}

	public void setRequestParamPath(String requestParamPath) {
		this.requestParamPath = requestParamPath;
	}

	public String getResponseParamPath() {
		return responseParamPath;
	}

	public void setResponseParamPath(String responseParamPath) {
		this.responseParamPath = responseParamPath;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	@Override
	public String toString() {
		return "DocProperties{" +
				"code='" + code + '\'' +
				", rootParamPath='" + rootParamPath + '\'' +
				", requestParamPath='" + requestParamPath + '\'' +
				", responseParamPath='" + responseParamPath + '\'' +
				", docPath='" + docPath + '\'' +
				'}';
	}
}
