/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-31  上午10:03:14
 */
package com.road.arpg.core.fsm;

/**
 * @author Dream.xie
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class AbstractStateOwner implements IStateOwner {

    /**
     * 状态机.
     */
    private StateMachine stateMachine = null;

    /**
     * @param stateMachine
     *            the stateMachine to set
     */
    protected void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    /**
     * 执行状态逻辑.
     */
    @Override
    public void execute() throws Exception {
        stateMachine.execute();
    }

    /**
     * @return the currentState
     */
    @Override
    public IState getCurrentState() {
        return stateMachine.getCurrentState();
    }

    /**
     * @return the globalState
     */
    @Override
    public IState getGlobalState() {
        return stateMachine.getGlobalState();
    }

    /**
     * @return the previousState
     */
    @Override
    public IState getPreviousState() {
        return stateMachine.getPreviousState();
    }

    /**
     * 改变状态.
     */
    @Override
    public void changeState(IState newState) throws Exception {
        stateMachine.changeState(newState);
    }

    /**
     * 改变状态并马上执行.
     */
    @Override
    public void changeStateAndUpdate(IState newState) throws Exception {
        stateMachine.changeStateAndUpdate(newState);
    }

    /**
     * 切换到前一个状态.
     */
    @Override
    public void revertToPrevious() throws Exception {
        stateMachine.revertToPrevious();
    }

    /**
     * 当前状态是否给定的状态。
     * 
     * @throws Exception
     */
    @Override
    public Boolean isCurrentState(IState state) throws Exception {
        return stateMachine.isCurrentState(state);
    }

    /**
     * 处理消息.
     * 
     * @param fsmMessage
     */
    public void handlerMessage(FsmMessage fsmMessage) {

    }
}
