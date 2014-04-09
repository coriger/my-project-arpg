/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:55:39
 */
package com.road.arpg.core.manager.socket.xsocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xsocket.connection.IConnectExceptionHandler;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IConnection;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.INonBlockingConnection;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.SocketUtil;

/**
 * 游戏Socket处理
 * 
 * @author Dream.xie
 * 
 */
@SuppressWarnings("unchecked")
final class XsocketSocketHandler implements IConnectHandler ,
                IDisconnectHandler , IDataHandler , IConnectExceptionHandler {
    /**
     * 连接附件名字
     */
    private static final String ATTACHMENT_CONNECTION = "ATTACHMENT_CONNECTION";

    /**
     * 解码器
     */
    private static XsocketStrictCodecFactory.XsocketAbstractDecoder decoder = null;

    static {
        decoder = XsocketStrictCodecFactory.getDecoder(GameServer.getInstance()
                        .getMiscConfig().getCiphertext());
    }

    /**
     * 
     */
    XsocketSocketHandler() {
    }

    /**
     * 连接上
     */
    @Override
    public boolean onConnect(INonBlockingConnection session) throws IOException {
        session.setOption(IConnection.SO_LINGER, 1);
        Connection connection = new XsocketConnection(session);
        int[] key = SocketUtil.copyDefaultKey();
        //附件 放入连接，加密密钥，解密密钥
        Map<String, Object> attachmentMap = new HashMap<>();
        attachmentMap.put(ATTACHMENT_CONNECTION, connection);
        attachmentMap.put(XsocketStrictCodecFactory.DECRYPTION_KEY, key);
        attachmentMap.put(XsocketStrictCodecFactory.ENCRYPTION_KEY, key);
        session.setAttachment(attachmentMap);
        return false;
    }

    /**
     * 接收到数据
     */
    @Override
    public boolean onData(INonBlockingConnection session) throws IOException {
        if (session.available() == -1) {
            return false;
        }
        if (session.getAttachment() == null) {
            return false;
        }
        // 读取封包头，获得封包长度，长度不足则抛出BufferUnderflowException，并重置标记等待下次数据
        Map<String, Object> attachmentMap = (Map<String, Object>) session
                        .getAttachment();
        Connection connection = (Connection) attachmentMap
                        .get(ATTACHMENT_CONNECTION);
        connection.process(decoder.doDecode(session));
        return false;
    }

    @Override
    public boolean onDisconnect(INonBlockingConnection session)
                    throws IOException {
        session.close();
        return false;
    }

    @Override
    public boolean onConnectException(INonBlockingConnection session,
                    IOException e) throws IOException {
        return false;
    }

}
