/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-24  上午9:48:09
 */
package com.road.arpg.core.manager.module;

import java.io.File;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.road.arpg.core.annotation.Module;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.manager.socket.mina.MinaSocketHandler;
import com.road.arpg.core.manager.socket.mina.MinaStrictCodecFactory;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 代理的Module抽象类.代理模块类只做中转使用。如果当主游戏服务器压力过大的时候，应该不用此方案，而是改用用户直接和对应的Module相连。
 * 
 * @author Dream.xie
 */
public abstract class AbstractProxyModule implements IModule {
    /**
     * 游戏配置文件
     */
    private static final String GAME_CFG_FILE = "game.xml";
    /**
     * SESSION
     */
    private IoSession session = null;

    /**
     * 写消息
     * 
     * @param message
     */
    protected void writeMessage(Message message) {
        session.write(message);
    }

    /**
     * 初始化模块
     */
    @Override
    public void start() throws Exception {
        Module moduleAnnotation = this.getClass().getAnnotation(Module.class);
        String proxyModuleCfgXpath = moduleAnnotation.proxyModuleCfgXpath();
        File configFile = new File(ProjectPathUtil.getConfigDirPath()
                        + File.separator + GAME_CFG_FILE);
        SAXReader reader = new SAXReader();
        Document document = reader.read(configFile);
        Element proxyModuleCfgElmt = (Element) document
                        .selectSingleNode(proxyModuleCfgXpath);
        String ip = proxyModuleCfgElmt.attributeValue("ip").trim();
        Integer port = Integer.parseInt(proxyModuleCfgElmt.attributeValue(
                        "port").trim());

        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast(
                        "codec",
                        new ProtocolCodecFilter(new MinaStrictCodecFactory(
                                        false)));
        connector.setHandler(new MinaSocketHandler());
        ConnectFuture futrue = connector
                        .connect(new InetSocketAddress(ip, port));
        futrue.await();
        session = futrue.getSession();
    }

    /**
     * 重启
     */
    @Override
    public void stop() throws Exception {
    }

}
