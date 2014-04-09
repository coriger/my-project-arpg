/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:55:39
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.xsocket.WorkerPool;
import org.xsocket.connection.IConnection;
import org.xsocket.connection.IConnection.FlushMode;
import org.xsocket.connection.IServer;
import org.xsocket.connection.Server;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.manager.socket.SocketAcceptor;

/**
 * Xsocket接收器.
 * 
 * @author Dream.xie
 */
public final class XsocketSocketAcceptor extends SocketAcceptor {
    /**
     * 接收器
     */
    private IServer server = null;

    /**
     * 
     */
    @Override
    protected void start() throws Exception {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(IConnection.SO_REUSEADDR, true);
        config.put(IConnection.SO_RCVBUF, 1024 * 64);
        server = new Server(GameServer.getInstance().getPort(), config,
                        new XsocketSocketHandler());
        server.setAutoflush(false);
        server.setFlushmode(FlushMode.ASYNC);
        WorkerPool workerPool = new WorkerPool(1, 2, 100,
                        TimeUnit.MILLISECONDS, 20000, false);
        // 预启动所有核心线程
        workerPool.prestartAllCoreThreads();
        server.setWorkerpool(workerPool);
        server.start();
    }

    /**
     * 停止
     */
    @Override
    protected void stop() throws Exception {
        server.close();
    }
}
