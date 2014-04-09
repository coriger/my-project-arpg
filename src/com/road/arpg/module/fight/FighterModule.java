/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-31  下午5:17:07
 */
package com.road.arpg.module.fight;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.road.arpg.core.annotation.Module;
import com.road.arpg.core.manager.module.ModuleManager.ModuleType;
import com.road.arpg.core.manager.module.NormalModule;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 战斗模块.
 * 
 * @author Dream.xie
 */
@Module(type = ModuleType.FIGHTER)
public class FighterModule extends NormalModule {

    /**
     * 游戏配置文件.
     */
    private static final String GAME_CFG_FILE = "game.xml";

    /**
     * 配置.
     */
    private FighterModuleConfig config = null;

    /**
     * @return the config
     */
    public FighterModuleConfig getConfig() {
        return config;
    }

    /**
     * 
     */
    @Override
    public void start() throws Exception {
        File configFile = new File(ProjectPathUtil.getConfigDirPath()
                        + File.separator + GAME_CFG_FILE);
        SAXReader reader = new SAXReader();
        Document document = reader.read(configFile);
        Element fighterModuleElmt = (Element) document
                        .selectSingleNode("//fighterModule/tick");
        int tick = Integer.parseInt(fighterModuleElmt.getTextTrim());
        this.config = new FighterModuleConfig();
        this.config.setTick(tick);
    }

    /**
     * 配置
     * 
     * @author Dream.xie
     */
    public static final class FighterModuleConfig {
        /**
         * tick
         */
        private Integer tick;

        /**
         * 
         */
        private FighterModuleConfig() {

        }

        /**
         * @return the tick
         */
        public Integer getTick() {
            return tick;
        }

        /**
         * @param tick
         *            the tick to set
         */
        private void setTick(Integer tick) {
            this.tick = tick;
        }
    }
}
