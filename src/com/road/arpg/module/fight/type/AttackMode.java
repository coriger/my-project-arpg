/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-11  下午3:55:46
 */
package com.road.arpg.module.fight.type;

/**
 * 攻击模式
 * 
 * @author shandong.su
 */
public enum AttackMode {
    /**
     * 普通攻击模式,只能打击怪物
     */
    NORAML(0),
    /**
     * 帮会攻击模式，攻击包括路人和怪物，不攻击帮会人员
     */
    GUILD(1),
    /**
     * 全体攻击模式，攻击又有人
     */
    ALL(2),
    /**
     * 组队攻击模式，攻击所有人，除了队友
     */
    TEAM(3);

    /**
         * 
         */
    private byte value;

    /**
     * 
     * @param value
     */
    private AttackMode(int value) {
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

    /**
     * 
     * @param value
     * @return
     */
    public static AttackMode prase(int value) {
        for (AttackMode type : AttackMode.values()) {
            if (value == type.getValue()) {
                return type;
            }
        }
        return NORAML;
    }

}
