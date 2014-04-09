/**
 * Date: 2013-7-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.ai.living.condition;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingConAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiConditionType;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.util.PointHelper;

/**
 * 生物能否普通攻击(攻击锁定目标) 被攻击之后
 * 
 * @author shandong.su
 */
@AiLivingConAnnotation(type = LivingAiConditionType.BE_HURT_CHANGE_LOCK_ENEMY, aiType = AiType.LIVING, desc = "每次被攻击都改变目标")
public class BeHurtChangeLockTargetCondition extends LivingAiCondition {

    @Override
    public Boolean actionExecut(Event context) {
        Living living = (Living) context.getSource();
        Living attackLiving = living.getStContext().getLockLiving();
        //如果没有目标，也就是第一次被攻击，那么开始追杀吧
        living.getStContext().setLockLiving(attackLiving);
        if (PointHelper.distance(living.getPos(), attackLiving.getPos()) < 50) {
            return true;
        }
        return false;
    }

}
