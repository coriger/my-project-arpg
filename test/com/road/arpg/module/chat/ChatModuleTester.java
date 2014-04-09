/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-26  上午11:14:47
 */
package com.road.arpg.module.chat;

import org.apache.mina.core.session.IoSession;
import org.junit.Test;

import com.road.arpg.BaseTester;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.module.chat.protobuff.ChatProto.ChatProtoMsg;

/**
 * 
 * 聊天测试
 * 
 * @author shandong.su
 */
public final class ChatModuleTester extends BaseTester {

    /**
     * @param args
     */
    @Test
    public void test() throws Exception {
        IoSession session = createSession();
        Message message = new Message((short) 17);
        ChatProtoMsg.Builder chatProto = ChatProtoMsg.newBuilder();
        chatProto.setType(1);
        String str = "我是一串超长的加密字符串啦，可能不够长哦，那我再加点长度吧，反正只是错测试，我总觉得吧，还是不够长，感觉快了，但是为了保险为了科学再长点吧";
        str += str + str + str;
        chatProto.setContent(str);
        message.setBody(chatProto.build().toByteArray());
        session.write(message);
        session.write(message);
    }

}
