package com.ailife.uip.test.util;

import org.slf4j.LoggerFactory;

/**
 * The <code>LogUtil</code> is a utility class producing Loggers.
 * 
 * @author chenmm
 * 
 */
public class LogUtil {

	/**
	 * Log a message at the INFO level.
	 * 
	 * @param clz
	 *            the class to be logged
	 * @param logStr
	 *            the message string to be logged
	 */
	public static void info(Class<?> clz, String logStr) {
		LoggerFactory.getLogger(clz).info("[" + clz.getSimpleName() + "] " + logStr);
	}

	/**
	 * Log a message at the DEBUG level.
	 * 
	 * @param clz
	 *            the class to be logged
	 * @param logStr
	 *            the message string to be logged
	 */
	public static void debug(Class<?> clz, String logStr) {
		LoggerFactory.getLogger(clz).debug("[" + clz.getSimpleName() + "] " + logStr);
	}

	/**
	 * Log a message at the ERROR level.
	 * 
	 * @param clz
	 *            the class to be logged
	 * @param logStr
	 *            the message string to be logged
	 */
	public static void error(Class<?> clz, String logStr) {
		LoggerFactory.getLogger(clz).error("[" + clz.getSimpleName() + "] " + logStr);
	}

	/**
	 * Log an exception (throwable) at the ERROR level with an accompanying
	 * message.
	 * 
	 * @param clz
	 *            the class to be logged
	 * @param logStr
	 *            the message string to be logged
	 * @param e
	 *            the exception (throwable) to log
	 */
	public static void error(Class<?> clz, String logStr, Throwable e) {
		LoggerFactory.getLogger(clz).error("[" + clz.getSimpleName() + "] " + logStr, e);
	}

}
