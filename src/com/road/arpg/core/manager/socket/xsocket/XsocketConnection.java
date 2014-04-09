/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:21:57
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.io.IOException;

import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.util.LogUtil;

/**
 * Connection的xsocket实现.
 * 
 * @author shandong.su
 */
final class XsocketConnection extends Connection {
    /**
     * 加密器
     */
    private static XsocketStrictCodecFactory.XsocketAbstractEncoder encoder = null;
    /**
     * nio抽象连接
     */
    private INonBlockingConnection session;

    /**
     * 
     */
    static {
        encoder = XsocketStrictCodecFactory.getEncoder(GameServer.getInstance()
                        .getMiscConfig().getCiphertext());
    }

    /**
     * @param handler
     */
    public XsocketConnection(INonBlockingConnection session) {
        this.session = session;
        this.setHostname(session.getRemoteAddress().getHostName());
        this.setPort(session.getRemotePort());
    }

    @Override
    public void writeMessage(Message message) {
        try {
            session.write(encoder.doEncode(session, message));
            session.flush();
        } catch (Exception e) {
            LogUtil.error("xsocket写入数据错误", e);
        }
    }

    /**
     * 关闭连接
     */
    @Override
    public void closeConnection() {
        try {
            session.close();
        } catch (IOException e) {
            LogUtil.error("关闭Xsocket连接错误", e);
        }
    }
}
