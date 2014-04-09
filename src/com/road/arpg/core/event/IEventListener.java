/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.core.event;

/**
 * 事件监听器接口
 * 
 * @author shandong.su
 */
public interface IEventListener {
    /**
     * 事件触发时的回调。
     * 
     * @param arg
     *            事件参数
     */
    void onEvent(Event arg);
}
