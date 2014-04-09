/**
 * Date: 2013-12-2
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.ArrayList;
import java.util.List;

import com.road.arpg.module.fight.bean.SkillBean;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.targetStategy.TargetStrategyFactory.TargetType;

/**
 * 获取锁定目标
 * 
 * @author shandong.su
 */
@TargetAnnotation(SelectTargetType = TargetType.LOCK_TARGET)
public class LockLiving extends BaseTargetStrategy {

    @Override
    public List<Living> getTarget(int rangeX, int rangeY, int count,
                    Living living, int hurtRangeType, int x, int y) {
        List<Living> targets = new ArrayList<>();
        if (living.getStContext().getLockLiving() != null) {
            targets.add(living.getStContext().getLockLiving());
        }
        return targets;
    }

    @Override
    public List<Living> getTarget(SkillBean skillBean, Living living) {
        List<Living> targets = new ArrayList<>();
        if (living.getStContext().getLockLiving() != null) {
            targets.add(living.getStContext().getLockLiving());
        }
        return targets;
    }

    @Override
    public List<Living> calTargets(List<Living> list, int count, Living living) {
        return null;
    }
}
