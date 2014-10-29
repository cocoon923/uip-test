package com.ailife.uip.test.util;

import org.slf4j.LoggerFactory;

/**
 * The <code>LogUtil</code> is a utility class producing Loggers.
 *
 * @author chenmm
 */
public class LogUtil {

	/**
	 * Log a message at the INFO level.
	 *
	 * @param clz      the class to be logged
	 * @param messages the message string to be logged
	 */
	public static void info(Class<?> clz, String... messages) {
		StringBuilder sb = new StringBuilder("[").append(clz.getSimpleName()).append("] ");
		for (String message : messages) {
			sb.append(message);
		}
		LoggerFactory.getLogger(clz).info(sb.toString());
	}

	/**
	 * Log a message at the DEBUG level.
	 *
	 * @param clz      the class to be logged
	 * @param messages the message string to be logged
	 */
	public static void debug(Class<?> clz, String... messages) {
		StringBuilder sb = new StringBuilder("[").append(clz.getSimpleName()).append("] ");
		for (String message : messages) {
			sb.append(message);
		}
		LoggerFactory.getLogger(clz).debug(sb.toString());
	}

	/**
	 * Log a message at the ERROR level.
	 *
	 * @param clz      the class to be logged
	 * @param messages the message string to be logged
	 */
	public static void error(Class<?> clz, String... messages) {
		StringBuilder sb = new StringBuilder("[").append(clz.getSimpleName()).append("] ");
		for (String message : messages) {
			sb.append(message);
		}
		LoggerFactory.getLogger(clz).error(sb.toString());
	}


	/**
	 * Log an exception (throwable) at the ERROR level with an accompanying
	 * message.
	 *
	 * @param clz      the class to be logged
	 * @param e        the exception (throwable) to log
	 * @param messages the message string to be logged
	 */
	public static void error(Class<?> clz, Throwable e, String... messages) {
		StringBuilder sb = new StringBuilder("[").append(clz.getSimpleName()).append("] ");
		for (String message : messages) {
			sb.append(message);
		}
		LoggerFactory.getLogger(clz).error(sb.toString(), e);
	}

}
