/**
 * Date: 2013-7-18
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.type;

/**
 * 伤害范围类型
 * 
 * @author yutao.chen
 */
public interface HurtRangeType {
    /**
     * 椭圆
     */
    byte OVAL = 1;

    /**
     * 矩形
     */
    byte RECTANGLE = 2;

    /**
     * 前方半椭圆
     */
    byte HALF_OVAL = 3;

    /**
     * 前方半矩阵
     */
    byte HALF_RECTANGLE = 4;
}
