/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.scene.info;

import java.util.Map;

/**
 * 场景信息,这部分的数据时不变化
 * 
 * @author shandong.su
 */
public class SceneInfo {
    /** 地图对应的数据库id */
    private int sceneID;
    /** 地图名 */
    private String name;
    /** 地图宽 */
    private int width;
    /** 地图高 */
    private int heigh;
    /** 谈话npc列表 */
    private Map<Integer, GameNPCInfo> talkNPCMap;
    /** 怪物npc列表 */
    private Map<Integer, GameNPCInfo> monsterMap;

    /**
     * @return the sceneID
     */
    public int getSceneID() {
        return sceneID;
    }

    /**
     * @return the talkNPCMap
     */
    public Map<Integer, GameNPCInfo> getTalkNPCMap() {
        return talkNPCMap;
    }

    /**
     * @param talkNPCMap
     *            the talkNPCMap to set
     */
    public void setTalkNPCMap(Map<Integer, GameNPCInfo> talkNPCMap) {
        this.talkNPCMap = talkNPCMap;
    }

    /**
     * @return the monsterMap
     */
    public Map<Integer, GameNPCInfo> getMonsterMap() {
        return monsterMap;
    }

    /**
     * @param monsterMap
     *            the monsterMap to set
     */
    public void setMonsterMap(Map<Integer, GameNPCInfo> monsterMap) {
        this.monsterMap = monsterMap;
    }

    /**
     * @param sceneID
     *            the sceneID to set
     */
    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width
     *            the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the heigh
     */
    public int getHeigh() {
        return heigh;
    }

    /**
     * @param heigh
     *            the heigh to set
     */
    public void setHeigh(int heigh) {
        this.heigh = heigh;
    }

    /**
     * 
     */
    public String toString() {
        return " sceneID:" + sceneID + " name:" + name + " \ntalkNPCList:"
                        + talkNPCMap + "\nmonsterList:" + monsterMap + "\n";
    }

}
