/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.core.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子锁，内部用AtomicInteger实现， 使用场景，锁的粒度非常小的情况。
 * 
 * @author shandong.su
 */
public final class AtomicLock {
    /**
     * 原子变量
     */
    private AtomicInteger value;

    /**
     * 
     */
    public AtomicLock() {
        value = new AtomicInteger(0);
    }

    /**
     * lock 与 unlock必须匹配使用
     */
    public void lock() {
        value.compareAndSet(0, 1);
    }

    /**
     * lock 与 unlock必须匹配使用
     */
    public void unlock() {
        value.set(0);
    }
}
