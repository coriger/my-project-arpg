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
 * 技能释放状态
 * 
 * @author yutao.chen
 */
public class SkillInReleaseState implements ISkillState {

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.skill.state.ISkillState#execute(com.road.dota.skill.
     * BaseActiveSkill, com.road.dota.BaseGame, long)
     */
    @Override
    public void execute(LivingSkill skill, long tick) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.road.dota.skill.state.ISkillState#handleStop(com.road.dota.skill.
     * BaseActiveSkill)
     */
    @Override
    public void handleStop(LivingSkill skill) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.road.dota.skill.state.ISkillState#startState(com.road.dota.skill.
     * BaseActiveSkill)
     */
    @Override
    public void startState(LivingSkill skill, long tick) {
        // TODO Auto-generated method stub
        skill.inRelease(tick);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.skill.state.ISkillState#stopState(com.road.dota.skill.
     * BaseActiveSkill)
     */
    @Override
    public void stopState(LivingSkill skill) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.road.dota.skill.state.ISkillState#handleSkill(com.road.dota.skill
     * .BaseActiveSkill, long, long)
     */
    @Override
    public void handleSkill(LivingSkill newKill, long startTime, long tick,
                    Point pos, Living living) {
        // TODO Auto-generated method stub

    }

}
