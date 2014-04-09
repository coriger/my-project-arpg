/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-13  上午11:37:18
 */
package com.road.arpg.module.fight.ai.living.condition;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingConAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiConditionType;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.AiType;

/**
 * @author shandong.su
 */
@AiLivingConAnnotation(type = LivingAiConditionType.CAN_ATTACK_LOCK_TARGET, aiType = AiType.LIVING, desc = "生物能否普通攻击(攻击锁定目标)")
public class CanAttackCondition extends LivingAiCondition {

    @Override
    public Boolean actionExecut(Event context) {
        Living living = (Living) context.getSource();
        return living.getSkillHandler().canNormalAttack();
    }

}
