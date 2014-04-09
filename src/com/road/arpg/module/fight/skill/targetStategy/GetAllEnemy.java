/**
 * Date: 2013-8-13
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.List;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.targetStategy.TargetStrategyFactory.TargetType;

/**
 * 获得所有敌人，不限范围
 * 
 * @author yutao.chen
 */
@TargetAnnotation(SelectTargetType = TargetType.ALL_TARGET)
public class GetAllEnemy extends BaseTargetStrategy {

    @Override
    protected List<Living> calTargets(List<Living> list, int count,
                    Living living) {
        return list;
    }

}
