package com.ailife.uip.test.util;

/**
 * Created by chenmm on 9/25/2014.
 */
public enum HTMLLEVEL {
	h1, h2, h3, h4, table;

	public String getBeginTag() {
		return "<" + this.toString().toLowerCase() + ">";
	}

	public String getEndTag() {
		return "</" + this.toString().toLowerCase() + ">";
	}

}
