/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.ai.living.condition;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.trigger.ConditionNode;

/**
 * 生物的条件节点
 * 
 * @author shandong.su
 */
public abstract class LivingAiCondition extends ConditionNode<Event> {
    /**
     * 
     */
    public AiType getAiType() {
        return AiType.LIVING;
    }

}
