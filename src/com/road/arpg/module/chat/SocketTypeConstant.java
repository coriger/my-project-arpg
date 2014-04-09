/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午4:51:56
 */
package com.road.arpg.module.chat;

import com.road.arpg.core.manager.socket.ISocketTypeConstant;

/**
 * socket 类型 常量接口
 * 
 * @author Dream.xie
 */
public interface SocketTypeConstant extends ISocketTypeConstant {

    /** 聊天 */
    short SEND_MSG_CMD = 0x0011;

}
