/**
 * Date: 2013-9-12
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.List;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.targetStategy.TargetStrategyFactory.TargetType;

/**
 * 血量百分比最少的友方
 * 
 * @author yutao.chen
 */
@TargetAnnotation(SelectTargetType = TargetType.HP_RATE_LOW_FRIEND)
public class HpRateLowFriend extends BaseTargetStrategy {

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
        // 冒泡
        for (int i = 0; i < count; i++) {
            for (int j = i; j < livingList.size() - 1; j++) {
                Living target1 = livingList.get(j);
                Living target2 = livingList.get(j + 1);
                // 比较2个目标的血量百分比
                if (((float) target1.getHp()) / target1.getHpMax() < ((float) target2
                                .getHp()) / target2.getHpMax()) {
                    livingList.set(j + 1, target1);
                    livingList.set(j, target2);
                }
            }
        }
        return livingList.subList(livingList.size() - count, livingList.size());
    }

}
