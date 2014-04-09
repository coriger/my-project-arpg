/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.manager.socket.SocketUtil;
import com.road.arpg.core.util.LogUtil;

/**
 * Xsocket加密器.线程安全单例类.
 * 
 * @author dream.xie
 * 
 */
@SuppressWarnings("unchecked")
final class XsocketCiphertextStrictMessageEncoder extends
                XsocketStrictCodecFactory.XsocketAbstractEncoder {

    /**
     * 
     */
    XsocketCiphertextStrictMessageEncoder() {

    }

    /**
     * 编码
     */
    byte[] doEncode(INonBlockingConnection session, Message message)
                    throws Exception {
        synchronized (session) {
            try {
                // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
                byte[] plainText = message.toByteBuffer().array();

                int length = plainText.length;
                IoBuffer cipherBuffer = IoBuffer.allocate(length);

                //获取key
                int[] encryptKey = getKey(session);

                //加密过程
                byte[] cipherText = SocketUtil.encoder(plainText, encryptKey);

                cipherBuffer.put(cipherText);

                cipherBuffer.flip();

                return cipherBuffer.array();
            } catch (Exception ex) {
                LogUtil.error("catch error for encoding packet:", ex);
                throw ex;
            }
        }
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
                        .get(XsocketStrictCodecFactory.ENCRYPTION_KEY);
        return key;
    }

}
