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
 * 自己
 * 
 * @author yutao.chen
 */
@TargetAnnotation(SelectTargetType = TargetType.SELF)
public class Self extends BaseTargetStrategy {

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.skill.targetStrategy.ITargetStrategy#getTarget(int,
     * int, int, int, int, com.road.dota.phy.object.Living, int, int)
     */
    @Override
    public List<Living> getTarget(int rangeX, int rangeY, int count,
                    Living living, int hurtRangeType, int x, int y) {
        // TODO Auto-generated method stub
        List<Living> targets = new ArrayList<>();
        targets.add(living);
        return targets;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.road.dota.skill.targetStrategy.ITargetStrategy#getTarget(com.road
     * .entity.bean.SkillBean, com.road.dota.phy.object.Living)
     */
    @Override
    public List<Living> getTarget(SkillBean skillBean, Living living) {
        List<Living> targets = new ArrayList<>();
        targets.add(living);
        return targets;
    }

    @Override
    protected List<Living> calTargets(List<Living> list, int count,
                    Living living) {
        return null;
    }
}
