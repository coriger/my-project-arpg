/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.scene.info;

/**
 * 场景中的npc(包括怪物和任务谈话npc)
 * 
 * @author shandong.su
 */
public class GameNPCInfo {

    /** 地图中对象id，这个id指的是怪物在scene/*.xml中的mapObjID字段 */
    private int mapObjID;
    /** 生物的数据库id 即模板id */
    private int objectID;
    /** npc的x坐标 */
    private int posX;
    /** npc的y左边 */
    private int posY;
    /** npc的ai id */
    private int aiID;
    /** npc名字 */
    private String name;

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
     * @return the objectID
     */
    public int getObjectID() {
        return objectID;
    }

    /**
     * @param objectID
     *            the objectID to set
     */
    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX
     *            the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY
     *            the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return the aiID
     */
    public int getAiID() {
        return aiID;
    }

    /**
     * @param aiID
     *            the aiID to set
     */
    public void setAiID(int aiID) {
        this.aiID = aiID;
    }

    /**
     * 
     */
    public String toString() {
        return " name:" + name;
    }

    /**
     * @return the mapObjID
     */
    public int getMapObjID() {
        return mapObjID;
    }

    /**
     * @param mapObjID
     *            the mapObjID to set
     */
    public void setMapObjID(int mapObjID) {
        this.mapObjID = mapObjID;
    }
}
