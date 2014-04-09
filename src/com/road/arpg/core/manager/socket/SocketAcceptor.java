/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-7  下午4:59:09
 */
package com.road.arpg.core.manager.socket;

/**
 * socket接收器
 * 
 * @author Dream.xie
 */
public abstract class SocketAcceptor {
    /**
     * 
     */
    protected SocketAcceptor() {
    }

    /**
     * 停止
     */
    protected abstract void stop() throws Exception;

    /**
     * 各自的SOCKET框架负责 实现startSocket这个方法
     */
    protected abstract void start() throws Exception;

}
