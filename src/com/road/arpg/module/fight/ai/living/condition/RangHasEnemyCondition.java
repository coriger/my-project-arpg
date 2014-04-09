/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-13  上午11:43:33
 */
package com.road.arpg.module.fight.ai.living.condition;

import java.util.Collections;
import java.util.List;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingConAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiConditionType;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.objects.npc.Monster;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.type.HurtRangeType;
import com.road.arpg.module.fight.util.P2LivingComparator;

/**
 * @author shandong.su
 */
@AiLivingConAnnotation(type = LivingAiConditionType.ATTACK_RANK_ANY_ENEMY, aiType = AiType.LIVING, desc = "范围内时候有敌人")
public class RangHasEnemyCondition extends LivingAiCondition {

    @Override
    public Boolean actionExecut(Event context) {
        Living living = (Living) context.getSource();
        if (living instanceof Monster) {
            Monster monster = (Monster) living;
            //将视野范围内的怪物排序
            List<Living> enemys = monster
                            .getBaseScene()
                            .getInjuryDetermine()
                            .getRangeLivings(HurtRangeType.OVAL,
                                            monster.getX(), monster.getY(), 50,
                                            50);
            if (enemys.size() == 0) {
                return false;
            }
            Collections.sort(enemys, new P2LivingComparator(monster.getPos()));
            for (int i = 0; i < enemys.size(); i++) {
                if (!monster.isFriendly(enemys.get(i))) {
                    monster.getStContext().setLockLiving(enemys.get(i));
                    return true;
                }
            }
        }
        return false;
    }

}
