/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.ai.config;

import com.road.arpg.module.fight.ai.XMLConfigurationReader;
import com.road.arpg.module.fight.scene.info.SceneInfo;

/**
 * 场景怪物配置
 * 
 * @author shandong.su
 */
public final class SceneNpcConfig extends AiConfig<SceneInfo> {
    /**
     * 实例
     */
    private static final SceneNpcConfig INSTANCE = new SceneNpcConfig();

    /**
     * 
     */
    private SceneNpcConfig() {
    }

    /* (non-Javadoc)
     * @see com.road.arpg.fight.ai.config.AiConfig#getConfigFilePath()
     */
    @Override
    String getConfigFilePath() {
        return "./module/internalModule/fight/scene/xiangyang_scene.xml";
    }

    /* (non-Javadoc)
     * @see com.road.arpg.fight.ai.config.AiConfig#readConfig(java.lang.String)
     */
    @Override
    boolean readConfig(String filePath) {
        return XMLConfigurationReader.reloadSenceMonster(configs, filePath);
    }

    /**
     * 
     * @return
     */
    public static SceneNpcConfig getInstance() {
        return INSTANCE;
    }
}
