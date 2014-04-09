/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : shandong.su
 * @param <T>
 * @version ai相关配置的基类
 */
public abstract class AiConfig<T> {
    /**
     * 配置存储
     * 
     * @param <T>
     */
    protected Map<Integer, T> configs = new HashMap<>();

    /**
     * 获取配置文件路径
     * 
     * @return
     */
    abstract String getConfigFilePath();

    /**
     * 读取配置文件
     * 
     * @param filePath
     * @return
     */
    abstract boolean readConfig(String filePath);

    /**
     * 
     * @return
     */
    public boolean init() {
        return reload();
    }

    /**
     * 加载所有副本的配置信息
     * 
     * @return
     */
    public boolean reload() {
        String filePath = getConfigFilePath();
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        return readConfig(filePath);
    }

    /**
     * 通过configID得到PVE配置信息
     * 
     * @param configID
     * @return
     */
    public T getConfig(int configID) {
        return configs.get(configID);
    }

}
