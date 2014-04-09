/**
 * Date: 2013-7-29
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.ai.living.action;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingActAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiActionType;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.type.LivingState;

/**
 * 
 * @author shandong.su
 */
@AiLivingActAnnotation(type = LivingAiActionType.FOLLOW_TARGET, aiType = AiType.LIVING, desc = "移动到目标点")
public class MoveToTargetAction extends LivingAiAction {

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.trigger.AiTreeNode#actionExecut(java.lang.Object)
     */
    @Override
    public Void actionExecut(Event context) {
        Living living = (Living) context.getSource();
        // 当前锁定的目标
        Living livingFollow = living.getStContext().getLockLiving();
        if (livingFollow == null) {
            living.setState(LivingState.RESTING);
            return null;
        }
        living.moveTo(livingFollow.getX(), livingFollow.getY());
        return null;
    }

}
