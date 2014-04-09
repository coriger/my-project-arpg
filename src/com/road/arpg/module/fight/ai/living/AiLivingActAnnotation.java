/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
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
 * @version 生物动作结点标记
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AiLivingActAnnotation {
    /***
     * ai動作類型
     * 
     * @return
     */
    LivingAiActionType type();

    /**
     * Ai类型
     * 
     * @return
     */
    AiType aiType();

    /**
     * ai描述
     * 
     * @return
     */
    String desc();
}
