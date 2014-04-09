/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.misc.entity;

import java.util.Date;

import com.road.arpg.core.manager.database.BaseEntity;

/**
 * 
 * @author shandong.su
 */
public class AcountEntity extends BaseEntity<Long> {
    /**
     * 玩家编号
     */
    private long playerID;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆站点
     */
    private String site;

    /**
     * 在线(0:否 1:是)
     */
    private int onLine;

    /**
     * 封号结束时间
     */
    private Date banLoginEndTime;

    /**
     * 禁言结束时间
     */
    private Date banChatEndTime;

    /**
     * 类型(1:玩家 2.GM 3.引导员 4.内部账号 5.机器人)
     */
    private int type;

    /**
     * 广告渠道类型
     */
    private String adType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 玩家编号
     */
    public long getPlayerID() {
        return playerID;
    }

    /**
     * 玩家编号
     */
    public void setPlayerID(long playerID) {
        if (playerID != this.playerID) {
            this.playerID = playerID;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        if (userName != null && !userName.equals(this.userName)) {
            this.userName = userName;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 登陆站点
     */
    public String getSite() {
        return site;
    }

    /**
     * 登陆站点
     */
    public void setSite(String site) {
        if (site != null && !site.equals(this.site)) {
            this.site = site;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 在线(0:否 1:是)
     */
    public int getOnLine() {
        return onLine;
    }

    /**
     * 在线(0:否 1:是)
     */
    public void setOnLine(int onLine) {
        if (onLine != this.onLine) {
            this.onLine = onLine;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 封号结束时间
     */
    public Date getBanLoginEndTime() {
        return banLoginEndTime;
    }

    /**
     * 封号结束时间
     */
    public void setBanLoginEndTime(Date banLoginEndTime) {
        if (banLoginEndTime != null
                        && !banLoginEndTime.equals(this.banLoginEndTime)) {
            this.banLoginEndTime = banLoginEndTime;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 禁言结束时间
     */
    public Date getBanChatEndTime() {
        return banChatEndTime;
    }

    /**
     * 禁言结束时间
     */
    public void setBanChatEndTime(Date banChatEndTime) {
        if (banChatEndTime != null
                        && !banChatEndTime.equals(this.banChatEndTime)) {
            this.banChatEndTime = banChatEndTime;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 类型(1:玩家 2.GM 3.引导员 4.内部账号 5.机器人)
     */
    public int getType() {
        return type;
    }

    /**
     * 类型(1:玩家 2.GM 3.引导员 4.内部账号 5.机器人)
     */
    public void setType(int type) {
        if (type != this.type) {
            this.type = type;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 广告渠道类型
     */
    public String getAdType() {
        return adType;
    }

    /**
     * 广告渠道类型
     */
    public void setAdType(String adType) {
        if (adType != null && !adType.equals(this.adType)) {
            this.adType = adType;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null && !createTime.equals(this.createTime)) {
            this.createTime = createTime;
            setOperation(EntityOperation.UPDATE);
        }
    }

    /**
     * 上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        if (lastLoginTime != null && !lastLoginTime.equals(this.lastLoginTime)) {
            this.lastLoginTime = lastLoginTime;
            setOperation(EntityOperation.UPDATE);
        }
    }

}