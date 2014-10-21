package com.ailife.uip.test.db.service.impl;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.service.IParamService;
import com.ailife.uip.test.db.util.StaticDataUtil;
import com.ailife.uip.test.util.DATATYPE;
import com.ailife.uip.test.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenmm
 */
@Service
public class ParamServiceImpl implements IParamService {

	@Autowired
	private IParamDAO paramDAO;

	@Override
	public boolean isPublicParamInitial(String paramType) {
		boolean isInitial = false;
		if (StringUtils.isNotNullorEmpty(paramType)) {
			isInitial = paramDAO.queryParamCountByParentSeq(Long.parseLong(paramType)) > 0;
		}
		return isInitial;
	}

	@Override
	public void batchSave(List<Param> paramList) {
		paramDAO.batchSave(Param.class, paramList);
	}

	@Override
	public Param queryBySeq(long seq) {
		return paramDAO.queryById(Param.class, seq);
	}

	@Override
	public List<Param> queryByParentSeq(long parentSeq) {
		return paramDAO.queryParamByParentSeq(parentSeq);
	}

	@Override
	public Param getReqRootParam() {
		String rootParentSeq = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "ROOT");
		if (StringUtils.isNotNullorEmpty(rootParentSeq)) {
			List<Param> params = paramDAO.queryReqParamByParentSeq(Long.parseLong(rootParentSeq));
			if (params != null && params.size() > 0) {
				return params.get(0);
			}
		}
		return null;
	}

	@Override
	public Param getRespRootParam() {
		String rootParentSeq = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "ROOT");
		if (StringUtils.isNotNullorEmpty(rootParentSeq)) {
			List<Param> params = paramDAO.queryRespParamByParentSeq(Long.parseLong(rootParentSeq));
			if (params != null && params.size() > 0) {
				return params.get(0);
			}
		}
		return null;
	}
}
