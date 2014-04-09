/**
 * Date: 2013-7-10
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.ArrayList;
import java.util.List;

import com.road.arpg.core.util.RandomUtil;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.targetStategy.TargetStrategyFactory.TargetType;

/**
 * 随机敌人
 * 
 * @author yutao.chen
 */
@TargetAnnotation(SelectTargetType = TargetType.RANDOM)
public class RandomEnemy extends BaseTargetStrategy {

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
        List<Living> targetList = new ArrayList<>();
        // 随机找出指定目标数
        while (targetList.size() < count) {
            int index = RandomUtil.randInt(livingList.size());
            targetList.add(livingList.get(index));
            livingList.remove(index);
        }
        return targetList;
    }
}
