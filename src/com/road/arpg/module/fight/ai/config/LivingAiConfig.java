/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.config;

import com.road.arpg.module.fight.ai.XMLConfigurationReader;
import com.road.arpg.module.fight.ai.entity.LivingAiInfo;

/**
 * @author : shandong.su
 * @version 战士的配置
 */
public final class LivingAiConfig extends AiConfig<LivingAiInfo> {

    /**
     * 
     */
    private static final LivingAiConfig INSTANCE = new LivingAiConfig();

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.config.AiConfig#getConfigFilePath()
     */
    @Override
    String getConfigFilePath() {
        return "./module/internalModule/fight/aiconfig/LivingAiConfig.xml";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.dota.ai.config.AiConfig#readConfig(java.lang.String)
     */
    @Override
    boolean readConfig(String filePath) {
        return XMLConfigurationReader.readLivingAiConfigs(configs, filePath);
    }

    /**
     * 返回实例
     * 
     * @return
     */
    public static LivingAiConfig getInstance() {
        return INSTANCE;
    }
}
