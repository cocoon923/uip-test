package com.ailife.uip.test.db.entity;

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
	private String paramTimes;
	private int sort;
	private String paramType;
	private String remark;
	private long parentSeq;

	public Param() {
	}

	public Param(long seq, String paramName, String paramCode, String paramClazz, String paramLength, String paramTimes, int sort, String paramType, String remark, long parentSeq) {
		this.seq = seq;
		this.paramName = paramName;
		this.paramCode = paramCode;
		this.paramClazz = paramClazz;
		this.paramLength = paramLength;
		this.paramTimes = paramTimes;
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

	public String getParamTimes() {
		return paramTimes;
	}

	public void setParamTimes(String paramTimes) {
		this.paramTimes = paramTimes;
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
				", paramTimes='" + paramTimes + '\'' +
				", sort=" + sort +
				", paramType='" + paramType + '\'' +
				", remark='" + remark + '\'' +
				", parentSeq=" + parentSeq +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Param param = (Param) o;

		if (parentSeq != param.parentSeq) return false;
		if (seq != param.seq) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (seq ^ (seq >>> 32));
		result = 31 * result + (int) (parentSeq ^ (parentSeq >>> 32));
		return result;
	}
}
