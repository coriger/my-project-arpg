/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-17  下午4:54:42
 */
package com.road.arpg.core.util;

/**
 * @author Dream.xie
 */
public final class StringUtil {
    /**
     * 
     */
    private StringUtil() {

    }

    /**
     * 是否null或者空字符串
     * 
     * @param str
     * @return
     */
    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
