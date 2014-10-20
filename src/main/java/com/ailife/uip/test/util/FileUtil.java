package com.ailife.uip.test.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by chenmm on 9/25/2014.
 */
public class FileUtil {


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
			LogUtil.error(FileUtil.class, "Save file Error!", e);
		}
	}

	public static InputStream loadFile(String file) {
		LogUtil.debug(FileUtil.class, "Load File <" + file + ">");

		ClassLoader classLoader = FileUtil.class.getClassLoader();
		return classLoader.getResourceAsStream(file);
	}

}
