/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.SocketUtil;

/**
 * 游戏Socket处理
 * 
 * @author Dream.xie
 * 
 */
final class NettySocketHandler extends ChannelInboundHandlerAdapter {
    /**
     * 附件属性名字
     */
    private static final AttributeKey<Connection> ATTRIBUTE_CONNECTION = AttributeKey
                    .valueOf("ATTRIBUTE_CONNECTION");

    /**
     * 
     */
    NettySocketHandler() {
    }

    /**
     * 
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Connection connection = new NettyConnection(
                        (NioSocketChannel) ctx.channel());
        //存入连接，默认密钥
        ctx.attr(ATTRIBUTE_CONNECTION).set(connection);
        int[] key = SocketUtil.copyDefaultKey();
        ctx.attr(NettyStrictCodecFactory.DECRYPTION_KEY).set(key);
        ctx.attr(NettyStrictCodecFactory.ENCRYPTION_KEY).set(key);
    }

    /**
     * 
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message)
                    throws Exception {
        Connection connection = getConnection(ctx);
        connection.process(message);
    }

    /**
     * 
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
                    throws Exception {
    }

    /**
     * 
     */
    private Connection getConnection(ChannelHandlerContext ctx) {
        return (Connection) ctx.attr(ATTRIBUTE_CONNECTION).get();
    }

}
