/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.type;

/**
 * @author : shandong.su
 * @version 生物事件
 */
public enum LivingEventType {
    /**
     * 无事件
     */
    NULL(-1),
    /**
     * //移动
     */
    MOVING(1),
    /**
     * //攻击状态帧更新
     */
    UPDATE_ATTACK(2),
    /**
     * 生物被激活后，没有目标的行为，有目标的话，这个就不执行，
     */
    UPDATE_LAZZY(3),
    /**
     * //休息状态帧更新
     */
    UPDATE_REST(4),

    /**
     * 被攻击后
     */
    AFTER_BE_HURT(5),
    /**
     * //死亡
     */
    DEAD(7), ;
    /**
     * 
     */
    private byte value;

    /**
     * 
     * @param value
     */
    private LivingEventType(int value) {
        this.setValue(((byte) value));
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(byte value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public byte getValue() {
        return value;
    }

}
