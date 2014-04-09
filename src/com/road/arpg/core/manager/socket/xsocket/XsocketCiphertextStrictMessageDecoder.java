/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.io.IOException;
import java.util.Map;

import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.manager.socket.SocketUtil;
import com.road.arpg.core.util.LogUtil;

/**
 * Xsocket解密器.线程安全单例类.
 * 
 * @author dream.xie
 * 
 */
@SuppressWarnings("unchecked")
final class XsocketCiphertextStrictMessageDecoder extends
                XsocketStrictCodecFactory.XsocketAbstractDecoder {
    /**
     * 
     */
    XsocketCiphertextStrictMessageDecoder() {

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

        int header = 0;
        int packetLength = 0;
        int[] decryptKey = getKey(session);
        int cipherByte1 = 0 , cipherByte2 = 0;

        // 此处4字节头部的解码使用直接解码形式，规避频繁的对象创建
        int plainByte1 , plainByte2;
        int key;

        // 解密两字节header
        cipherByte1 = session.readByte() & 0xff;
        key = decryptKey[0];
        plainByte1 = (cipherByte1 ^ key) & 0xff;

        cipherByte2 = session.readByte() & 0xff;
        key = ((decryptKey[1] ^ cipherByte1));
        plainByte2 = ((cipherByte2 - cipherByte1) ^ key) & 0xff;

        header = ((plainByte1 << 8) + plainByte2);
        // 两字节length,没有加密
        packetLength = session.readShort();
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
        data = SocketUtil.decrypt(data, decryptKey);
        Message packet = Message.build(data);
        if (packet != null) {
            return packet;
        }
        return null;
    }

    /**
     * 获取当前加解密密钥
     * 
     * @param session
     * @return
     */
    private int[] getKey(INonBlockingConnection session) {
        Map<String, Object> attachments = (Map<String, Object>) session
                        .getAttachment();
        int[] key = (int[]) attachments
                        .get(XsocketStrictCodecFactory.DECRYPTION_KEY);
        return key;
    }
}
