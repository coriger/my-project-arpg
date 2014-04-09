/**
 * Date: 2013-7-10
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.List;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.targetStategy.TargetStrategyFactory.TargetType;

/**
 * 血量最少的目标
 * 
 * @author yutao.chen
 */
@TargetAnnotation(SelectTargetType = TargetType.HP_LOW)
public class HpLowerEnemy extends BaseTargetStrategy {

    /**
     * 
     * @param livingList
     * @param count
     * @param living
     * @return
     */
    @Override
    protected List<Living> calTargets(List<Living> livingList, int count,
                    Living living) {
        if (livingList.size() <= count) {
            return livingList;
        }
        // 冒泡 每次冒出一个血量最少的
        for (int i = 0; i < count; i++) {
            for (int j = i; j < livingList.size() - 1; j++) {
                Living target1 = livingList.get(j);
                Living target2 = livingList.get(j + 1);
                // 比较位置
                if (target1.getHp() < target2.getHp()) {
                    livingList.set(j + 1, target1);
                    livingList.set(j, target2);
                }
            }
        }
        return livingList.subList(livingList.size() - count, livingList.size());
    }
}
