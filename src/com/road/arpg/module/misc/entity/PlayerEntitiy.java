/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  上午10:43:14
 */
package com.road.arpg.module.misc.entity;

import com.road.arpg.core.manager.database.BaseEntity;
import com.road.arpg.core.manager.socket.Connection;

/**
 * 游戏玩家
 * 
 * @author Dream.xie
 */
public class PlayerEntitiy extends BaseEntity<Long> {

    /**
     * 连接
     */
    private Connection conn;
    /**
     * 用户名
     */
    private String username;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn
     *            the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
