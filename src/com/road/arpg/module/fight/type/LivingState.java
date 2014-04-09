/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.arpg.module.fight.type;

/**
 * @author : cookie
 * @version 战斗中生物的状态
 */
public enum LivingState {
    /**
     * // 休息状态
     */
    RESTING(0),
    /**
     * // 移动状态
     */
    MOVING(1),
    /**
     * // 攻击状态
     */
    ATTACKING(2),
    /**
     * // 死亡状态
     */
    DEAD(3),
    /**
     * //非激活状态
     */
    INACTIVE(4),
    /**
     * 返回出生点
     */
    RETURNBORN(5);

    /**
     * 
     */
    private byte value;

    /**
     * 
     */
    private LivingState(int value) {
        this.value = (byte) value;
    }

    /**
     * 
     * @return
     */
    public byte getValue() {
        return this.value;
    }
}
