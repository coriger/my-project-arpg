/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午8:35:52
 */
package com.road.arpg.core.manager.module;

/**
 * 模块抽象类.
 * 
 * @author Dream.xie
 */
interface IModule {

    /**
     * 开始
     */
    void start() throws Exception;

    /**
     * 停止
     */
    void stop() throws Exception;

}
