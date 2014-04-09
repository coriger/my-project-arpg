/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:10:11
 */
package com.road.arpg.core.fsm;

import com.road.arpg.core.util.RandomUtil;

/**
 * 人物全局状态.
 * 
 * @author Dream.xie
 */
public class PersonGlobalState extends AbstractState<Person> {
    /**
     * 
     */
    private static final PersonGlobalState INSTANCE = new PersonGlobalState();

    /**
     * @return
     */
    public static PersonGlobalState getInstance() {
        return INSTANCE;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.IState#execute(java.lang.Object)
     */
    @Override
    public void execute(Person owner) throws Exception {
        //1/10的概率上厕所.
        if (RandomUtil.inRandom(0, 10)) {
            owner.changeStateAndUpdate(GoToWCState.getInstance());
        }

    }

}
