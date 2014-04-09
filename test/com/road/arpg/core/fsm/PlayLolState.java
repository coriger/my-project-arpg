/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:10:11
 */
package com.road.arpg.core.fsm;

/**
 * 玩lol状态.
 * 
 * @author Dream.xie
 */
public class PlayLolState extends AbstractState<Person> {
    /**
     * 
     */
    private static final PlayLolState INSTANCE = new PlayLolState();

    /**
     * @return
     */
    public static PlayLolState getInstance() {
        return INSTANCE;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#enter(java.lang.Object)
     */
    @Override
    public void enter(Person owner) throws Exception {
        System.out.println(String.format("【%s】已经准备玩LOL咯！", owner.getName()));
        System.out.println("发消息给自己!");
        FsmMessageDispatcher.getInstance().dispatchMessage(10, null, owner,
                        owner, "就是一条消息而已，延迟10秒钟!");
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#execute(java.lang.Object)
     */
    @Override
    public void execute(Person owner) throws Exception {
        owner.setGames(owner.getGames() + 1);
        System.out.println(String.format("【%s】正在玩LOL咯,第【%d】局!",
                        owner.getName(), owner.getGames()));
        //玩游戏超过两局，就切换到休息状态.
        if (owner.getGames() > 2) {
            owner.changeStateAndUpdate(RestState.getInstance());
        }
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#exit(java.lang.Object)
     */
    @Override
    public void exit(Person owner) throws Exception {
        owner.setGames(0);
        System.out.println(String.format("【%s】终止玩LOL咯！", owner.getName()));
    }

}
