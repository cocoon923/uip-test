package com.ailife.uip.test.controllers;

import com.ailife.uip.test.db.dao.IParamDAO;
import com.ailife.uip.test.event.DocInitialEventPublisher;
import com.ailife.uip.test.file.entity.Param;
import com.ailife.uip.test.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by chenmm on 9/23/2014.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private DocInitialEventPublisher publisher;

	@RequestMapping()
	public ModelAndView index() {
		return new ModelAndView("upload.html");
	}

	@RequestMapping(value = "/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		boolean isSuccess = false;
		File localFile = null;
		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				String fileName = file.getOriginalFilename();
				localFile = new File("doc/" + fileName);
				localFile.deleteOnExit();
				localFile.createNewFile();

				FileOutputStream fileOutputStream = new FileOutputStream(localFile, false);
				fileOutputStream.write(bytes);
				fileOutputStream.flush();
				fileOutputStream.close();
				isSuccess = true;
			}
		} catch (Exception e) {
			logger.error("Upload Error", e);
		}
		if (isSuccess) {
			publisher.monitor(localFile);
			return "Upload Success!";
		} else {
			return "Upload Fail!";
		}
	}

}
