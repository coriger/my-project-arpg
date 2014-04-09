/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.scene;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.road.arpg.core.util.NamedThreadFactory;

/**
 * 场景管理器，静态类，不继承，不实例
 * 
 * @author shandong.su
 */
public final class SceneManager {
    /**
     * 场景id，递增
     */
    private AtomicInteger sceneID;

    /**
     * 场景map
     */
    private Map<Integer, BaseScene> scenes;

    /**
     * 线程服务器线程池
     */
    private ExecutorService executorService;

    /**
     * 私有化管理器构造方法
     */
    private SceneManager() {

    }

    /**
     * 场景管理器初始化
     * 
     * @return
     */
    public boolean init() {
        // 游戏驱动专用线程池
        executorService = Executors.newFixedThreadPool(5,
                        new NamedThreadFactory("GAMELOGIC-GAME-DRIVE-POOL",
                                        Thread.NORM_PRIORITY));
        scenes = new ConcurrentHashMap<Integer, BaseScene>();
        sceneID = new AtomicInteger(0);
        return true;
    }

    /**
     * 将线程提交到线程池
     * 
     * @return
     */
    public void submit(BaseScene scene) {
        executorService.execute(scene);
    }

    /**
     * 获取自增id，使用原子数据类型，无需同步
     * 
     * @return id
     */
    public int getSceneID() {
        return sceneID.getAndIncrement();
    }

    /**
     * 
     * @param sceneID
     */
    public BaseScene getScene(int sceneID) {
        return scenes.get(sceneID);
    }

    /**
     * 
     * @author shandong.su
     */
    private static class SingletionHandler {
        /**
         * 利用内部类，即做到了需要使用的时候才加载，又做到了加载线程安全
         */
        public static final SceneManager INSTANCE = new SceneManager();
    }

    /**
     * 
     * @return
     */
    public static SceneManager getInstance() {
        return SingletionHandler.INSTANCE;
    }
}
