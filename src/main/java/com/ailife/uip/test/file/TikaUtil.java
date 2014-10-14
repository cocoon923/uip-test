package com.ailife.uip.test.file;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by chenmm on 9/25/2014.
 */
public class TikaUtil {

	private final static Logger logger = LoggerFactory.getLogger(TikaUtil.class);

	public static String parse(File file) {
		try {
			ContentHandler handler = new ToXMLContentHandler();
			InputStream inputStream = new FileInputStream(file);
			AutoDetectParser parser = new AutoDetectParser();
			Metadata metadata = new Metadata();
			try {
				parser.parse(inputStream, handler, metadata);
				return handler.toString();
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			logger.error("Parse Doc Error!", e);
			return "";
		}
	}

}
