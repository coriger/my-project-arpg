/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * @author : shandong.su
 * @version
 * 
 */
public enum AiType {
    /**
     * 游戏AI即监听游戏事件，控制游戏流程
     */
    GAME(0),
    /**
     * 生物AI即监听生物事件，控制生物行为
     */
    LIVING(1);

    /**
     * 
     */
    private byte value;

    /**
     * 
     */
    private AiType(int value) {
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
    public static AiType parse(int value) {
        AiType result = LIVING;
        switch (value) {
            case 0:
                result = GAME;
                break;
            case 1:
                result = LIVING;
                break;
            default:
                break;
        }
        return result;
    }
}
