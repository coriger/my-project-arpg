/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.living.condition;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingConAnnotation;
import com.road.arpg.module.fight.trigger.AiBaseNodeFactory;

/**
 * @author : shandong.su
 * @version 生物Ai条件工厂
 */
public class AiLivingConditionFactory extends
                AiBaseNodeFactory<Event, Boolean, AiLivingConAnnotation> {
    /**
     * 实例
     */
    private static final AiLivingConditionFactory INSTANCE = new AiLivingConditionFactory();

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.AiBaseNodeFactory#getAnnoClass()
     */
    @Override
    public Class<AiLivingConAnnotation> getAnnoClass() {
        return AiLivingConAnnotation.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.AiBaseNodeFactory#getPackPath()
     */
    @Override
    protected String getPackPath() {
        return "com.road.arpg.module.fight.ai.living.condition";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.AiBaseNodeFactory#getNodeType(java.lang.annotation.
     * Annotation)
     */
    @Override
    public Integer getNodeType(AiLivingConAnnotation annotation) {
        return annotation.type().getValue();
    }

    /**
     * 返回单例
     * 
     * @return
     */
    public static AiLivingConditionFactory getInstance() {
        return INSTANCE;
    }
}
