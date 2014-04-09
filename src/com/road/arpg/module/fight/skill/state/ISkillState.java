/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-7  下午2:13:37
 */
package com.road.arpg.module.fight.skill.state;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.skills.LivingSkill;
import com.road.arpg.module.fight.util.Point;

/**
 * 技能状态机基类
 * 
 * @author shandong.su
 */
public interface ISkillState {
    /**
     * 技能处理
     * 
     * @param game
     * @param tick
     */
    void execute(LivingSkill skill, long tick);

    /**
     * 状态初始化
     * 
     * @param skill
     * @param tick
     */
    void startState(LivingSkill skill, long tick);

    /**
     * 结束该状态
     * 
     * @param skill
     */
    void stopState(LivingSkill skill);

    /**
     * 收到使用技能
     * 
     * @param living
     *            TODO
     * 
     */
    void handleSkill(LivingSkill newKill, long delay, long tick, Point pos,
                    Living living);

    /**
     * 停止当前状态，即无论是什么状态，直接恢复成休息状态
     * 
     * @param host
     */
    void handleStop(LivingSkill skill);

}
