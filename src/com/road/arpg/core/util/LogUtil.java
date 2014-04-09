/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午4:51:56
 */
package com.road.arpg.core.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author dream.xie
 * 
 */
public final class LogUtil {
    /**
     * 
     */
    private LogUtil() {

    }

    /**
     * 
     * @param message
     */
    public static void debug(String message) {
        if (isDebug()) {
            getDebugLogger().debug(message);
        }
    }

    /**
     * 
     * @param message
     */
    public static void debug(String message, Throwable throwable) {
        if (isDebug()) {
            getDebugLogger().debug(message, throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void debug(Throwable throwable) {
        if (isDebug()) {
            getDebugLogger().debug(throwable.getMessage(), throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void info(String message) {
        if (isInfo()) {
            getInfoLogger().info(message);
        }
    }

    /**
     * 
     * @param message
     */
    public static void info(String message, Throwable throwable) {
        if (isInfo()) {
            getInfoLogger().info(message, throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void info(Throwable throwable) {
        if (isInfo()) {
            getInfoLogger().info(throwable.getMessage(), throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void warn(String message) {
        if (isWarn()) {
            getWarnLogger().warn(message);
        }
    }

    /**
     * 
     * @param message
     */
    public static void warn(String message, Throwable throwable) {
        if (isWarn()) {
            getWarnLogger().warn(message, throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void warn(Throwable throwable) {
        if (isWarn()) {
            getWarnLogger().warn(throwable.getMessage(), throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void error(String message) {
        if (isError()) {
            getErrorLogger().error(message);
        }
    }

    /**
     * 
     * @param message
     */
    public static void error(String message, Throwable throwable) {
        if (isError()) {
            getErrorLogger().error(message, throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void error(Throwable throwable) {
        if (isError()) {
            getErrorLogger().error(throwable.getMessage(), throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void fatal(String message) {
        if (isFatal()) {
            getFatalLogger().fatal(message);
        }
    }

    /**
     * 
     * @param message
     */
    public static void fatal(String message, Throwable throwable) {
        if (isFatal()) {
            getFatalLogger().fatal(message, throwable);
        }
    }

    /**
     * 
     * @param message
     */
    public static void fatal(Throwable throwable) {
        if (isFatal()) {
            getFatalLogger().fatal(throwable.getMessage(), throwable);
        }
    }

    /**
     * 
     * @param message
     */
    private static Logger getDebugLogger() {
        return Logger.getLogger("debugLogger");
    }

    /**
     * 
     * @param message
     */
    private static Logger getInfoLogger() {
        return Logger.getLogger("infoLogger");
    }

    /**
     * 
     * @param message
     */
    private static Logger getWarnLogger() {
        return Logger.getLogger("warnLogger");
    }

    /**
     * 
     * @param message
     */
    private static Logger getErrorLogger() {
        return Logger.getLogger("errorLogger");
    }

    /**
     * 
     * @param message
     */
    private static Logger getFatalLogger() {
        return Logger.getLogger("fatalLogger");
    }

    /**
     * 
     * @param message
     */
    private static Boolean isDebug() {
        return Logger.getRootLogger().getLevel().toInt() <= Level.DEBUG_INT;
    }

    /**
     * 
     * @param message
     */
    private static Boolean isInfo() {
        return Logger.getRootLogger().getLevel().toInt() <= Level.INFO_INT;
    }

    /**
     * 
     * @param message
     */
    private static Boolean isWarn() {
        return Logger.getRootLogger().getLevel().toInt() <= Level.WARN_INT;
    }

    /**
     * 
     * @param message
     */
    private static Boolean isError() {
        return Logger.getRootLogger().getLevel().toInt() <= Level.ERROR_INT;
    }

    /**
     * 
     * @param message
     */
    private static Boolean isFatal() {
        return Logger.getRootLogger().getLevel().toInt() <= Level.FATAL_INT;
    }

}
