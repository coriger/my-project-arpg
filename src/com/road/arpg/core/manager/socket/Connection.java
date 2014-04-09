/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:15:51
 */
package com.road.arpg.core.manager.socket;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.road.arpg.core.util.NamedThreadFactory;
import com.road.arpg.core.util.SelfDrivenTaskQueue;
import com.road.arpg.module.misc.entity.PlayerEntitiy;

/**
 * socket连接接口
 * 
 * @author shandong.su
 */
public abstract class Connection {
    /**
     * socket服务器管理器
     */
    private static SocketManager socketManager = SocketManager.getInstance();

    /**
     * 自驱动线程池
     */
    private static ThreadPoolExecutor selfDrivenThreadPool = new ThreadPoolExecutor(
                    5, Runtime.getRuntime().availableProcessors() * 2 + 1, 20,
                    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                    new NamedThreadFactory("GAME-MSG-PROCESS-THREADS",
                                    Thread.NORM_PRIORITY));

    /**
     * socket对应的玩家连接
     */
    private PlayerEntitiy player;

    /**
     * 连接ID
     */
    private Long id;

    /**
     * 状态
     */
    private State state = State.CLOSED;

    /**
     * 主机名
     */
    private String hostname;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 一个玩家身上的自驱动队列
     */
    private SelfDrivenTaskQueue<ExcuteCommand> cmdQueue = new SelfDrivenTaskQueue<ExcuteCommand>(
                    selfDrivenThreadPool);

    /**
     * 消息的处理器
     * 
     * @param handler
     */
    public Connection() {
    }

    /**
     * 将指令压入自驱动队列
     * 
     * @param conn
     * @param packet
     */
    public void process(Object packet) {
        short code = ((Message) packet).getCode();
        ICommand cmd = socketManager.getCommand(code);
        cmdQueue.add(new ExcuteCommand(cmd, (Message) packet, this));
    }

    /**
     * 回调，驱动命令队列中的下一个
     */
    public void finishOneCommandTask() {
        cmdQueue.complete();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 是否已连接上.
     */
    public Boolean isConnected() {
        return this.state == State.CONNECTED;
    }

    /**
     * 是否断开.
     */
    public Boolean isClosed() {
        return this.state == State.CLOSED;
    }

    /**
     * 写消息给外部<br>
     * 抽象，由具体的类实现
     * 
     * @param message
     */
    public abstract void writeMessage(Message message);

    /**
     * 断开连接
     */
    public abstract void closeConnection();

    /**
     * @return the player
     */
    public PlayerEntitiy getPlayer() {
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    public void setPlayer(PlayerEntitiy player) {
        this.player = player;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname
     *            the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 状态
     * 
     * @author Dream.xie
     */
    public enum State {
        /**
         * 已连接
         */
        CONNECTED,
        /**
         * 
         */
        CLOSED
    }
}
