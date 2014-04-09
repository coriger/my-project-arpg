/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:55:39
 */
package com.road.arpg.core.manager.socket.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.manager.socket.SocketAcceptor;

/**
 * MINA接收器.
 * 
 * @author Dream.xie
 */
public final class MinaSocketAcceptor extends SocketAcceptor {

    /**
     * 接收器
     */
    private NioSocketAcceptor ioAcceptor = null;

    /**
     * 启动socket
     */
    @Override
    protected void start() throws Exception {
        ioAcceptor = new NioSocketAcceptor(Runtime.getRuntime()
                        .availableProcessors() * 2 + 1);
        SocketSessionConfig config = ioAcceptor.getSessionConfig();
        config.setReceiveBufferSize(1024 * 64);
        config.setSendBufferSize(1024 * 4);
        config.setIdleTime(IdleStatus.BOTH_IDLE, 60 * 2);
        //5秒
        config.setSoLinger(5);
        config.setTcpNoDelay(true);
        ioAcceptor.getFilterChain().addLast(
                        "codec",
                        new ProtocolCodecFilter(new MinaStrictCodecFactory(
                                        GameServer.getInstance()
                                                        .getMiscConfig()
                                                        .getCiphertext())));
        ioAcceptor.getFilterChain()
                        .addLast("executor",
                                        new ExecutorFilter(
                                                        Executors.newFixedThreadPool(Runtime
                                                                        .getRuntime()
                                                                        .availableProcessors() * 4 + 1)));
        ioAcceptor.setHandler(new MinaSocketHandler());
        ioAcceptor.setReuseAddress(false);
        ioAcceptor.bind(new InetSocketAddress(GameServer.getInstance()
                        .getPort()));
    }

    /**
     * 停止
     */
    @Override
    protected void stop() throws Exception {
        ioAcceptor.unbind();
    }
}
