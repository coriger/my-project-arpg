/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.chat;

import com.road.arpg.core.annotation.Module;
import com.road.arpg.core.manager.module.ModuleManager.ModuleType;
import com.road.arpg.core.manager.module.NormalModule;

/**
 * 聊天模块
 * 
 * @author Dream.xie
 */
@Module(type = ModuleType.CHAT)
public class ChatModule extends NormalModule {

    /**
     * 聊天消息
     * 
     * @param msgType
     * @param content
     */
    public void chatMsg(int msgType, String content) {
    }
}
