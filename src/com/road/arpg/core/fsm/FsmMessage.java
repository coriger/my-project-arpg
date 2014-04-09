/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-31  上午10:30:23
 */
package com.road.arpg.core.fsm;

import java.util.Date;

/**
 * @author Dream.xie
 */
public final class FsmMessage {
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 延迟多久，单位(秒)
     */
    private Integer delay;
    /**
     * 发送者.
     */
    private IStateOwner sender;
    /**
     * 接受者.
     */
    private IStateOwner receiver;
    /**
     * 参数.
     */
    private Object content;

    /**
     * 类型.
     */
    private IFsmMessageType type;

    /**
     * 执行时间，毫秒.
     */
    private Long executeTime;

    /**
     * 
     */
    FsmMessage(Integer delay, IFsmMessageType type, Date sendTime,
                    IStateOwner sender, IStateOwner receiver, Object content) {
        this.delay = delay;
        this.sendTime = sendTime;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.type = type;
        if (delay != null) {
            executeTime = sendTime.getTime() + delay * 1000;
        }
    }

    /**
     * @return the executeTime
     */
    Long getExecuteTime() {
        return executeTime;
    }

    /**
     * @return the type
     */
    public IFsmMessageType getType() {
        return type;
    }

    /**
     * @return the sendTime
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @return the delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * @return the sender
     */
    public IStateOwner getSender() {
        return sender;
    }

    /**
     * 
     * @return the receiver
     */
    public IStateOwner getReceiver() {
        return receiver;
    }

    /**
     * @return the param
     */
    public Object getContent() {
        return content;
    }

}
