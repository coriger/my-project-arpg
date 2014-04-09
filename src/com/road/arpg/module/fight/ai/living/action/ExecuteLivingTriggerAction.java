/**
 * Date: 2013-7-23
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.ai.living.action;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.config.LivingTriggerConfig;
import com.road.arpg.module.fight.ai.living.AiLivingActAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiActionType;
import com.road.arpg.module.fight.ai.living.condition.AiLivingConditionFactory;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.trigger.BaseTrigger;
import com.road.arpg.module.fight.trigger.TriggerInfo;

/**
 * 执行另外一个触发器
 * 
 * @author Cookie.hu
 */
@AiLivingActAnnotation(type = LivingAiActionType.LIVING_EXECUTE_TRIGGER, aiType = AiType.LIVING, desc = "执行另外一个触发器")
public class ExecuteLivingTriggerAction extends LivingAiAction {
    /**
     * 新的节点的节点编号
     */
    private int triggerID;
    /**
     * 节点触发器
     */
    private BaseTrigger<Living> trigger;

    /**
     * 
     */
    public ExecuteLivingTriggerAction() {
        super();
    }

    /**
     * @param triggerID
     */
    public ExecuteLivingTriggerAction(String triggerID) {
        super();
        this.triggerID = Integer.parseInt(triggerID);
        TriggerInfo triggerInfo = LivingTriggerConfig.getInstance().getConfig(
                        this.triggerID);
        trigger = new BaseTrigger<Living>(triggerInfo,
                        AiLivingConditionFactory.getInstance(),
                        AiLivingActionFactory.getInstance(), AiType.LIVING,
                        null);
    }

    @Override
    public Void actionExecut(Event context) {
        trigger.onEvent(context);
        return null;
    }

}
