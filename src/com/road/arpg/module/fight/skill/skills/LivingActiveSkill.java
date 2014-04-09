/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-7  下午2:53:20
 */
package com.road.arpg.module.fight.skill.skills;

/**
 * 自由施放的技能
 * 
 * @author shandong.su
 */
public class LivingActiveSkill extends LivingSkill {

    @Override
    public void inReading(long tick) {
    }

    @Override
    public void inRelease(long tick) {
    }

    /**
     * 僵直时间(伤血后的剩余动画时间)
     * 
     */
    public void inRigidityTime(long tick) {
        if (startTime + skillBean.getReadTime() + skillBean.getRigidityTime() <= tick) {
            stop();
        }
    }

    @Override
    public void stop() {
        this.skillState = STATE_WAIT;
    }
}
