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
import com.road.arpg.core.util.LogUtil;

/**
 * Netty编码器-明文.
 * 
 * @author dream.xie
 * 
 */
final class NettyPlaintextStrictMessageEncoder extends
                MessageToByteEncoder<Message> {
    /**
     * 
     */
    NettyPlaintextStrictMessageEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message,
                    ByteBuf out) throws Exception {
        try {
            // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
            byte[] plainText = message.toByteBuffer().array();
            out.writeBytes(plainText);
            //写入
        } catch (Exception ex) {
            LogUtil.error("catch error for encoding packet:", ex);
            throw ex;
        }

    }
}