package com.gh.app.util.tool.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;


/**
 * 通用日志器，依赖于slf4j的API，最大限度优化性能
 * 
 * 
 */
public class Logs {
	
	private static final Object[] EMPTY_ARRAY = new Object[] {};
	private static final String FQCN = Logs.class.getName();
	private static final Integer DEPTH = 3;

	/**
	 * 获取适配日志器，供内部调用
	 * 
	 * @return
	 */
	@SuppressWarnings("restriction")
	private static LocationAwareLogger getLocationAwareLogger(final int depth) {
		String className = sun.reflect.Reflection.getCallerClass(depth)
				.getName();
		return (LocationAwareLogger) LoggerFactory.getLogger(className);
	}

	/**
	 * 静态的获取日志器
	 * 
	 * @return
	 */
	public static Logger getLogger() {
		return getLocationAwareLogger(DEPTH);
	}

	public static String getName() {
		return getLocationAwareLogger(DEPTH).getName();
	}

	public static boolean isTraceEnabled() {
		return getLocationAwareLogger(DEPTH).isTraceEnabled();
	}

	public static void trace(String msg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, null);
	}

	public static void trace(String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.TRACE_INT, format, new Object[] { arg },
				null);
	}

	public static void trace(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.TRACE_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void trace(String format, Object... arguments) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.TRACE_INT, format, arguments, null);
	}

	public static void trace(String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isTraceEnabled(Marker marker) {
		return getLocationAwareLogger(DEPTH).isTraceEnabled(marker);
	}

	public static void trace(Marker marker, String msg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, null);
	}

	public static void trace(Marker marker, String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.TRACE_INT, format, new Object[] { arg },
				null);
	}

	public static void trace(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.TRACE_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void trace(Marker marker, String format, Object... argArray) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.TRACE_INT, format, argArray, null);
	}

	public static void trace(Marker marker, String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isInfoEnabled() {
		return getLocationAwareLogger(DEPTH).isInfoEnabled();
	}

	public static void info(String msg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.INFO_INT,
				msg, EMPTY_ARRAY, null);
	}

	public static void info(String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.INFO_INT,
				format, new Object[] { arg }, null);
	}

	public static void info(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.INFO_INT,
				format, new Object[] { arg1, arg2 }, null);
	}

	public static void info(String format, Object... arguments) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.INFO_INT,
				format, arguments, null);
	}

	public static void info(String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.INFO_INT,
				msg, EMPTY_ARRAY, t);
	}

	public static boolean isInfoEnabled(Marker marker) {
		return getLocationAwareLogger(DEPTH).isInfoEnabled(marker);
	}

	public static void info(Marker marker, String msg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, null);
	}

	public static void info(Marker marker, String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.INFO_INT, format, new Object[] { arg },
				null);
	}

	public static void info(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.INFO_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void info(Marker marker, String format, Object... argArray) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.INFO_INT, format, argArray, null);
	}

	public static void info(Marker marker, String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isDebugEnabled() {
		return getLocationAwareLogger(DEPTH).isDebugEnabled();
	}

	public static void debug(String msg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);
	}

	public static void debug(String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.DEBUG_INT, format, new Object[] { arg },
				null);
	}

	public static void debug(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.DEBUG_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void debug(String format, Object... arguments) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.DEBUG_INT, format, arguments, null);
	}

	public static void debug(String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isDebugEnabled(Marker marker) {
		return getLocationAwareLogger(DEPTH).isDebugEnabled(marker);
	}

	public static void debug(Marker marker, String msg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);
	}

	public static void debug(Marker marker, String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.DEBUG_INT, format, new Object[] { arg },
				null);
	}

	public static void debug(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.DEBUG_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void debug(Marker marker, String format, Object... argArray) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.DEBUG_INT, format, argArray, null);
	}

	public static void debug(Marker marker, String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isWarnEnabled() {
		return getLocationAwareLogger(DEPTH).isWarnEnabled();
	}

	public static void warn(String msg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.WARN_INT,
				msg, EMPTY_ARRAY, null);
	}

	public static void warn(String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.WARN_INT,
				format, new Object[] { arg }, null);
	}

	public static void warn(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.WARN_INT,
				format, new Object[] { arg1, arg2 }, null);
	}

	public static void warn(String format, Object... arguments) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.WARN_INT,
				format, arguments, null);
	}

	public static void warn(String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(null, FQCN, LocationAwareLogger.WARN_INT,
				msg, EMPTY_ARRAY, t);
	}

	public static boolean isWarnEnabled(Marker marker) {
		return getLocationAwareLogger(DEPTH).isWarnEnabled(marker);
	}

	public static void warn(Marker marker, String msg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, null);
	}

	public static void warn(Marker marker, String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.WARN_INT, format, new Object[] { arg },
				null);
	}

	public static void warn(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.WARN_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void warn(Marker marker, String format, Object... argArray) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.WARN_INT, format, argArray, null);
	}

	public static void warn(Marker marker, String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isErrorEnabled() {
		return getLocationAwareLogger(DEPTH).isErrorEnabled();
	}

	public static void error(String msg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);
	}

	public static void error(String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.ERROR_INT, format, new Object[] { arg },
				null);
	}

	public static void error(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.ERROR_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void error(String format, Object... arguments) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.ERROR_INT, format, arguments, null);
	}

	public static void error(String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(null, FQCN,
				LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, t);
	}

	public static boolean isErrorEnabled(Marker marker) {
		return getLocationAwareLogger(DEPTH).isErrorEnabled(marker);
	}

	public static void error(Marker marker, String msg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);
	}

	public static void error(Marker marker, String format, Object arg) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.ERROR_INT, format, new Object[] { arg },
				null);
	}

	public static void error(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.ERROR_INT, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void error(Marker marker, String format, Object... argArray) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.ERROR_INT, format, argArray, null);
	}

	public static void error(Marker marker, String msg, Throwable t) {
		getLocationAwareLogger(DEPTH).log(marker, FQCN,
				LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, t);
	}
}