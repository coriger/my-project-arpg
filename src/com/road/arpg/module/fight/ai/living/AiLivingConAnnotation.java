/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.arpg.module.fight.ai.living;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.road.arpg.module.fight.trigger.AiType;

/**
 * @author : shandong.su
 * @version 生物条件结点标记
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AiLivingConAnnotation {
    /**
     * 条件类型
     * 
     * @return
     */
    LivingAiConditionType type();

    /**
     * 描述
     * 
     * @return
     */
    String desc();

    /**
     * ai类型
     * 
     * @return
     */
    AiType aiType();
}
