package com.ailife.uip.test.db.customization;

import com.ailife.uip.test.config.DocProperties;
import com.ailife.uip.test.db.entity.Param;
import com.ailife.uip.test.db.service.IParamService;
import com.ailife.uip.test.db.util.IdGenerator;
import com.ailife.uip.test.db.util.StaticDataUtil;
import com.ailife.uip.test.event.DataInitialEvent;
import com.ailife.uip.test.file.entity.Inter;
import com.ailife.uip.test.util.DATATYPE;
import com.ailife.uip.test.util.FileUtil;
import com.ailife.uip.test.util.JsoupUtil;
import com.ailife.uip.test.util.TikaUtil;
import com.alibaba.fastjson.JSONReader;
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
public class UIPDataSourceInitializer implements ApplicationListener<DataInitialEvent> {

	@Autowired
	private DocProperties docProperties;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private IParamService paramService;

	@PostConstruct
	protected void initialize() throws Exception {
		initialPublicParams();
		Param param = StaticDataUtil.getReqRootParam();
		System.out.println(param);
		initialDocument();
	}

	private void initialDocument() {
		InputStream inputStream = FileUtil.loadProjectFile(docProperties.getDocPath());
		String html = TikaUtil.parse(inputStream);
		List<Inter> interList = JsoupUtil.parseHtml(html);
		this.applicationContext.publishEvent(new DataInitialEvent<Inter>(interList, Inter.class));
	}

	private void initialPublicParams() {
		String rootValue = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "ROOT");
		if (!paramService.isPublicParamInitial(rootValue)) {
			List<Param> params = getParams(docProperties.getRootParamPath());
			if (params != null && params.size() > 0) {
				this.applicationContext.publishEvent(new DataInitialEvent<Param>(params, Param.class));
			}
		}

		String requestValue = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "REQUEST");
		if (!paramService.isPublicParamInitial(requestValue)) {
			List<Param> params = getParams(docProperties.getRequestParamPath());
			if (params != null && params.size() > 0) {
				this.applicationContext.publishEvent(new DataInitialEvent<Param>(params, Param.class));
			}
		}

		String responseValue = StaticDataUtil.getStaticDataValue(DATATYPE.PUBLIC_PARAM.toString(), "RESPONSE");
		if (!paramService.isPublicParamInitial(responseValue)) {
			List<Param> params = getParams(docProperties.getResponseParamPath());
			if (params != null && params.size() > 0) {
				this.applicationContext.publishEvent(new DataInitialEvent<Param>(params, Param.class));
			}
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

	@Override
	public void onApplicationEvent(DataInitialEvent event) {
		if (event.getClz() == Param.class) {
			paramService.batchSave(event.getSource());
		}
		if (event.getClz() == Inter.class) {

		}

	}
}
