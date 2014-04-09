/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.core.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.road.arpg.core.util.LogUtil;

/**
 * 事件源。同一事件类型，支持多个监听者，并且监听者对象可以相同。
 * 
 * @author shandong.su
 */
public class EventSource {

    /**
     * 监听都列表。同一事件，可能存在多个相同的监听者。
     */
    private Map<Integer, Collection<IEventListener>> listeners;

    /**
     * 
     */
    private ReadWriteLock lock;

    /**
     * 构造方法
     */
    public EventSource() {
        this.listeners = new ConcurrentHashMap<Integer, Collection<IEventListener>>();
        this.lock = new ReentrantReadWriteLock();
    }

    /**
     * 将监听者加入到指定事件类型的监听队列中。
     * 
     * @param eventType
     *            事件类型
     * @param listener
     *            监听者
     */
    public void addListener(int eventType, IEventListener listener) {
        Collection<IEventListener> lstns = this.listeners.get(eventType);
        if (lstns == null) {
            try {
                // 加写锁确保没有并发问题。
                this.lock.writeLock().lock();

                lstns = this.listeners.get(eventType);
                if (lstns == null) {
                    // lstns = new ConcurrentSkipListSet<IEventListener>();
                    lstns = new LinkedList<IEventListener>();
                    lstns.add(listener);
                    this.listeners.put(eventType, lstns);
                } else {
                    lstns.add(listener);
                }
            } catch (Exception e) {
                LogUtil.error("", e);
            } finally {
                this.lock.writeLock().unlock();
            }
        } else {
            lstns.add(listener);
        }

    }

    /**
     * 从指定事件类型的监听队列中移除指定的监听者。
     * 
     * @param eventType
     *            事件类型
     * @param listener
     *            监听者
     */
    public void removeListener(int eventType, IEventListener listener) {
        try {
            this.lock.writeLock().lock();

            Collection<IEventListener> lstns = this.listeners.get(eventType);
            if (lstns != null) {
                lstns.remove(listener);
            }
        } catch (Exception e) {
            LogUtil.error("", e);
        } finally {
            this.lock.writeLock().unlock();
        }

    }

    /**
     * 通知监听者发生了事件。事件源由事件参数arg指定。
     * 
     * @param arg
     *            事件参数
     */
    public void notifyListeners(Event arg) {
        try {
            this.lock.readLock().lock();

            Collection<IEventListener> lstns = this.listeners.get(arg
                            .getEventType());
            if (lstns != null) {
                lstns = new ArrayList<IEventListener>(lstns);
                for (IEventListener item : lstns) {
                    item.onEvent(arg);
                }
            }
        } catch (Exception e) {
            LogUtil.error("", e);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    /**
     * 通知监听者发生了事件。事件源为当前this对象。
     * 
     * @param eventType
     *            事件类型
     */
    public void notifyListeners(int eventType) {
        try {
            this.lock.readLock().lock();
            Collection<IEventListener> lstns = listeners.get(eventType);
            if (lstns != null) {
                lstns = new ArrayList<IEventListener>(lstns);
                for (IEventListener item : lstns) {
                    item.onEvent(new Event(this, eventType));
                }
            }
        } catch (Exception e) {
            LogUtil.error("", e);
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
