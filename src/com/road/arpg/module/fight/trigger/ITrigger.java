/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * @param <M>
 * @param <N>
 * @author : shandong.su
 * @version 触发器接口
 */
public interface ITrigger<M, N> {
    /**
     * 添加条件子结点
     * 
     * @param condition
     */
    void addConditionNode(ConditionNode<N> condition);

    /**
     * 添加条件结点为true时执行子结点
     * 
     * @param action
     */
    void addYesActionNode(ActionNode<N> action);

    /**
     * 添加条件结点为false时执行子结点
     * 
     * @param action
     */
    void addNoActionNode(ActionNode<N> action);

    /**
     * 挂载此触发器到host
     * 
     * @param host
     * @param eventType
     */
    void mount(M host, int eventType);

    /**
     * 取消挂载触发器
     * 
     * @param host
     * @param eventType
     */
    void unMount(M host, int eventType);
}
