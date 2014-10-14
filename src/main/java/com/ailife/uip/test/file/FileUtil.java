package com.ailife.uip.test.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by chenmm on 9/25/2014.
 */
public class FileUtil {

	private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static void saveFile(String name, String content) {
		try {
			File localFile = new File("doc/" + name);
			localFile.deleteOnExit();
			localFile.createNewFile();

			FileOutputStream fileOutputStream = new FileOutputStream(localFile, false);
			fileOutputStream.write(content.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			logger.error("Save file Error!", e);
		}
	}

}
