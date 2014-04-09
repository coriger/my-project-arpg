/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.arpg.module.fight.type;

/**
 * @author : cookie
 * @version 阵营类型(相对于当前玩家)
 */
public enum CampType {
    /**
     * // 蓝方阵营，战场中默认显示在右边
     */
    BLUE(-1),

    /**
     * // 红方阵营，战场中默认显示在左边
     */
    RED(1),

    /**
     * // 中立阵营
     */
    BLACK(2),
    /**
     * // 未知阵营
     */
    UNKNOWN(3),

    /**
     * // 无敌蓝方阵营(地刺)
     */
    INVICIBLE_BLUE(4),

    /**
     * ' // 无敌红方阵营(坐骑)
     */
    INVICIBLE_RED(5),

    /**
     * // 奖励阵营(木桶)
     */
    AWARD(6),

    /**
     * // 黄色阵营(漏洞)
     */
    YELLOW(7),

    /**
     * // 红方敌对阵营，蓝方中立阵营
     */
    RED_ENEMY_AND_BLUE_BLACK(8),

    ;

    /**
     * 
     */
    private byte value;

    /**
     * 
     * @param value
     */
    private CampType(int value) {
        this.value = (byte) value;
    }

    /**
     * 
     * @return
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * 
     * @param value
     * @return
     */
    public static CampType prase(int value) {
        for (CampType type : CampType.values()) {
            if (value == type.getValue()) {
                return type;
            }
        }
        return RED;
    }

    /**
     * 获得己方的主阵营
     * 
     * @param type
     * @return
     */
    public static CampType getMainCamp(CampType type) {
        switch (type) {
            case INVICIBLE_BLUE:
                return BLUE;
            case INVICIBLE_RED:
                return RED;
            default:
                return type;
        }
    }

    /**
     * 得取敌对的阵营(仅用于判断胜负)
     * 
     * @param type
     * @return
     */
    public static CampType getEnemyCamp(CampType type) {
        if (type == RED) {
            return BLUE;
        } else {
            return RED;
        }
    }
}
