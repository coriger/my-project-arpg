/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.living.action;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.living.AiLivingActAnnotation;
import com.road.arpg.module.fight.trigger.AiBaseNodeFactory;

/**
 * @author : shandong.su
 * @version 生物action工厂
 */
public class AiLivingActionFactory extends
                AiBaseNodeFactory<Event, Void, AiLivingActAnnotation> {
    /**
     * 实例
     */
    private static final AiLivingActionFactory INSTANCE = new AiLivingActionFactory();

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.AiBaseNodeFactory#getAnnoClass()
     */
    @Override
    public Class<AiLivingActAnnotation> getAnnoClass() {
        return AiLivingActAnnotation.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.AiBaseNodeFactory#getPackPath()
     */
    @Override
    protected String getPackPath() {
        return "com.road.arpg.module.fight.ai.living.action";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.AiBaseNodeFactory#getNodeType(java.lang.annotation.
     * Annotation)
     */
    @Override
    public Integer getNodeType(AiLivingActAnnotation annotation) {
        return annotation.type().getValue();
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static AiLivingActionFactory getInstance() {
        return INSTANCE;
    }
}
