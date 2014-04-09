/**
 * Date: 2013-7-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.state;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.skills.LivingSkill;
import com.road.arpg.module.fight.util.Point;

/**
 * 技能僵直状态
 * 
 * @author yutao.chen
 */
public class SkillRigidityState implements ISkillState {

    @Override
    public void execute(LivingSkill skill, long tick) {
        skill.inRigidityTime(tick);
    }

    @Override
    public void startState(LivingSkill skill, long tick) {
    }

    @Override
    public void stopState(LivingSkill skill) {

    }

    @Override
    public void handleSkill(LivingSkill newKill, long delay, long tick,
                    Point pos, Living living) {

    }

    @Override
    public void handleStop(LivingSkill skill) {
        // 客户端移动先行
        skill.stop();
    }

}
