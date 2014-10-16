package com.ailife.uip.test.util;

import com.google.common.base.CaseFormat;

/**
 * Created by chenmm on 10/16/2014.
 */
public class StringUtils {

	public static String lowerCamel2UpperUnderscore(String source){
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, source);
	}

	public static String upperUnderscore2LowerCamel(String source){
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, source);
	}

}
