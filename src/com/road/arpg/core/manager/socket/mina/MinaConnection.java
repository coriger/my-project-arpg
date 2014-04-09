/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:21:57
 */
package com.road.arpg.core.manager.socket.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.session.IoSession;

import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.Message;

/**
 * Connection的mima实现
 * 
 * @author shandong.su
 */
final class MinaConnection extends Connection {
    /**
     * nio抽象连接
     */
    private IoSession session;

    /**
     * @param handler
     */
    MinaConnection(IoSession session) {
        this.session = session;
        InetSocketAddress socketAddr = (InetSocketAddress) session
                        .getRemoteAddress();
        this.setHostname(socketAddr.getHostName());
        this.setPort(socketAddr.getPort());
    }

    @Override
    public void writeMessage(Message message) {
        session.write(message);
    }

    /**
     * 关闭连接
     */
    @Override
    public void closeConnection() {
        //并不立即关闭
        session.close(false);
    }
}
