package com.ailife.uip.test.db.entity;

import java.io.Serializable;

/**
 * Created by chenmm on 9/28/2014.
 */

public class Param implements Serializable {

	private Long seq;
	private String paramName;
	private String paramCode;
	private String paramClazz;
	private String paramLength;
	private String paramTimes;
	private Integer sort;
	private String paramType;
	private String remark;
	private Long parentSeq;

	public Param() {
	}

	public Param(Long seq, String paramName, String paramCode, String paramClazz, String paramLength, String paramTimes, Integer sort, String paramType, String remark, Long parentSeq) {
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

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
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

	public Long getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
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

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Param{");
		sb.append("seq=").append(seq);
		sb.append(", paramName='").append(paramName).append('\'');
		sb.append(", paramCode='").append(paramCode).append('\'');
		sb.append(", paramClazz='").append(paramClazz).append('\'');
		sb.append(", paramLength='").append(paramLength).append('\'');
		sb.append(", paramTimes='").append(paramTimes).append('\'');
		sb.append(", sort=").append(sort);
		sb.append(", paramType='").append(paramType).append('\'');
		sb.append(", remark='").append(remark).append('\'');
		sb.append(", parentSeq=").append(parentSeq);
		sb.append('}');
		return sb.toString();
	}
}
