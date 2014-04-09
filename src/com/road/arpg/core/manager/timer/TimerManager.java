/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:56:59
 */
package com.road.arpg.core.manager.timer;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.road.arpg.core.IManager;

/**
 * 定时器管理器.
 * 
 * @author Dream.xie
 */
public final class TimerManager implements IManager {
    /**
     * 
     */
    private static TimerManager instance = new TimerManager();
    /**
     * 调度器
     */
    private Scheduler scheduler = null;

    /**
     * 
     */
    private TimerManager() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @return
     */
    public static TimerManager getInstance() {
        return instance;
    }

    /**
     * 
     */
    @Override
    public void start() throws Exception {
        // 系统调度
        scheduler.start();

    }

    /**
     * 停止
     */
    @Override
    public void stop() throws Exception {
        scheduler.shutdown();
    }
}
