/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-7  下午12:01:08
 */
package com.road.arpg.module.fight.skill.skills;

import com.road.arpg.module.fight.bean.SkillBean;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.state.ISkillState;
import com.road.arpg.module.fight.skill.state.SkillInReleaseState;
import com.road.arpg.module.fight.skill.state.SkillReadState;
import com.road.arpg.module.fight.skill.state.SkillRigidityState;
import com.road.arpg.module.fight.skill.state.SkillWaitState;
import com.road.arpg.module.fight.skill.targetStategy.BaseTargetStrategy;
import com.road.arpg.module.fight.util.Point;
import com.road.arpg.module.fight.util.PointHelper;

/**
 * 技能的基础类
 * 
 * @author shandong.su
 */
public abstract class LivingSkill {

    /** 技能等待状态 */
    public static final ISkillState STATE_WAIT = new SkillWaitState();

    /** 技能吟唱 */
    public static final ISkillState STATE_READ = new SkillReadState();

    /** 技能释放状态 */
    public static final ISkillState STATE_INRELEASE = new SkillInReleaseState();

    /** 技能僵直时间 */
    public static final ISkillState STATE_RIGIDITY = new SkillRigidityState();

    /**
     * 技能当前状态
     */
    protected ISkillState skillState = STATE_WAIT;
    /**
     * 技能模板
     */
    protected SkillBean skillBean;

    /**
     * 上次施放时间
     */
    protected long startTime;

    /**
     * 施放目标点
     */
    protected Point point;

    /**
     * 目标生物
     */
    protected Living targetLiving;

    /**
     * 选择目标策略
     */
    private BaseTargetStrategy targetStrategy;

    /**
     * 能否施放，主要涉及对点施放，对目标施放，对目标施放有分为，对自己施放，对锁定单位施放(包括友方，敌人)
     * 
     * @param tick
     * @param pos
     *            TODO
     * @param living
     *            TODO
     * @return
     */
    public boolean canRelease(long tick, Point pos, Living living) {
        switch (skillBean.getCanUseType()) {
            case 0:
                if (pos != null
                                && PointHelper.distance(living.getPos(), pos) > this.skillBean
                                                .getReleaseRang()) {
                    return true;
                }
                return false;
            case 1: //友方单位
                if (living.getStContext().getLockLiving() != null
                                && living.isFriendly(living.getStContext()
                                                .getLockLiving())) {
                    return true;
                }
                return false;
            case 2: //敌方单位
                if (living.getStContext().getLockLiving() != null
                                && !living.isFriendly(living.getStContext()
                                                .getLockLiving())) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * 开始施放,这一步与上一步的校验是单线程的，所以不用担心上目标不对的问题,这个方法不建议重写，先写成final
     * 
     * @param tick
     * @param living
     *            TODO
     */
    public final void start(long tick, Point pos, Living living) {
        if (skillBean.getCanUseType() == 0) {
            this.point = pos;
        } else {
            this.targetLiving = living.getStContext().getLockLiving();
        }
    }

    /**
     * 
     * @param tick
     */
    public abstract void inReading(long tick);

    /**
     * 
     * @param tick
     */
    public abstract void inRelease(long tick);

    /**
     * 僵直时间(伤血后的剩余动画时间)
     * 
     */
    public abstract void inRigidityTime(long tick);

    /**
     * 
     */
    public abstract void stop();

    /**
     * @return the skillBean
     */
    public SkillBean getSkillBean() {
        return skillBean;
    }

    /**
     * @param skillBean
     *            the skillBean to set
     */
    public void setSkillBean(SkillBean skillBean) {
        this.skillBean = skillBean;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取当前技能的状态
     * 
     * @return
     */
    public final ISkillState getSkillState() {
        return this.skillState;
    }

}
