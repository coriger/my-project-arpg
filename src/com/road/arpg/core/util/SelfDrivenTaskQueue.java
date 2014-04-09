/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-25  上午11:26:21
 */
package com.road.arpg.core.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自驱动任务队列，队列中同时最多只能一个任务在执行。使用线程池执行任务。
 * 
 * @param <T>
 *            所有任务的基类
 * @author shandong.su
 */
public class SelfDrivenTaskQueue<T extends Runnable> {

    /** 执行Task的线程池 */
    private ExecutorService executorService = null;

    /**
     * 任务队列。队头元素是正在执行的任务。
     */
    private Queue<T> taskQueue = null;

    /** 运行锁，用于确保同时最多只能有一个任务在执行。任务队列本身是线程安全的。 */
    private ReentrantLock runningLock = null;

    /**
     * 创建自驱动队列
     * 
     * @param exeService
     */
    public SelfDrivenTaskQueue(ExecutorService exeService) {
        this.executorService = exeService;

        // 使用无锁线程安全队列
        this.taskQueue = new ConcurrentLinkedQueue<T>();

        this.runningLock = new ReentrantLock();
    }

    /**
     * 往任务队列中添加任务。
     * 
     * @param task
     */
    public void add(T task) {
        try {
            this.runningLock.lock();

            if (this.taskQueue.isEmpty()) {
                this.taskQueue.add(task);

                // 没有任务在执行，开始执行新添加的。
                this.executorService.submit(task);
            } else {
                // 有任务正在执行，将新任务添加到队列中，等待执行。
                this.taskQueue.add(task);
            }
        } finally {
            this.runningLock.unlock();
        }
    }

    /**
     * 完成一个任务。<br>
     * 任务完成的时候，必须调用本方法来驱动后续的任务，“自驱动”没有你想像中的智能哈~
     */
    public void complete() {
        try {
            this.runningLock.lock();

            // 移除已经完成的任务。
            this.taskQueue.remove();
            // 完成一个任务后，如果还有任务，则继续执行。
            if (!this.taskQueue.isEmpty()) {
                this.executorService.submit(this.taskQueue.peek());
            }
        } finally {
            this.runningLock.unlock();
        }
    }
}
