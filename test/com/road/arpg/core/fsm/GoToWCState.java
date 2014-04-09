/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:10:11
 */
package com.road.arpg.core.fsm;

import java.util.concurrent.TimeUnit;

/**
 * 上厕所状态.
 * 
 * @author Dream.xie
 */
public class GoToWCState extends AbstractState<Person> {
    /**
     * 
     */
    private static final GoToWCState INSTANCE = new GoToWCState();

    /**
     * @return
     */
    public static GoToWCState getInstance() {
        return INSTANCE;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#enter(java.lang.Object)
     */
    @Override
    public void enter(Person owner) throws Exception {
        System.out.println(String.format("【%s】已经准备上厕所咯！", owner.getName()));
        System.out.println("发消息给自己!");
        FsmMessageDispatcher.getInstance().dispatchMessage(
                        PersonMessageType.GO_TO_WC, owner, owner,
                        "就是一条消息而已，不延迟的");

    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#execute(java.lang.Object)
     */
    @Override
    public void execute(Person owner) throws Exception {
        System.out.println(String.format("【%s】正在上厕所！", owner.getName()));
        TimeUnit.SECONDS.sleep(10);
        System.out.println(String.format("【%s】撒尿用了10秒", owner.getName()));
        //转变到上一个状态.
        owner.revertToPrevious();
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#exit(java.lang.Object)
     */
    @Override
    public void exit(Person owner) throws Exception {
        System.out.println(String.format("【%s】停止上厕所！", owner.getName()));
    }

}
