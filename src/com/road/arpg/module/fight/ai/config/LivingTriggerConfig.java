/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.config;

import java.util.Map;

import com.road.arpg.module.fight.ai.XMLConfigurationReader;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.trigger.TriggerInfo;

/**
 * @author : shandong.su
 * @version Living触发器配置
 */
public final class LivingTriggerConfig extends AiConfig<TriggerInfo> {
    /**
     * 实例
     */
    private static final LivingTriggerConfig INSTANCE = new LivingTriggerConfig();

    @Override
    String getConfigFilePath() {
        return "./module/internalModule/fight/aiconfig/LivingTriggerConfig.xml";
    }

    @Override
    boolean readConfig(String filePath) {
        return XMLConfigurationReader.readTriggerConfig(configs, filePath,
                        AiType.LIVING);
    }

    /**
     * 获取实例
     * 
     * @return
     */
    public static LivingTriggerConfig getInstance() {
        return INSTANCE;
    }

    /**
     * 获取map
     * 
     * @return
     */
    public Map<Integer, TriggerInfo> getConfigs() {
        return this.configs;
    }
}
