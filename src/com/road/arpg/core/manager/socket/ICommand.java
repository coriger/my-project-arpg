/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午8:39:31
 */
package com.road.arpg.core.manager.socket;

/**
 * Command接口
 * 
 * @author Dream.xie
 */
public interface ICommand {

    /**
     * command的具体执行方法
     * 
     * @param conn
     * @param msg
     * @throws Exception
     */
    void excute(Connection conn, Message msg) throws Exception;

}
