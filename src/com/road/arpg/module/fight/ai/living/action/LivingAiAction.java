/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.ai.living.action;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.trigger.ActionNode;
import com.road.arpg.module.fight.trigger.AiType;

/**
 * 生物的动作节点
 * 
 * @author shandong.su
 */
public abstract class LivingAiAction extends ActionNode<Event> {
    /**
     * 
     */
    public AiType getAiType() {
        return AiType.LIVING;
    }

}
