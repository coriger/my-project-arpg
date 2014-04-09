/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-13  下午2:15:21
 */
package com.road.arpg.module.fight.util;

import java.util.Comparator;

import com.road.arpg.module.fight.objects.Living;

/**
 * 某点和生物列表排序用的比较器
 * 
 * @author shandong.su
 */
public class P2LivingComparator implements Comparator<Living> {

    /**
     * 比较的点
     */
    private Point point;

    /**
     * 
     * @param point
     */
    public P2LivingComparator(Point point) {
        this.point = point;
    }

    @Override
    public int compare(Living o1, Living o2) {
        return PointHelper.distanceSquare(point, o1.getPos())
                        - PointHelper.distanceSquare(point, o2.getPos());
    }
}
