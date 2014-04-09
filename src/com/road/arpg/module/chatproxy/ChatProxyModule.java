/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.chatproxy;

import com.road.arpg.core.manager.module.AbstractProxyModule;

/**
 * 聊天代理模块，当聊天当作单独的服务器部署到一台物理机上面的时候，需要使用这个。
 * 
 * @author Dream.xie
 */
//@Module(type = ModuleType.CHAT_PROXY, proxyModuleCfgXpath = "//modules/chatModule")
public class ChatProxyModule extends AbstractProxyModule {

    /**
     * 聊天消息
     * 
     * @param msgType
     * @param content
     */
    public void chatMsg(int msgType, String content) {
    }
}
