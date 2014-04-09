/**
 * Date: 2013-7-10
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.road.arpg.core.util.ClassUtil;
import com.road.arpg.module.fight.objects.Living;

/**
 * 目标策略工厂
 * 
 * @author yutao.chen
 */
public final class TargetStrategyFactory {

    /**
     * 
     */
    private static Map<Integer, BaseTargetStrategy> targetStrategyMap;

    static {
        init();
    }

    /**
     * 
     */
    private TargetStrategyFactory() {
    }

    /**
     * 初始化目标策略
     */
    public static void init() {
        targetStrategyMap = new HashMap<>();
        Package pack = TargetStrategyFactory.class.getPackage();
        Set<Class<?>> classes = ClassUtil.getClasses(pack);
        try {
            for (Class<?> cla : classes) {
                TargetAnnotation annotation = cla
                                .getAnnotation(TargetAnnotation.class);
                if (annotation != null) {
                    BaseTargetStrategy targetStrategy;

                    targetStrategy = (BaseTargetStrategy) cla.newInstance();
                    targetStrategyMap.put(annotation.SelectTargetType(),
                                    targetStrategy);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param type
     * @return
     */
    public static BaseTargetStrategy getTargetStrategy(int type) {
        return targetStrategyMap.get(type);
    }

    /**
     * 获得指定目标策略的敌人
     * 
     * @param shiftX
     * @param shiftY
     * @param rangeX
     * @param rangeY
     * @param count
     * @param living
     * @param hurtRangeType
     * @param hurtType
     * @param targetType
     * @param y
     * @param x
     * @return
     */
    public static List<Living> getTargetsByType(int rangeX, int rangeY,
                    int count, Living living, int hurtRangeType, int hurtType,
                    int targetType, int x, int y) {
        return getTargetStrategy(targetType).getTarget(rangeX, rangeY, count,
                        living, hurtRangeType, x, y);
    }

    /**
     * 目标类型
     * 
     * @author yutao.chen
     */
    protected interface TargetType {
        /**
         * 随机敌人
         */
        int RANDOM = 0;
        /**
         * 最近敌方
         */
        int POS_NEAR = 1;
        /**
         * 血量最少敌方
         */
        int HP_LOW = 2;
        /**
         * 无目标
         */
        int NO_TARGET = 3;
        /**
         * 所有敌方
         */
        int ALL_TARGET = 4;
        /**
         * 最近友方（不包括自己）
         */
        int NEAR_FRIEND_EXCEPT_SELF = 5;

        /**
         * 最近友方(包括自己)
         */
        int NEAR_FRIEND = 6;

        /**
         * 血量百分比最少的友方
         */
        int HP_RATE_LOW_FRIEND = 7;

        /**
         * 自己
         */
        int SELF = 8;

        /**
         * 友方英雄（随机）
         */
        int FRIEND_HERO = 9;

        /**
         * 敌方英雄（随机）
         */
        int ENEMY_HERO = 10;

        /**
         * 最远敌方
         */
        int FAR_ENEMY = 11;

        /**
         * 锁定英雄
         */
        int LOCK_TARGET = 12;

    }
}
