/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:10:11
 */
package com.road.arpg.core.fsm;

/**
 * 看电视状态.
 * 
 * @author Dream.xie
 */
public class WatchTVState extends AbstractState<Person> {
    /**
     * 
     */
    private static final WatchTVState INSTANCE = new WatchTVState();

    /**
     * @return
     */
    public static WatchTVState getInstance() {
        return INSTANCE;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#enter(java.lang.Object)
     */
    @Override
    public void enter(Person owner) throws Exception {
        System.out.println(String.format("【%s】已经准备看电视咯！", owner.getName()));
        System.out.println("发消息给自己!");
        FsmMessageDispatcher.getInstance().dispatchMessage(10, null, owner,
                        owner, "就是一条消息而已，延迟10秒钟!");
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#execute(java.lang.Object)
     */
    @Override
    public void execute(Person owner) throws Exception {
        owner.setEpisodes(owner.getEpisodes() + 1);
        System.out.println(String.format("【%s】正在看电视,第【%d】集!", owner.getName(),
                        owner.getEpisodes()));
        //如果看电视超过3集.
        if (owner.getEpisodes() > 3) {
            owner.changeStateAndUpdate(RestState.getInstance());
        }
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#exit(java.lang.Object)
     */
    @Override
    public void exit(Person owner) throws Exception {
        owner.setEpisodes(0);
        System.out.println(String.format("【%s】停止看电视！", owner.getName()));
    }

}
