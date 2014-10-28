package com.ailife.uip.test.db.service.impl;

import com.ailife.uip.test.db.dao.IInterDAO;
import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.db.entity.Inter;
import com.ailife.uip.test.db.service.IInterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenmm6 on 2014/10/28.
 */
@Service
@Transactional
public class InterServiceImpl implements IInterService {

	@Autowired
	private IInterDAO interDAO;

	@Autowired
	private IParamDAO paramDAO;

	@Override
	public void batchSave(List<Inter> interList) {

	}

}
