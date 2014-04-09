/**
 * Date: 2013-7-9
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.state;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.skills.LivingSkill;
import com.road.arpg.module.fight.util.Point;

/**
 * 技能读条状态 （包括吟唱和抬手时间）
 * 
 * @author yutao.chen
 */
public class SkillReadState implements ISkillState {

    @Override
    public void execute(LivingSkill skill, long tick) {
        skill.inReading(tick);
    }

    @Override
    public void handleStop(LivingSkill skill) {
        skill.stop();
    }

    @Override
    public void startState(LivingSkill skill, long tick) {
        skill.inReading(tick);
    }

    @Override
    public void stopState(LivingSkill skill) {
    }

    @Override
    public void handleSkill(LivingSkill newKill, long startTime, long tick,
                    Point pos, Living living) {
    }

}
