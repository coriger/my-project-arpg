/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.manager.socket.SocketUtil;
import com.road.arpg.core.util.LogUtil;

/**
 * Netty解码器-密文.
 * 
 * @author dream.xie
 * 
 */
final class NettyCiphertextStrictMessageDecoder extends ByteToMessageDecoder {

    /**
     * 
     */
    NettyCiphertextStrictMessageDecoder() {

    }

    /**
     * 解密
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                    List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }

        int header = 0;
        int packetLength = 0;
        int[] decryptKey = getKey(ctx);
        int cipherByte1 = 0 , cipherByte2 = 0;

        // 此处4字节头部的解码使用直接解码形式，规避频繁的对象创建
        int plainByte1 , plainByte2;
        int key;

        // 解密两字节header
        cipherByte1 = in.readByte() & 0xff;
        key = decryptKey[0];
        plainByte1 = (cipherByte1 ^ key) & 0xff;

        cipherByte2 = in.readByte() & 0xff;
        key = ((decryptKey[1] ^ cipherByte1));
        plainByte2 = ((cipherByte2 - cipherByte1) ^ key) & 0xff;

        header = ((plainByte1 << 8) + plainByte2);
        // 两字节length,没有加密
        packetLength = in.readShort();
        // 预解密长度信息成功，回溯位置
        in.readerIndex(in.readerIndex() - 4);
        //如果不是标识头，发送给客户端说，断开连接
        if (header != Message.HEADER || packetLength < Message.HEAD_SIZE) {
            // 数据包长度错误，断开连接
            LogUtil.error(String
                            .format("error packet length: packetlength=%d Packet.HDR_SIZE=%d",
                                            packetLength, Message.HEAD_SIZE));
            LogUtil.error(String.format("Disconnect the client:%s",
                            ((InetSocketAddress) ctx.channel().remoteAddress())
                                            .getHostName()));
            ctx.channel().close();
            return;
        }

        if (in.readableBytes() < packetLength) {
            // 数据长度不足，等待下次接收
            return;
        }

        // 读取数据并解密数据
        byte[] data = new byte[packetLength];
        in.getBytes(in.readerIndex(), data, 0, packetLength);
        data = SocketUtil.decrypt(data, decryptKey);
        in.readerIndex(in.readerIndex() + packetLength);
        Message packet = Message.build(data);
        if (packet != null) {
            out.add(packet);
        }
        return;
    }

    /**
     * 获取当前加解密密钥
     * 
     * @param session
     * @return
     */
    private int[] getKey(ChannelHandlerContext ctx) {
        int[] key = ctx.attr(NettyStrictCodecFactory.DECRYPTION_KEY).get();
        return key;
    }
}