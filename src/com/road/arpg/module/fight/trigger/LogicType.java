/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * @author : shandong.su
 * @version Ai结点之间的逻辑关系
 */
public enum LogicType {
    /**
     * 与
     */
    AND(0),

    /**
     * 或
     */
    OR(1),

    /**
     * 随机执行N个
     */
    RANDOM(2);

    /**
     * 
     */
    private byte value;

    /**
     * 
     * @param value
     */
    LogicType(int value) {
        this.value = (byte) value;
    }

    /**
     * 
     * @return
     */
    public byte getValue() {
        return value;
    }

    /**
     * 
     * @param value
     * @return
     */
    public static LogicType parse(int value) {
        LogicType result = AND;
        switch (value) {
            case 0:
                result = AND;
                break;
            case 1:
                result = OR;
                break;
            case 2:
                result = RANDOM;
                break;
            default:
                result = AND;
                break;
        }
        return result;
    }

    /**
     * 
     * @param value
     * @return
     */
    public static LogicType parse(String value) {
        LogicType result = AND;
        if (value == null || value.isEmpty()) {
            return result;
        }
        if (value.equals("or")) {
            result = OR;
        } else if (value.equals("random")) {
            result = RANDOM;
        } else {
            result = AND;
        }
        return result;
    }
}
