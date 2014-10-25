package com.ailife.uip.test.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 9/28/2014.
 */
public class Inter implements Serializable {

	private long seq;
	private String interName;
	private String interDesc;
	private String interCode;
	private String implClass;
	private String invokeMethod;
	private int sort;
	private String remark;
	private List<Param> params = new ArrayList<Param>();

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getInterName() {
		return interName;
	}

	public void setInterName(String interName) {
		this.interName = interName;
	}

	public String getInterDesc() {
		return interDesc;
	}

	public void setInterDesc(String interDesc) {
		this.interDesc = interDesc;
	}

	public String getInterCode() {
		return interCode;
	}

	public void setInterCode(String interCode) {
		this.interCode = interCode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "Inter{" +
				"seq=" + seq +
				", interName='" + interName + '\'' +
				", interDesc='" + interDesc + '\'' +
				", interCode='" + interCode + '\'' +
				", implClass='" + implClass + '\'' +
				", invokeMethod='" + invokeMethod + '\'' +
				", sort=" + sort +
				", remark='" + remark + '\'' +
				", params=" + params +
				'}';
	}
}
