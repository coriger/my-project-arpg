/**
 * Date: 2013-8-15
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.ArrayList;
import java.util.List;

import com.road.arpg.module.fight.bean.SkillBean;
import com.road.arpg.module.fight.objects.Living;

/**
 * 无目标
 * 
 * @author yutao.chen
 */
public class NoTarget extends BaseTargetStrategy {
    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.skill.targetStrategy.ITargetStrategy#getTarget(int,
     * int, int, int, int, com.road.dota.phy.object.Living, int, int)
     */
    @Override
    public List<Living> getTarget(int rangeX, int rangeY, int count,
                    Living living, int hurtRangeType, int x, int y) {
        return new ArrayList<>();
    }

    @Override
    public List<Living> getTarget(SkillBean skillBean, Living living) {
        return new ArrayList<>();
    }

    @Override
    protected List<Living> calTargets(List<Living> list, int count,
                    Living living) {
        return new ArrayList<>();
    }
}
