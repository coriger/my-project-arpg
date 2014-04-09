/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.manager.socket.SocketUtil;
import com.road.arpg.core.util.LogUtil;

/**
 * Netty编码器-密文.
 * 
 * @author dream.xie
 * 
 */
final class NettyCiphertextStrictMessageEncoder extends
                MessageToByteEncoder<Message> {
    /**
     * 
     */
    NettyCiphertextStrictMessageEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message,
                    ByteBuf out) throws Exception {
        synchronized (ctx) {
            try {
                // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
                byte[] plainText = message.toByteBuffer().array();
                //获取key
                int[] encryptKey = getKey(ctx);
                //加密过程
                byte[] cipherText = SocketUtil.encoder(plainText, encryptKey);
                out.writeBytes(cipherText);
                //写入
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
    private int[] getKey(ChannelHandlerContext ctx) {
        int[] key = ctx.attr(NettyStrictCodecFactory.ENCRYPTION_KEY).get();
        return key;
    }
}