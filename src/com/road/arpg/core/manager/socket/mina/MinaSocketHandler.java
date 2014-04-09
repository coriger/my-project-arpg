/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.SocketUtil;

/**
 * MINA socket处理器
 * 
 * @author Dream.xie
 * 
 */
public final class MinaSocketHandler extends IoHandlerAdapter {
    /**
     * 附件属性名字
     */
    private static final String ATTRIBUTE_CONNECTION = "ATTRIBUTE_CONNECTION";

    /**
     * 
     */
    public MinaSocketHandler() {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        Connection conn = new MinaConnection(session);
        session.setAttribute(ATTRIBUTE_CONNECTION, conn);
        int[] key = SocketUtil.copyDefaultKey();
        session.setAttribute(MinaStrictCodecFactory.DECRYPTION_KEY, key);
        session.setAttribute(MinaStrictCodecFactory.ENCRYPTION_KEY, key);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }

    @Override
    public void messageReceived(IoSession session, Object message)
                    throws Exception {
        Connection conn = (Connection) session
                        .getAttribute(ATTRIBUTE_CONNECTION);
        conn.process(message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
                    throws Exception {
    }
}
