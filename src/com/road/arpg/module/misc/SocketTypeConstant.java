/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午4:51:56
 */
package com.road.arpg.module.misc;

import com.road.arpg.core.manager.socket.ISocketTypeConstant;

/**
 * socket 类型 常量接口
 * 
 * @author Dream.xie
 */
public interface SocketTypeConstant extends ISocketTypeConstant {

    /** 用户名登陆或者创建账户 */
    short LOGIN_OR_CREATE = 0x0000;
    /** 创建角色 */
    short CREATE_ROLE = 0x0001;

    /** 登陆成功 */
    short OUT_LOGIN_SUCCESS = 0x2000;
    /** 创建账户成功 */
    short OUT_CREATE_ACOUNT_SUCCESS = 0x2001;
    /** 创建角色成功 */
    short OUT_CREATE_ROLE_SUCCESS = 0x2002;
    /** 发送公共消息 */
    short OUT_COMMON_MESSAGE = 0x2003;
}
