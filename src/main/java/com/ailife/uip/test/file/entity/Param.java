package com.ailife.uip.test.file.entity;

import java.io.Serializable;

/**
 * Created by chenmm on 9/28/2014.
 */

public class Param implements Serializable, Cloneable {

	private final static String REQUERT_HEAD = "RequestHead";
	private final static String RESPONSE_HEAD = "ResponseHead";

	private long seq;
	private String paramName;
	private String paramCode;
	private String paramValue;
	private String isNull;
	private int sort;
	private String paramType;
	private String remark;

	public Param() {
	}

	public Param(long seq, String paramName, String paramCode, String paramValue, String isNull, int sort, String paramType, String remark) {
		this.seq = seq;
		this.paramName = paramName;
		this.paramCode = paramCode;
		this.paramValue = paramValue;
		this.isNull = isNull;
		this.sort = sort;
		this.paramType = paramType;
		this.remark = remark;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFullParamCode() {
		String fullParamCode = this.getParamCode();
		if ("0".equals(this.getParamType())) {
			fullParamCode = REQUERT_HEAD + ":" + fullParamCode;
		} else if ("1".equals(this.getParamType())) {
			fullParamCode = RESPONSE_HEAD + ":" + fullParamCode;
		}
		return fullParamCode;
	}


	public String getFullParamValue() {
		String fullParamValue = this.getParamValue();
		if ("0".equals(this.getParamType())) {
			fullParamValue = REQUERT_HEAD + ":" + fullParamValue;
		} else if ("1".equals(this.getParamType())) {
			fullParamValue = RESPONSE_HEAD + ":" + fullParamValue;
		}
		return fullParamValue;
	}


	@Override
	protected Param clone() throws CloneNotSupportedException {
		return (Param) super.clone();
	}

	public Param generateInterParm(String interSeq) throws Exception {
		Param interParam = this.clone();
		interParam.setSeq(Long.parseLong(interSeq + "000") + this.getSeq());
		return interParam;
	}
}
