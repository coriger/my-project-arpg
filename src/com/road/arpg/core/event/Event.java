/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.core.event;

import java.util.EventObject;

/**
 * 事件
 * 
 * @author shandong.su
 */
public class Event extends EventObject {
    /**
     * 
     */
    private static final long serialVersionUID = -3737140928956265655L;

    /** 事件类型 */
    private int eventType;

    /** 自定义参数 */
    private Object data;

    /** 触发器参数(如果事件会或发触发器的话，有可能会设置此参数) */
    private String triParams;

    /**
     * 事件构造器
     * 
     * @param source
     *            事件源
     * @param eventType
     *            事件类型
     */
    public Event(Object source, int eventType) {
        super(source);
        this.eventType = eventType;
    }

    /**
     * 事件构造器
     * 
     * @param source
     *            事件源
     * @param eventType
     *            事件类型
     * @param data
     *            自定义参数
     */
    public Event(Object source, int eventType, Object data) {
        super(source);
        this.eventType = eventType;
        this.data = data;
    }

    /**
     * 获取事件类型
     * 
     * @return 事件类型
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * 获取自定义参数
     * 
     * @return 自定义参数
     */
    public Object getData() {
        return data;
    }

    /**
     * 
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the triParams
     */
    public String getTriParams() {
        return triParams;
    }

    /**
     * @param triParams
     *            the triParams to set
     */
    public void setTriParams(String triParams) {
        this.triParams = triParams;
    }
}
