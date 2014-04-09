/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.action;

/**
 * 游戏动作基类
 * 
 * @param <T>
 * @author shandong.su
 */
public interface IAction<T> {
    /**
     * action执行动作
     * 
     * @param scene
     *            当前action所属game对象
     * @param tick
     *            action执行时刻
     */
    void execute(T scene, long tick);

    /**
     * action是否执行完成
     * 
     * @param scene
     *            当前action所属game对象
     * @param tick
     *            action完成时刻
     * @return 是否已经执行完成
     */
    boolean isFinished(T scene, long tick);
}
