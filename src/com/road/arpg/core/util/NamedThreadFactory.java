/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.core.util;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 命名线程生成工厂，仅为了给游戏所用线程池所创建的线程提供命名支持
 * 
 * @author shandong.su
 * @version
 * 
 */
public class NamedThreadFactory implements ThreadFactory ,
                UncaughtExceptionHandler {

    /**
     * 是否为后台线程
     */
    private boolean daemon;

    /**
     * 线程名
     */
    private String threadName;
    /**
     * 优先级
     */
    private int prio;
    /**
     * 线程组
     */
    private ThreadGroup group;
    /**
     * 线程数目
     */
    private AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 
     * @param threadName
     */
    public NamedThreadFactory(String threadName) {
        this(threadName, false);
        group = new ThreadGroup(threadName);
    }

    /**
     * 
     * 
     * @param threadName
     *            线程名前缀
     * @param daemon
     *            是否为后台线程
     */
    public NamedThreadFactory(String threadName, boolean daemon) {
        this.threadName = threadName;
        this.daemon = daemon;
        group = new ThreadGroup(threadName);
    }

    /**
     * 
     * 
     * @param name
     * @param prio
     */
    public NamedThreadFactory(String threadName, int prio) {
        this.threadName = threadName;
        this.prio = prio;
        this.daemon = false;
        group = new ThreadGroup(threadName);
    }

    /**
     * 
     * 
     * @param name
     * @param prio
     */
    public NamedThreadFactory(String threadName, boolean daemon, int prio) {
        this.prio = prio;
        this.daemon = daemon;
        this.threadName = threadName;
        group = new ThreadGroup(threadName);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
     */
    // @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r);
        t.setName(threadName + "-" + threadNumber.getAndIncrement());
        t.setPriority(prio);
        t.setDaemon(daemon);
        t.setUncaughtExceptionHandler(this);
        return t;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread,
     *      java.lang.Throwable)
     */
    public void uncaughtException(Thread thread, Throwable throwable) {
        LogUtil.error(String.format("线程【%s】抛出异常。", thread.getName()), throwable);
    }
}
