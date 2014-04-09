/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午4:51:56
 */
package com.road.arpg.module.chat.command;

import com.road.arpg.core.annotation.SocketCommand;
import com.road.arpg.core.manager.module.ModuleManager;
import com.road.arpg.core.manager.module.ModuleManager.ModuleType;
import com.road.arpg.core.manager.socket.ICommand;
import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.module.chat.ChatModule;
import com.road.arpg.module.chat.SocketTypeConstant;
import com.road.arpg.module.chat.protobuff.ChatProto.ChatProtoMsg;

/**
 * 发送消息
 * 
 * @author Dream.xie
 */
@SocketCommand(type = SocketTypeConstant.SEND_MSG_CMD)
public class SendMsgCMD implements ICommand {

    @Override
    public void excute(Connection conn, Message msg) throws Exception {
        ChatProtoMsg chatProto = ChatProtoMsg.parseFrom(msg.getBody());
        String content = chatProto.getContent();

        System.out.println("content:"
                        + new String(content.getBytes("UTF-8"), "UTF-8"));
        ChatModule chatModule = ModuleManager.getInstance().getModule(
                        ModuleType.CHAT);
        System.out.println(chatModule);
    }

}