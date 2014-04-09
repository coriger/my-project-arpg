/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-21  上午10:01:52
 */
package com.road.arpg.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.road.arpg.core.manager.module.ModuleManager;

/**
 * @author Dream.xie
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Module {
    /**
     * 模块类型
     * 
     * @return
     */
    ModuleManager.ModuleType type();

    /**
     * 如果是代理模块，这个表示真实模块的配置信息在game.xml里的xpath路径。
     */
    String proxyModuleCfgXpath() default "";
}
