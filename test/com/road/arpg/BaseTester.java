/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-18  上午10:47:59
 */
package com.road.arpg;

import java.io.File;
import java.net.InetSocketAddress;

import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.BeforeClass;

import com.road.arpg.core.manager.socket.SocketUtil;
import com.road.arpg.core.manager.socket.mina.MinaSocketHandler;
import com.road.arpg.core.manager.socket.mina.MinaStrictCodecFactory;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * @author Dream.xie
 */
public abstract class BaseTester {
    /**
     * log4j配置
     */
    private static final String LOG4J_FILE = "log4j.properties";
    /**
     * 在session中用来解密的字符串
     */
    private static final String ENCRYPTION_KEY = "ENCRYPTION_KEY";

    /**
     * ip地址
     */
    private static final String IP = "localhost";
    /**
     * 端口号
     */
    private static final int PORT = 9000;

    /**
     * 初始化init
     */
    @BeforeClass
    public static void init() {
        //初始化log4j
        PropertyConfigurator.configure(ProjectPathUtil.getConfigDirPath()
                        + File.separator + LOG4J_FILE);
    }

    /**
     * 创建session
     * 
     * @param args
     * @throws Exception
     */
    public IoSession createSession() throws Exception {
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast(
                        "codec",
                        new ProtocolCodecFilter(new MinaStrictCodecFactory(
                                        false)));
        connector.setHandler(new MinaSocketHandler());
        ConnectFuture futrue = connector
                        .connect(new InetSocketAddress(IP, PORT));
        futrue.await();
        IoSession session = futrue.getSession();
        session.setAttribute(ENCRYPTION_KEY, SocketUtil.copyDefaultKey());
        return session;
    }
}
