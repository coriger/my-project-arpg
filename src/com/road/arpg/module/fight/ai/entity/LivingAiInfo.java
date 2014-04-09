/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.entity;

import java.util.Map;

import com.road.arpg.module.fight.type.LivingEventType;

/**
 * @author : shandong.su
 * @version 生物Ai配置
 */
public class LivingAiInfo {
    /**
     * 配置编号
     */
    private int configID;

    /**
     * 游戏触发器组，关注Living事件
     */
    private Map<LivingEventType, Map<Integer, String>> livingTriggers;

    /**
     * @return the configID
     */
    public int getConfigID() {
        return configID;
    }

    /**
     * @param configID
     *            the configID to set
     */
    public void setConfigID(int configID) {
        this.configID = configID;
    }

    /**
     * @return the livingTriggers
     */
    public Map<LivingEventType, Map<Integer, String>> getLivingTriggers() {
        return livingTriggers;
    }

    /**
     * @param livingTriggers
     *            the livingTriggers to set
     */
    public void setLivingTriggers(
                    Map<LivingEventType, Map<Integer, String>> livingTriggers) {
        this.livingTriggers = livingTriggers;
    }

    /**
     * 获取字符串
     */
    public String toString() {
        return " configID: " + this.configID + " groupTriggerMap:"
                        + this.livingTriggers + "\n";
    }
}
