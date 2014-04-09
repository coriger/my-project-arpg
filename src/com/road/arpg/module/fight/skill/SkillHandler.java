/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-7  上午11:26:51
 */
package com.road.arpg.module.fight.skill;

import java.util.HashMap;
import java.util.Map;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.skill.skills.LivingSkill;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.util.Point;
import com.road.arpg.module.fight.util.PointHelper;

/**
 * 技能handler
 * 
 * @author shandong.su
 */
public class SkillHandler {

    /**
     * 
     */
    private static final int MAX_NORMAL_ATTACK = 50;

    /**
     * 主动技能列表
     */
    private Map<Integer, LivingSkill> activeMap;

    /**
     * handler宿主
     */
    private Living living;

    /**
     * 当前使用的技能
     */
    private LivingSkill curSkill;

    /**
     * 技能公共冷却时间
     */
    private long publicCDTime;

    /**
     * 初始化
     */
    public SkillHandler(Living living) {
        activeMap = new HashMap<>();
        this.living = living;
    }

    /**
     * 每帧更新
     * 
     * @param tickTime
     */
    public void tick(long tickTime) {
        if (tickTime > this.publicCDTime + 1000) {
            living.setState(LivingState.RESTING);
        }
        if (this.curSkill != null) {
            this.curSkill.getSkillState().execute(this.curSkill, tickTime);
        }
    }

    /**
     * 施放技能
     * 
     * @param skillID
     *            技能id
     * @param tick
     *            服务器帧的更新时间
     * @param pos
     *            施放技能的目标点
     */
    public void useSkill(int skillID, long tick, Point pos) {
        this.living.setState(LivingState.ATTACKING);
        this.publicCDTime = tick;
        LivingSkill skill = activeMap.get(skillID);
        if (null == skill) {
            return;
        }
        //施放条件是否满足
        if (!canReleaseSkill(skill, pos, tick)) {
            return;
        }
        //如果有当前技能，如果是正要使用的话，那么跳出，如果不是，那么先看能停止不
        if (curSkill != null) {
            if (curSkill.getSkillBean().getSkillID() == skillID) {
                return;
            }
            this.curSkill.getSkillState().stopState(curSkill);
        }
        //只有当前技能为null，才能使用新的技能
        if (null == this.curSkill) {
            //主要锁定目标，或者将技能的施放地点固定
            skill.getSkillState().handleSkill(skill, 0, tick, pos, living);
        }
    }

    /**
     * 是否能施放技能
     */
    private boolean canReleaseSkill(LivingSkill skill, Point pos, long tick) {
        //校验公共cd是否满足，各种的cd施放满足
        if (publicCDTime > tick
                        || tick < skill.getStartTime()
                                        + skill.getSkillBean().getCoolTime()) {
            return false;
        }
        //由各个技能独自实现
        return skill.canRelease(tick, pos, null);
    }

    /**
     * 设置当前技能,有技能内部调用
     * 
     * @param skill
     */
    public void setCurSkill(LivingSkill skill) {
        this.curSkill = skill;
    }

    /**
     * 是否可以普通攻击
     * 
     * @return
     */
    public boolean canNormalAttack() {
        if (living.getStContext().getLockLiving() == null) {
            return false;
        }
        return PointHelper.distanceSquare(living.getPos(), living
                        .getStContext().getLockLiving().getPos()) <= MAX_NORMAL_ATTACK
                        * MAX_NORMAL_ATTACK;
    }

}
