/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.type;

/**
 * @author : shandong.su
 * @version 地图上物件的类型,只有战斗中才会存在
 */
public enum PhysicsType {
    /**
     * 玩家战士
     */
    HERO(1),
    /**
     * 物品类型
     */
    ITEMPHY(2),
    /**
     * 攻击效果，比如，火球，火墙
     */
    SKILLPHY(3),
    /**
     * 怪物类，生物的子类，由AI控制其逻辑
     */
    MONSTER(4),
    /**
     * npc类
     */
    GAMENPC(5), ;
    /**
     * 
     */
    private byte value;

    /**
     * 
     * @param value
     */
    private PhysicsType(int value) {
        this.value = (byte) value;
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
    public static PhysicsType parse(int value) {
        for (PhysicsType type : PhysicsType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return PhysicsType.MONSTER;
    }

}
