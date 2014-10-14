package com.ailife.uip.test.file.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 9/28/2014.
 */
public class Inter implements Serializable {

	private String name;
	private String desc;
	private String serviceType = "1";
	private String busiCode;
	private String accessCode;
	private String interfaceCode = "1000000001";
	private long serviceSeq;
	private long paramSeq;
	private String implClass;
	private String invokeMethod;
	private String remarks;
	private List<Param> params = new ArrayList<Param>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
		this.accessCode = busiCode;
		this.invokeMethod = busiCode.substring(0, 1).toLowerCase() + busiCode.substring(1);
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getInterfaceCode() {
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}

	public long getServiceSeq() {
		return serviceSeq;
	}

	public void setServiceSeq(long serviceSeq) {
		this.serviceSeq = serviceSeq;
	}

	public long getParamSeq() {
		return paramSeq;
	}

	public void setParamSeq(long paramSeq) {
		this.paramSeq = paramSeq;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	public String getInvokeMethod() {
		return invokeMethod;
	}

	public void setInvokeMethod(String invokeMethod) {
		this.invokeMethod = invokeMethod;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	public void addParam(Param param) {
		this.params.add(param);
	}

	public void addParams(List<Param> params) {
		this.params.addAll(params);
	}

}
