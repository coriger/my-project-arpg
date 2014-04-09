/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.io.IOException;

import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.manager.socket.Message;

/**
 * Netty 编码,解码器工厂.
 * 
 * @author Dream.xie
 */
final class XsocketStrictCodecFactory {
    /**
     * 在session中用来存储加密字符串的key
     */
    static final String DECRYPTION_KEY = "DECRYPTION_KEY";
    /**
     * 在session中用来解密的字符串
     */
    static final String ENCRYPTION_KEY = "ENCRYPTION_KEY";

    /**
     * 
     */
    private XsocketStrictCodecFactory() {

    }

    /**
     * 获得编码器
     */
    static XsocketAbstractEncoder getEncoder(Boolean isCiphertext) {
        XsocketAbstractEncoder encoder = null;
        if (GameServer.getInstance().getMiscConfig().getCiphertext()) {
            encoder = new XsocketCiphertextStrictMessageEncoder();
        } else {
            encoder = new XsocketplaintextStrictMessageEncoder();
        }
        return encoder;
    }

    /**
     * 获得解码器
     */
    static XsocketAbstractDecoder getDecoder(Boolean isCiphertext) {
        XsocketAbstractDecoder decoder = null;
        if (GameServer.getInstance().getMiscConfig().getCiphertext()) {
            decoder = new XsocketCiphertextStrictMessageDecoder();
        } else {
            decoder = new XsocketPlaintextStrictMessageDecoder();
        }
        return decoder;
    }

    /**
     * 抽象解码器
     * 
     * @author Dream.xie
     */
    abstract static class XsocketAbstractDecoder {
        /**
         * 解码
         */
        abstract Message doDecode(INonBlockingConnection session)
                        throws IOException;
    }

    /**
     * 抽象的编码器.
     * 
     * @author Dream.xie
     */
    abstract static class XsocketAbstractEncoder {
        /**
         * 编码
         */
        abstract byte[] doEncode(INonBlockingConnection session, Message message)
                        throws Exception;
    }

}
