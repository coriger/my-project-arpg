/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-4  下午4:42:19
 */
package com.road.arpg.module.fight.util;

/**
 * @author shandong.su
 */
public final class CostTimeUtil {

    /**
     * 
     */
    private CostTimeUtil() {
    }

    /**
     * 用来消耗时间的
     * 
     * @param x
     *            消耗的毫秒数
     */
    public static void costTimes(int x) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < x) {
            int sd = 3;
            sd += sd;
        }
    }

}
