package com.ailife.uip.test.db.util;

import com.ailife.uip.test.db.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by chenmm on 10/16/2014.
 */
@Repository
public class IdGenerator {

	@Autowired
	private void init(IdGeneratorService idGeneratorService) {
		IdGenerator.idGeneratorService = idGeneratorService;
	}

	private static IdGeneratorService idGeneratorService;

	public static long getNewId() {
		return idGeneratorService.getNewId();
	}

	public static long getMaxId() {
		return idGeneratorService.getMaxId();
	}


}
