package com.ailife.uip.test.db.entity;

/**
 * Created by chenmm6 on 2014/10/30.
 */
public class ItemRelat {

	private Long seq;
	private Long itemSeq;
	private Long relatItemSeq;
	private String itemType;
	private String relatItemType;

	public ItemRelat() {
	}

	public ItemRelat(Long seq, Long itemSeq, Long relatItemSeq, String itemType, String relatItemType) {
		this.seq = seq;
		this.itemSeq = itemSeq;
		this.relatItemSeq = relatItemSeq;
		this.itemType = itemType;
		this.relatItemType = relatItemType;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getItemSeq() {
		return itemSeq;
	}

	public void setItemSeq(Long itemSeq) {
		this.itemSeq = itemSeq;
	}

	public Long getRelatItemSeq() {
		return relatItemSeq;
	}

	public void setRelatItemSeq(Long relatItemSeq) {
		this.relatItemSeq = relatItemSeq;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getRelatItemType() {
		return relatItemType;
	}

	public void setRelatItemType(String relatItemType) {
		this.relatItemType = relatItemType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ItemRelat{");
		sb.append("seq=").append(seq);
		sb.append(", itemSeq=").append(itemSeq);
		sb.append(", relatItemSeq=").append(relatItemSeq);
		sb.append(", itemType='").append(itemType).append('\'');
		sb.append(", relatItemType='").append(relatItemType).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
