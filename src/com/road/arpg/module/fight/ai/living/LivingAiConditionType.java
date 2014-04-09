/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.living;

/**
 * @author : shandong.su
 * @version 生物条件编号
 */
public enum LivingAiConditionType {
    /**
     * 被攻击之后，锁定目标，死磕，如果能攻击就攻击，不能攻击就追击,
     */
    BE_HURT_LOCK_ENEMY(1),
    /**
     * 被攻击之后改变目标
     */
    BE_HURT_CHANGE_LOCK_ENEMY(2),
    /**
     * 攻击范围内是否有敌人，有就攻击
     */
    ATTACK_RANK_ANY_ENEMY(3),
    /**
     * 范围内时候有敌人
     */
    CAN_ATTACK_LOCK_TARGET(4);
    /**
     * 值
     */
    private int value;

    /**
     * @param value
     */
    private LivingAiConditionType(int value) {
        this.value = value;
    }

    /**
     * 
     * @return
     */
    public int getValue() {
        return value;
    }
}
