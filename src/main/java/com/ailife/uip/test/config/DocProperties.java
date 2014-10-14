package com.ailife.uip.test.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chenmm on 9/29/2014.
 */
@ConfigurationProperties(prefix = DocAutoConfiguration.CONFIGURATION_PREFIX)
public class DocProperties implements BeanClassLoaderAware {

	private String code;
	private String basicParamPath;

	private ClassLoader classLoader;

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
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

	public ClassLoader getClassLoader() {
		return classLoader;
	}

}
