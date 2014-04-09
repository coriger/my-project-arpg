/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:53:55
 */
package com.road.arpg.core.fsm;

/**
 * @param <T>
 *            具体的对象类.
 * @author Dream.xie
 */
public abstract class AbstractState<T> implements IState<T> {

    /**
     * 
     */
    @Override
    public void enter(T owner) throws Exception {
    }

    /**
     * 
     */
    @Override
    public void exit(T owner) throws Exception {
    }

    /**
     * 
     */
    @Override
    public abstract void execute(T owner) throws Exception;

}
