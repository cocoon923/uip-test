package com.ailife.uip.test.db.customization;

import com.ailife.uip.test.config.DocProperties;
import com.ailife.uip.test.db.entity.Inter;
import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.service.IInterService;
import com.ailife.uip.test.db.service.IParamService;
import com.ailife.uip.test.db.util.IdGenerator;
import com.ailife.uip.test.db.util.StaticDataUtil;
import com.ailife.uip.test.event.DataInitialEvent;
import com.ailife.uip.test.util.DATATYPE;
import com.ailife.uip.test.util.FileUtil;
import com.ailife.uip.test.util.JsoupUtil;
import com.ailife.uip.test.util.TikaUtil;
import com.alibaba.fastjson.JSONReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by chenmm on 10/13/2014.
 */
public class UIPDataSourceInitializer {

	@Autowired
	private DocProperties docProperties;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private IParamService paramService;

	@Autowired
	private IInterService interService;

	@PostConstruct
	protected void initialize() throws Exception {
		initialPublicParams();
//		initialDocument();
	}

	private void initialDocument() {
		InputStream inputStream = FileUtil.loadProjectFile(docProperties.getDocPath());
		interService.batchSave(JsoupUtil.parseHtml(TikaUtil.parse(inputStream)));
	}

	private void initialPublicParams() {
		String rootValue = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "ROOT");
		if (!paramService.isPublicParamInitial(rootValue)) {
			paramService.batchSave(getParams(docProperties.getRootParamPath()));
		}

		String requestValue = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "REQUEST");
		if (!paramService.isPublicParamInitial(requestValue)) {
			paramService.batchSave(getParams(docProperties.getRequestParamPath()));
		}

		String responseValue = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "RESPONSE");
		if (!paramService.isPublicParamInitial(responseValue)) {
			paramService.batchSave(getParams(docProperties.getResponseParamPath()));
		}
	}

	private List<Param> getParams(String filePath) {
		List<Param> params = new ArrayList<Param>();
		InputStream inputStream = FileUtil.loadClassPathFile(filePath);
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

}
