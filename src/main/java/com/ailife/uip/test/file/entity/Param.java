package com.ailife.uip.test.file.entity;

import java.io.Serializable;

/**
 * Created by chenmm on 9/28/2014.
 */

public class Param implements Serializable {

	private long seq;
	private String paramName;
	private String paramCode;
	private String paramClazz;
	private String paramLength;
	private String isNull;
	private int sort;
	private String paramType;
	private String remark;
	private long parentSeq;

	public Param() {
	}

	public Param(long seq, String paramName, String paramCode, String paramClazz, String paramLength, String isNull, int sort, String paramType, String remark, long parentSeq) {
		this.seq = seq;
		this.paramName = paramName;
		this.paramCode = paramCode;
		this.paramClazz = paramClazz;
		this.paramLength = paramLength;
		this.isNull = isNull;
		this.sort = sort;
		this.paramType = paramType;
		this.remark = remark;
		this.parentSeq = parentSeq;
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

	public String getParamClazz() {
		return paramClazz;
	}

	public void setParamClazz(String paramClazz) {
		this.paramClazz = paramClazz;
	}

	public String getParamLength() {
		return paramLength;
	}

	public void setParamLength(String paramLength) {
		this.paramLength = paramLength;
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

	public long getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(long parentSeq) {
		this.parentSeq = parentSeq;
	}

	@Override
	public String toString() {
		return "Param{" +
				"seq=" + seq +
				", paramName='" + paramName + '\'' +
				", paramCode='" + paramCode + '\'' +
				", paramClazz='" + paramClazz + '\'' +
				", paramLength='" + paramLength + '\'' +
				", isNull='" + isNull + '\'' +
				", sort=" + sort +
				", paramType='" + paramType + '\'' +
				", remark='" + remark + '\'' +
				", parentSeq=" + parentSeq +
				'}';
	}
}
