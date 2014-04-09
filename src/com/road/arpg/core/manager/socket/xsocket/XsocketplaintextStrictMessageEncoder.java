/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.xsocket;

import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.manager.socket.Message;

/**
 * Xsocket加密器.线程安全单例类.
 * 
 * @author dream.xie
 * 
 */
final class XsocketplaintextStrictMessageEncoder extends
                XsocketStrictCodecFactory.XsocketAbstractEncoder {
    /**
     */
    XsocketplaintextStrictMessageEncoder() {

    }

    /**
     * 编码
     */
    byte[] doEncode(INonBlockingConnection session, Message message)
                    throws Exception {
        return message.toByteBuffer().array();

    }

}
