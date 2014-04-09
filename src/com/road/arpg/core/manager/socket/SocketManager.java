/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:26:21
 */
package com.road.arpg.core.manager.socket;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.IManager;
import com.road.arpg.core.IReloadable;
import com.road.arpg.core.annotation.SocketCommand;
import com.road.arpg.core.manager.cache.CacheManager;
import com.road.arpg.core.manager.cache.CacheManager.CacheType;
import com.road.arpg.core.manager.socket.mina.MinaSocketAcceptor;
import com.road.arpg.core.manager.socket.netty.NettySocketAcceptor;
import com.road.arpg.core.manager.socket.xsocket.XsocketSocketAcceptor;
import com.road.arpg.core.util.LogUtil;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 抽象的服务器类<br>
 * 由底层的具体实现
 * 
 * @author shandong.su
 */
@SuppressWarnings({ "unchecked" })
public final class SocketManager implements IManager ,
                IReloadable<List<ICommand>> {
    /**
     * 单例
     */
    private static final SocketManager INSTANCE = new SocketManager();
    /**
     * 连接管理
     */
    private Map<Long, Connection> connections = null;

    /**
     * 游戏Command
     */
    private Map<Short, ICommand> gameCommands = new HashMap<>();

    /**
     * Socket控制器
     */
    private SocketAcceptor socketAcceptor = null;

    /**
     * 私有化公共方法，不让初始化
     */
    private SocketManager() {
    }

    /**
     * 
     * @return
     */
    public static SocketManager getInstance() {
        return INSTANCE;
    }

    /**
     * 添加连接
     */
    public void addNewConnection(Connection gamePlayer) {
        connections.put(gamePlayer.getId(), gamePlayer);
    }

    /**
     * 新增连接之前，先获取连接
     * 
     * @param gamePlayer
     * @return
     */
    public Connection getConnection(Connection gamePlayer) {
        return connections.get(gamePlayer.getId());
    }

    /**
     * 开始
     */
    @Override
    public void start() throws Exception {
        //初始化连接缓存
        connections = (Map<Long, Connection>) CacheManager.getInstance()
                        .getCache(CacheType.CONNECTION);
        //初始化内部模块指令
        initInternalCommand();
        //初始化socket
        initSocketAcceptor();
    }

    /**
     * 停止
     */
    @Override
    public void stop() throws Exception {
        socketAcceptor.stop();
    }

    /**
     * 重新加载
     */
    @Override
    public void reload(List<ICommand> list) throws Exception {
        SocketCommand socketCommandAnnotation = null;
        for (ICommand abstractCommand : list) {
            socketCommandAnnotation = abstractCommand.getClass().getAnnotation(
                            SocketCommand.class);
            if (socketCommandAnnotation == null) {
                throw new IllegalArgumentException(String.format(
                                "【%s】类不包含【%s】注解!", abstractCommand.getClass()
                                                .getName(), SocketCommand.class
                                                .getName()));
            } else {
                gameCommands.put(socketCommandAnnotation.type(),
                                abstractCommand);
            }
        }
    }

    /**
     * 返回对应的执行命令
     * 
     * @param code
     * @return
     */
    protected ICommand getCommand(short code) {
        return this.gameCommands.get(code);
    }

    /**
     * 初始化模块指令
     * 
     * @throws Exception
     */
    private void initInternalCommand() throws Exception {

        //module主目录
        File allModuleDir = new File(ProjectPathUtil.getBinDirPath()
                        + File.separator + "main" + File.separator + "com"
                        + File.separator + "road" + File.separator + "arpg"
                        + File.separator + "module");
        //获得所有module目录
        File[] moduleDirs = allModuleDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        //获得Module的Command文件夹
        List<File> commandDirs = new ArrayList<File>();
        for (File moduleDir : moduleDirs) {
            commandDirs.addAll(Arrays.asList(moduleDir
                            .listFiles(new FileFilter() {
                                @Override
                                public boolean accept(File pathname) {
                                    return pathname.isDirectory()
                                                    && pathname.getName()
                                                                    .equalsIgnoreCase(
                                                                                    "command");
                                }
                            })));
        }

        //获得Module的class文件
        List<File> commandFiles = new ArrayList<File>();
        for (File commandDir : commandDirs) {
            commandFiles.addAll(Arrays.asList(commandDir.listFiles()));
        }

        //加载class文件
        List<Class<ICommand>> classes = new ArrayList<Class<ICommand>>(
                        commandFiles.size());
        String fileName = null;
        for (File commandFile : commandFiles) {
            fileName = commandFile.getAbsolutePath();
            fileName = fileName.substring(fileName.indexOf("main") + 5,
                            fileName.length() - 6);
            fileName = fileName.replaceAll("[/\\\\]", ".");
            classes.add((Class<ICommand>) Class.forName(fileName));
        }

        SocketCommand socketCommandAnnotation = null;
        short type = 0;
        ICommand gameCommand = null;
        for (Class<?> clazz : classes) {
            socketCommandAnnotation = clazz.getAnnotation(SocketCommand.class);
            if (socketCommandAnnotation != null) {
                type = socketCommandAnnotation.type();
                gameCommand = (ICommand) clazz.newInstance();
                gameCommands.put(type, gameCommand);
            }
        }

    }

    /**
     * 初始化socketHandler
     * 
     * @param class
     * @return
     * @throws Exception
     */
    private void initSocketAcceptor() throws Exception {
        String socketType = GameServer.getInstance().getMiscConfig()
                        .getSocketType();
        switch (socketType) {
            case "mina":
                socketAcceptor = new MinaSocketAcceptor();
                break;
            case "netty":
                socketAcceptor = new NettySocketAcceptor();
                break;
            case "xsocket":
                socketAcceptor = new XsocketSocketAcceptor();
                break;
            default:
                throw new IllegalArgumentException(
                                String.format("SocketType不属于mina,netty,socket任何一种,SocketType为：%s",
                                                socketType));
        }
        //启动
        socketAcceptor.start();
        LogUtil.info(String.format("使用【%s】socket架构【%s】。", socketType,
                        socketAcceptor.getClass().getName()));
        if (!GameServer.getInstance().getMiscConfig().getCiphertext()) {
            System.err.println("注意:当前使用的是明文编码!");
        }
    }
}
