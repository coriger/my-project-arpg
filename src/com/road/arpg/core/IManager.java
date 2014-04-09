/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:50:23
 */
package com.road.arpg.core;

/**
 * 管理器接口类，所有实现次类的Manager都要提供一个getInstance的静态方法来返回单例的对象。
 * 
 * @author Dream.xie
 */
public interface IManager {
    /**
     * 启动
     */
    void start() throws Exception;

    /**
     * 启动
     */
    void stop() throws Exception;
}
