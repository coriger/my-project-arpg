/**
 * Date: 2013-7-20
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.ai.living.action;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingActAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiActionType;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.AiType;

/**
 * 攻击追击的生物
 * 
 * @author shandong.su
 */
@AiLivingActAnnotation(type = LivingAiActionType.ATTACK_LOCK_TARGET, aiType = AiType.LIVING, desc = "攻击锁定的目标")
public class NormalAttackAction extends LivingAiAction {
    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.trigger.AiTreeNode#actionExecut(java.lang.Object)
     */
    @Override
    public Void actionExecut(Event context) {
        Living living = (Living) context.getSource();
        living.useSkill((short) 1, living.getBaseScene().getTick());
        return null;
    }
}
