package com.ailife.uip.test.util;

import org.springframework.core.io.Resource;

import java.io.*;

/**
 * Created by chenmm on 9/25/2014.
 */
public class FileUtil {


	public static void saveFile(String name, String content) {
		Resource resource = null;
		try {
			File localFile = new File("doc/" + name);
			localFile.deleteOnExit();
			localFile.createNewFile();

			FileOutputStream fileOutputStream = new FileOutputStream(localFile, false);
			fileOutputStream.write(content.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			LogUtil.error(FileUtil.class, e, "Save file Error!");
		}
	}

	public static InputStream loadProjectFile(String file) {
		try {
			File localFile = new File(file);
			if (localFile.exists()) {
				return new FileInputStream(localFile);
			}
		} catch (FileNotFoundException e) {
			LogUtil.error(FileUtil.class, e, "File not found!");
		}
		return null;
	}

	public static InputStream loadClassPathFile(String file) {
		LogUtil.debug(FileUtil.class, "Load File <" + file + ">");
		return FileUtil.class.getClassLoader().getResourceAsStream(file);
	}

}
