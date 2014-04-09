/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:15:51
 */
package com.road.arpg.core.manager.socket;

import com.road.arpg.core.GameServer;
import com.road.arpg.core.util.LogUtil;

/**
 * 执行命令线程
 * 
 * @author shandong.su
 */
final class ExcuteCommand implements Runnable {

    /**
     * 执行体
     */
    private ICommand command;

    /**
     * 消息体
     */
    private Message message;

    /**
     * 连接
     */
    private Connection connection;

    /**
     * 创建新的执行指令
     * 
     * @param command
     * @param message
     * @param conn
     */
    ExcuteCommand(ICommand command, Message message, Connection conn) {
        this.command = command;
        this.message = message;
        this.connection = conn;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            command.excute(connection, message);
        } catch (Exception e) {
            LogUtil.error(String.format("command error code: %d",
                            message.getCode()), e);
        } finally {
            //为了更精确。应该先获得endTime，然后再回调下一个函数。
            long endTime = System.currentTimeMillis();
            //回调下一个函数
            this.connection.finishOneCommandTask();
            long currentRuntime = endTime - startTime;
            int cmdRuntime = GameServer.getInstance().getMiscConfig()
                            .getCmdRuntime();
            if (currentRuntime > cmdRuntime) {
                LogUtil.fatal(String
                                .format("Command执行超过限制时间，限制的时间为【%d】，而当前执行【%s】命令的时间为【%d】",
                                                cmdRuntime, command.getClass()
                                                                .getName(),
                                                currentRuntime));
            }
        }
    }
}
