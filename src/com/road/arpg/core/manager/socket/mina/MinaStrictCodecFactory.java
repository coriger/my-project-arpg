/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * mina 编码,解码器工厂.
 * 
 * @author Dream.xie
 */
public final class MinaStrictCodecFactory implements ProtocolCodecFactory {
    /**
     * 在session中用来存储加密字符串的key
     */
    static final String DECRYPTION_KEY = "DECRYPTION_KEY";
    /**
     * 在session中用来解密的字符串
     */
    static final String ENCRYPTION_KEY = "ENCRYPTION_KEY";
    /**
     * 编码器
     */
    private ProtocolEncoder encoder = null;
    /**
     * 解码器
     */
    private ProtocolDecoder decoder = null;

    /**
     * @param isCiphertext
     *            是否采用密文，通常情况都都是true,只有内部通信的时候为了性能采用false
     */
    public MinaStrictCodecFactory(Boolean isCiphertext) {
        if (isCiphertext) {
            encoder = new MinaCiphertextStrictMessageEncoder();
            decoder = new MinaCiphertextStrictMessageDecoder();
        } else {
            encoder = new MinaPlaintextStrictMessageEncoder();
            decoder = new MinaPlaintextStrictMessageDecoder();
        }
    }

    /**
     * 获得编码器
     */
    @Override
    public ProtocolEncoder getEncoder(IoSession paramIoSession)
                    throws Exception {
        return encoder;
    }

    /**
     * 获得解码器
     */
    @Override
    public ProtocolDecoder getDecoder(IoSession paramIoSession)
                    throws Exception {
        return decoder;
    }
}
