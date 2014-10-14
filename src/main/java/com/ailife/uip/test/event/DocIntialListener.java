package com.ailife.uip.test.event;

import com.ailife.uip.test.file.JsoupUtil;
import com.ailife.uip.test.file.TikaUtil;
import com.ailife.uip.test.file.entity.Inter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by chenmm on 9/25/2014.
 */
@Resource
public class DocIntialListener implements ApplicationListener<DocInitialEvent<File>> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JsoupUtil jsoupUtil;

	@Override
	public void onApplicationEvent(DocInitialEvent<File> event) {
		try {
			File file = event.getSource();
			if (file.exists()) {
				jsoupUtil.parseHtml(TikaUtil.parse(file));
				List<Inter> inters = jsoupUtil.getInterList();
			} else {
				logger.error("file is not exists!");
			}
		} catch (Exception e) {
			logger.error("Something Wrong!", e);
		}
	}
}
