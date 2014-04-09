/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects.state;

import java.awt.Color;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.util.Point;

/**
 * 施放技能状态<br>
 * 
 * 施放技能与攻击都属于这个状态<br>
 * 攻击和施放技能都有一个延迟的伤害计算
 * 
 * @author shandong.su
 */
public class AttackingState implements IState {

    @Override
    public void updateFrame(Living self, long tick) {
        self.getSkillHandler().tick(tick);
    }

    @Override
    public void enter(Living self, long tick) {
        self.setColor(Color.GREEN);
    }

    @Override
    public void beHurt(Living self, long tick) {

    }

    @Override
    public void moving(Living self, int posX, int posY, long tick) {

    }

    @Override
    public void releaseSkill(Living self, long tick, int skillID, Point pos) {
        self.getSkillHandler().useSkill(skillID, tick, pos);
    }

    @Override
    public LivingState getLivingState() {
        return LivingState.ATTACKING;
    }

}
