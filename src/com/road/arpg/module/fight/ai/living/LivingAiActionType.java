/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.living;

/**
 * @author : shandong.su
 * @version 生物动作类型
 */
public enum LivingAiActionType {
    /**
     * 自动移动
     */
    AUTO_MOVE(1),
    /**
     * 攻击锁定目标
     */
    ATTACK_LOCK_TARGET(2),
    /**
     * 追击目标
     */
    FOLLOW_TARGET(3),
    /**
     * 
     */
    LIVING_EXECUTE_TRIGGER(11);

    /**
     * 值
     */
    private int value;

    /**
     * 创建动作类型
     * 
     * @param value
     */
    private LivingAiActionType(int value) {
        this.value = value;
    }

    /**
     * 获取值
     * 
     * @return
     */
    public int getValue() {
        return value;
    }
}
