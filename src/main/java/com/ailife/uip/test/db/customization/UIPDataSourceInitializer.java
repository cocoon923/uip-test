package com.ailife.uip.test.db.customization;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.event.DataInitialEvent;
import com.ailife.uip.test.file.entity.Param;
import com.alibaba.fastjson.JSONReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public class UIPDataSourceInitializer implements ApplicationListener<DataInitialEvent<List<Param>>> {

	@Value("${doc.inter.basicParamPath}")
	private String basicParamPath;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private IParamDAO paramDAO;

	@PostConstruct
	protected void initialize() throws Exception {
		List<Param> params = new ArrayList<Param>();
		Resource resource = this.applicationContext.getResource("classpath:" + basicParamPath);
		if (resource.exists()) {
			JSONReader jsonReader = new JSONReader(new FileReader(resource.getFile()));
			jsonReader.startArray();
			while (jsonReader.hasNext()) {
				Param param = jsonReader.readObject(Param.class);
				params.add(param);
			}
			jsonReader.endArray();
			jsonReader.close();
		}
		if (params != null && params.size() > 0) {
			this.applicationContext.publishEvent(new DataInitialEvent<List<Param>>(params));
		}
	}

	@Override
	public void onApplicationEvent(DataInitialEvent<List<Param>> event) {
		List<Param> param = event.getSource();
		paramDAO.insert(param.get(0));
	}
}
