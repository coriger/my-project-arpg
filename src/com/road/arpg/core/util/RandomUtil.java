/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.core.util;

import java.util.Random;

/**
 * 随机函数类
 * 
 * @author shandong.su
 */
public final class RandomUtil {
    /**
     * 随机函数
     */
    private static final Random RAND = new Random();

    /**
     * 
     */
    private static final AtomicLock LOCK = new AtomicLock();

    /**
     * 既然RAND是静态的，那么这个也做静态的吧
     */
    private static int ran = 0;

    /**
     * 
     */
    private RandomUtil() {
    }

    /**
     * 对随机的均匀分布有要求
     * 
     * @param i
     * @return
     */
    public static int randInt(int i) {
        try {
            LOCK.lock();
            return RAND.nextInt(i);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 对随机均匀分布没有要求的时候，用此方法是随机函数的2的以上的效率
     * 
     * @param i
     * @return
     */
    public static int notRandInt(int i) {
        return (int) (0xffffffff & (System.currentTimeMillis())) % i;
    }

    /**
     * 返回是否在随机范围内
     * 
     * @param value
     * @param maxValue
     * @return
     */
    public static boolean inRandom(int value, int maxValue) {
        try {
            LOCK.lock();
            ran = RAND.nextInt(maxValue);
            return ran <= value;
        } finally {
            LOCK.unlock();
        }
    }

}
