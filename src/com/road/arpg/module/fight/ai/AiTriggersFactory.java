/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.ai;

import java.util.HashMap;
import java.util.Map;

import com.road.arpg.module.fight.ai.config.LivingAiConfig;
import com.road.arpg.module.fight.ai.config.LivingTriggerConfig;
import com.road.arpg.module.fight.ai.living.action.AiLivingActionFactory;
import com.road.arpg.module.fight.ai.living.condition.AiLivingConditionFactory;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.BaseTrigger;

/**
 * 在这个类中初始化所有类，并将所有的ai组合成多个无状态的行为树，以后每个玩家都取自己的树并注册到自己的事件上 所有的相同的行为共享一棵树
 * 
 * @author shandong.su
 */
public final class AiTriggersFactory {

    /**
     * 所有生物的行为树
     */
    private final Map<Integer, BaseTrigger<Living>> livingTriggers = new HashMap<Integer, BaseTrigger<Living>>();

    /**
     * 
     */
    private AiTriggersFactory() {
    }

    /**
     * 在这儿初始化所有ai相关的数据
     */
    public void init() {
        //人物条件加载
        AiLivingConditionFactory.getInstance().init();
        //人物动作类加载
        AiLivingActionFactory.getInstance().init();
        //触发器初始化
        LivingTriggerConfig.getInstance().init();

        LivingAiConfig.getInstance().init();
        //   Map<Integer, TriggerInfo> livingTriggerConfigs = LivingTriggerConfig.getInstance().getConfigs();

//        for ( Entry<Integer, TriggerInfo> entry : livingTriggerConfigs.entrySet())
//        {
//         //   BaseTrigger<Living> livingTrigger = new BaseTrigger<Living>();
//        }

    }

    /**
     * 单例
     * 
     * @author shandong.su
     */
    private static class SingletionHolder {
        /**
         * 
         */
        private static final AiTriggersFactory INSTANCE = new AiTriggersFactory();
    }

    /**
     * 返回实例
     * 
     * @return
     */
    public static AiTriggersFactory getInstance() {
        return SingletionHolder.INSTANCE;
    }

    /**
     * 返回一组行为
     * 
     * @param triggerID
     * @return
     */
    public BaseTrigger<Living> getTriggerByID(Integer triggerID) {
        return this.livingTriggers.get(triggerID);
    }
}
