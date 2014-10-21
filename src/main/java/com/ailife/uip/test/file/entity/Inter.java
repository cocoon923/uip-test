package com.ailife.uip.test.file.entity;

import com.ailife.uip.test.db.entity.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 9/28/2014.
 */
public class Inter implements Serializable {

	private long seq;
	private String name;
	private String desc;
	private String busiCode;
	private String implClass;
	private String invokeMethod;
	private int sort;
	private String remarks;
	private List<Param> params = new ArrayList<Param>();

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
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

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public void addParams(List<Param> params) {
		this.params.addAll(params);
	}
}
