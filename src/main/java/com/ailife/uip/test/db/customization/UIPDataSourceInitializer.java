package com.ailife.uip.test.db.customization;

import com.ailife.uip.test.config.DocProperties;
import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.db.dao.IStaticDataDAO;
import com.ailife.uip.test.db.service.IParamService;
import com.ailife.uip.test.db.util.IdGenerator;
import com.ailife.uip.test.db.util.StaticDataUtil;
import com.ailife.uip.test.event.DataInitialEvent;
import com.ailife.uip.test.file.entity.Param;
import com.ailife.uip.test.util.FileUtil;
import com.ailife.uip.test.util.LogUtil;
import com.alibaba.fastjson.JSONReader;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public class UIPDataSourceInitializer implements ApplicationListener<DataInitialEvent<List<Param>>> {

	@Autowired
	private DocProperties docProperties;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private IParamService paramService;

	@PostConstruct
	protected void initialize() throws Exception {
//		StaticDataUtil.getStaticData(PUBLIC_MEMBER).
		String filePath = "";
		initialPublicParams(filePath);
	}

	private void initialPublicParams(String filePath) {
		List<Param> params = getParams(filePath);
		if (params != null && params.size() > 0) {
			this.applicationContext.publishEvent(new DataInitialEvent<List<Param>>(params));
		}
	}

	private List<Param> getParams(String filePath) {
		List<Param> params = new ArrayList<Param>();
		InputStream inputStream = FileUtil.loadFile(filePath);
		JSONReader jsonReader = new JSONReader(new InputStreamReader(inputStream));
		jsonReader.startArray();
		while (jsonReader.hasNext()) {
			Param param = jsonReader.readObject(Param.class);
			param.setSeq(IdGenerator.getNewId());
			params.add(param);
		}
		jsonReader.endArray();
		jsonReader.close();
		return params;
	}

	@Override
	public void onApplicationEvent(DataInitialEvent<List<Param>> event) {
//		List<Param> paramList = event.getSource();
//		paramDAO.batchInsert(Param.class, paramList);
//		List<Param> dataList = paramDAO.selectAll();
//		for (Param param : dataList) {
//			LogUtil.debug(this.getClass(), param.toString());
//		}
	}
}
