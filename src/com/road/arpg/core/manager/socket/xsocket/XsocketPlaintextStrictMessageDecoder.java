/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.io.IOException;

import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.util.LogUtil;

/**
 * Xsocket解密器.线程安全单例类.
 * 
 * @author dream.xie
 * 
 */
final class XsocketPlaintextStrictMessageDecoder extends
                XsocketStrictCodecFactory.XsocketAbstractDecoder {
    /**
     * 
     */
    XsocketPlaintextStrictMessageDecoder() {

    }

    /**
     * 解码
     */
    Message doDecode(INonBlockingConnection session) throws IOException {
        if (session.available() < 4) {
            return null;
        }
        //记录一下当前的位置
        session.markReadPosition();

        int header = session.readShort();
        int packetLength = session.readShort();

        // 预解密长度信息成功，回溯位置
        session.resetToReadMark();

        //如果不是标识头，发送给客户端说，断开连接
        if (header != Message.HEADER) {
            try {
                session.close();
            } catch (IOException e) {
                LogUtil.error("关闭xsocket错误", e);
            }
        }

        if (session.available() < packetLength) {
            // 数据长度不足，等待下次接收
            return null;
        }

        // 读取数据并解密数据
        byte[] data = session.readBytesByLength(packetLength);
        Message packet = Message.build(data);
        if (packet != null) {
            return packet;
        }
        return null;
    }
}
