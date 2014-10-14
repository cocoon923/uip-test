package com.ailife.uip.test.util;

import com.ailife.uip.test.file.entity.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public class DBUtil {

	public static List<Param> batchGenerateInterParams(List<Param> params, String interSeq) {
		try {
			List<Param> retList = new ArrayList<Param>(params.size());
			for (Param param : params) {
				retList.add(param.generateInterParm(interSeq));
			}
			return retList;
		} catch (Exception e) {
			LogUtil.error(DBUtil.class, "Batch Generate Interface Params Error", e);
			return new ArrayList<Param>();
		}
	}

	public enum STATIC_DATA_TYPE {
		REQUEST_HEAD
	}

}
