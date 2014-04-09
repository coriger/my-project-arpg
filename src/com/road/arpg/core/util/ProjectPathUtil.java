/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  上午11:30:43
 */
package com.road.arpg.core.util;

import java.io.File;
import java.net.URL;

/**
 * 项目路径工具类.
 * 
 * @author Dream.xie
 */
public final class ProjectPathUtil {
    /**
     * 项目根目录路径
     */
    private static final String PROJECT_DIR_PATH = getPah("../../");
    /**
     * bin目录路径
     */
    private static final String BIN_DIR_PATH = getPah("../");
    /**
     * config目录路径
     */
    private static final String CONFIG_DIR_PATH = getPah("../../config");
    /**
     * lib目录路径
     */
    private static final String LIB_DIR_PATH = getPah("../../lib");
    /**
     * log目录路径
     */
    private static final String LOG_DIR_PATH = getPah("../../log");
    /**
     * test目录路径
     */
    private static final String MODULE_DIR_PATH = getPah("../../module");
    /**
     * test目录路径
     */
    private static final String TEST_DIR_PATH = getPah("../../test");
    /**
     * i18n目录路径
     */
    private static final String I18N_DIR_PATH = getPah("../../i18n");
    /**
     * tmp目录路径
     */
    private static final String TMP_DIR_PATH = getPah("../../tmp");

    /**
     * 默认构造函数
     */
    private ProjectPathUtil() {
    }

    /**
     * 
     * @return 返回项目的根目录
     */
    public static String getProjectDirPath() {
        return PROJECT_DIR_PATH;
    }

    /**
     * 
     * @return 返回test目录
     */
    public static String getTestDirPath() {
        return TEST_DIR_PATH;
    }

    /**
     * 
     * @return 返回bin目录
     */
    public static String getBinDirPath() {
        return BIN_DIR_PATH;
    }

    /**
     * 
     * @return 获得config目录
     */
    public static String getConfigDirPath() {
        return CONFIG_DIR_PATH;
    }

    /**
     * 
     * @return 获得lib目录
     */
    public static String getLibDirPath() {
        return LIB_DIR_PATH;
    }

    /**
     * 
     * @return 获得log目录
     */
    public static String getLogDirPath() {
        return LOG_DIR_PATH;
    }

    /**
     * 
     * @return 获得module目录
     */
    public static String getModuleDirPath() {
        return MODULE_DIR_PATH;
    }

    /**
     * 
     * @return 获得javadoc目录
     */
    public static String getTmpDirPath() {
        return TMP_DIR_PATH;
    }

    /**
     * 
     * @return 获得i18n目录
     */
    public static String getI18nDirPath() {
        return I18N_DIR_PATH;
    }

    /**
     * 获得路径，已bin目录为基础生成其他目录。
     * 
     * @return
     */
    private static String getPah(String subDir) {
        URL binUrl = Thread.currentThread().getContextClassLoader()
                        .getResource("");
        File binFile = null;
        String result = null;
        try {
            binFile = new File(binUrl.toURI());
            if (subDir == null) {
                result = binFile.toString();
            } else {
                result = new File(binFile + File.separator + subDir)
                                .getCanonicalPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
