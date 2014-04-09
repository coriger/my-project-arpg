/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-13  下午12:20:56
 */
package com.road.arpg.module.fight.bean;

/**
 * @author shandong.su
 */
public class MonsterBean {

    /**
     * 怪物编号
     */
    private int monsterID;

    /**
     * 视野范围
     */
    private int eyeRang;

    /**
     * @return the monsterID
     */
    public int getMonsterID() {
        return monsterID;
    }

    /**
     * @param monsterID
     *            the monsterID to set
     */
    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    /**
     * @return the eyeRang
     */
    public int getEyeRang() {
        return eyeRang;
    }

    /**
     * @param eyeRang
     *            the eyeRang to set
     */
    public void setEyeRang(int eyeRang) {
        this.eyeRang = eyeRang;
    }

}
