/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:10:11
 */
package com.road.arpg.core.fsm;

import com.road.arpg.core.util.RandomUtil;

/**
 * 休息状态.
 * 
 * @author Dream.xie
 */
public class RestState extends AbstractState<Person> {
    /**
     * 
     */
    private static final RestState INSTANCE = new RestState();

    /**
     * @return
     */
    public static RestState getInstance() {
        return INSTANCE;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#enter(java.lang.Object)
     */
    @Override
    public void enter(Person owner) throws Exception {
        System.out.println(String.format("【%s】已经准备休息咯！", owner.getName()));
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#execute(java.lang.Object)
     */
    @Override
    public void execute(Person owner) throws Exception {
        System.out.println(String.format("【%s】正在休息！", owner.getName()));
        //如果休息超过了20毫秒就切换到Lol或者看电影
        if (owner.getResetTime() >= 20) {
            int randNum = RandomUtil.randInt(2);
            //0表示LOL，1表示电影.
            if (randNum == 0) {
                owner.changeStateAndUpdate(PlayLolState.getInstance());
            } else {
                owner.changeStateAndUpdate(WatchTVState.getInstance());
            }
        }
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#exit(java.lang.Object)
     */
    @Override
    public void exit(Person owner) throws Exception {
        owner.setResetTime(0);
        System.out.println(String.format("【%s】停止休息！", owner.getName()));
    }

}
