/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects.state;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.util.Point;

/**
 * 状态机基类
 * 
 * @author shandong.su
 */
public interface IState {

    /**
     * 怪物的处于某些状态时，不需要更新
     * 
     * @param self
     * @param tick
     */
    void updateFrame(Living self, long tick);

    /**
     * 激活，当怪物处于{@link InactivingState}时，将调用此函数将怪物激活
     * 
     * @param self
     * @param tick
     */
    void enter(Living self, long tick);

    /**
     * 受到攻击时，有些怪物就将切换到{@link FollowingState} 或 {@link AttackingState}状态
     * 
     * @param self
     * @param tick
     */
    void beHurt(Living self, long tick);

    /**
     * 移动状态<br>
     * 当怪物被激活过后，不主动攻击的怪物就进入{@link MovingState}或者 {@link SleepingState}<br>
     * 如果合法，都将进入{@link MovingState}
     * 
     * @param self
     * @param posX
     * @param posY
     * @param tick
     */
    void moving(Living self, int posX, int posY, long tick);

    /**
     * 施放技能<br>
     * 如果施放技能是合法的话，那么都将进入{@link AttackingState}
     * 
     * @param self
     * @param tick
     * @param skillID
     * @param pos
     *            TODO
     */
    void releaseSkill(Living self, long tick, int skillID, Point pos);

    /**
     * 获取当前状态的状态机类型
     * 
     * @return
     */
    LivingState getLivingState();
}
