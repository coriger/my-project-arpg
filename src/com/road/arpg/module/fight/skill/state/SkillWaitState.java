/**
 * Date: 2013-7-10
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.state;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.skills.LivingSkill;
import com.road.arpg.module.fight.util.Point;

/**
 * 技能等待状态
 * 
 * @author yutao.chen
 */
public class SkillWaitState implements ISkillState {

    @Override
    public void execute(LivingSkill skill, long tick) {
    }

    @Override
    public void startState(LivingSkill skill, long tick) {

    }

    @Override
    public void stopState(LivingSkill skill) {

    }

    @Override
    public void handleStop(LivingSkill skill) {
        skill.stop();
    }

    @Override
    public void handleSkill(LivingSkill newKill, long delay, long tick,
                    Point pos, Living living) {
        newKill.start(tick, pos, living);
    }

}
