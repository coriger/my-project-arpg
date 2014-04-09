/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-31  上午10:48:35
 */
package com.road.arpg.core.fsm;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.road.arpg.core.util.LogUtil;

/**
 * 消息分发器.
 * 
 * @author Dream.xie
 */
public final class FsmMessageDispatcher implements Runnable {

    /**
     * 
     */
    private static final FsmMessageDispatcher INSTANCE = new FsmMessageDispatcher();

    /**
     * 
     */
    private List<FsmMessage> fasMessages = new LinkedList<>();

    /**
     * 
     */
    private FsmMessageDispatcher() {

    }

    /**
     * 
     * @return
     */
    public static FsmMessageDispatcher getInstance() {
        return INSTANCE;
    }

    /**
     * 发送消息.
     * 
     * @param delay
     * @param sender
     * @param receiver
     * @param param
     * @throws Exception
     */
    public void dispatchMessage(IFsmMessageType type, IStateOwner sender,
                    IStateOwner receiver, Object param) {
        FsmMessage fsmMessage = new FsmMessage(null, type, new Date(), sender,
                        receiver, param);
        receiver.handlerMessage(fsmMessage);
    }

    /**
     * 发送延迟消息.
     * 
     * @param delay
     * @param sender
     * @param receiver
     * @param param
     * @throws Exception
     */
    public void dispatchMessage(Integer delay, IFsmMessageType type,
                    IStateOwner sender, IStateOwner receiver, Object param) {
        if (delay == null || delay <= 0) {
            throw new IllegalArgumentException("传入的Delay参数为null或者小于等于0!");
        }
        FsmMessage fsmMessage = new FsmMessage(delay, type, new Date(), sender,
                        receiver, param);
        //添加
        synchronized (fasMessages) {
            fasMessages.add(fsmMessage);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            List<FsmMessage> removeFsmMsgs = new ArrayList<>();
            for (FsmMessage fsmMessage : fasMessages) {
                if (fsmMessage.getExecuteTime() <= currentTime) {
                    removeFsmMsgs.add(fsmMessage);
                    fsmMessage.getReceiver().handlerMessage(fsmMessage);
                }
            }
            //删除
            synchronized (fasMessages) {
                fasMessages.removeAll(removeFsmMsgs);
            }
            //睡眠
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                LogUtil.error(e);
            }
        }
    }
}
