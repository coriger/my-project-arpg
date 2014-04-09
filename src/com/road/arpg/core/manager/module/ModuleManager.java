/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:56:15
 */
package com.road.arpg.core.manager.module;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.road.arpg.core.IManager;
import com.road.arpg.core.annotation.Module;
import com.road.arpg.core.util.LogUtil;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 模块管理器.负责对系统所有模块进行管理.
 * 
 * @author Dream.xie
 */
@SuppressWarnings("unchecked")
public final class ModuleManager implements IManager {
    /**
     * 
     */
    private static ModuleManager instance = new ModuleManager();
    /**
     * 模块缓存表
     */
    private Map<Integer, IModule> modules = new HashMap<>();

    /**
     * 构造函数.
     * 
     */
    private ModuleManager() {
    }

    /**
     * 
     * @return
     */
    public static ModuleManager getInstance() {
        return instance;
    }

    /**
     * 根据模块名获取模块
     * 
     * @param moudleName
     * @return
     */
    public <T> T getModule(ModuleType moduleType) {
        return (T) modules.get(moduleType.ordinal());
    }

    /**
     * 根据模块名获取模块
     * 
     * @param moudleName
     * @return
     */
    public Collection<IModule> getAllModule() {
        return modules.values();
    }

    /**
     * 开始
     */
    @Override
    public void start() throws Exception {
        //module主目录
        File allModuleDir = new File(ProjectPathUtil.getBinDirPath()
                        + File.separator + "main" + File.separator + "com"
                        + File.separator + "road" + File.separator + "arpg"
                        + File.separator + "module");
        //获得所有module目录
        File[] moduleDirs = allModuleDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        //获得Module的class文件
        List<File> moduleFiles = new ArrayList<File>();
        for (File moduleDir : moduleDirs) {
            moduleFiles.addAll(Arrays.asList(moduleDir
                            .listFiles(new FileFilter() {
                                @Override
                                public boolean accept(File pathname) {
                                    return pathname.getName().toLowerCase()
                                                    .endsWith("module.class");
                                }
                            })));
        }

        //加载class文件
        List<Class<IModule>> classes = new ArrayList<Class<IModule>>(
                        moduleFiles.size());
        String fileName = null;
        for (File moduleFile : moduleFiles) {
            fileName = moduleFile.getAbsolutePath();
            fileName = fileName.substring(fileName.indexOf("main") + 5,
                            fileName.length() - 6);
            fileName = fileName.replaceAll("[/\\\\]", ".");
            classes.add((Class<IModule>) Class.forName(fileName));
        }

        //启动Module
        IModule moduleInstance = null;
        Module moduleAnnotation = null;
        ModuleType type = null;
        for (Class<?> clazz : classes) {
            moduleAnnotation = clazz.getAnnotation(Module.class);
            if (moduleAnnotation != null) {
                type = moduleAnnotation.type();
                moduleInstance = (IModule) clazz.newInstance();
                moduleInstance.start();
                modules.put(type.ordinal(), moduleInstance);
            }
        }
    }

    /**
     * 停止
     */
    @Override
    public void stop() throws Exception {
        for (Map.Entry<Integer, IModule> entry : modules.entrySet()) {
            long startTime = System.currentTimeMillis();
            entry.getValue().stop();
            LogUtil.info(String.format(
                            "===========停止模块【%s】，停止用了%dms===========",
                            ModuleType.values()[entry.getKey()].toString(),
                            System.currentTimeMillis() - startTime));
        }
    }

    /**
     * 模块类型
     * 
     * @author Dream.xie
     */
    public enum ModuleType {
        /**
         * 聊天
         */
        CHAT,
        /**
         * 战斗
         */
        FIGHTER,
        /**
         * 聊天代理
         */
        CHAT_PROXY,
        /**
         * 背包
         */
        BAG,
        /**
         * 任务
         */
        TASK,
        /**
         * 好友
         */
        FRIEND,
        /**
         * 杂项
         */
        MISC;
    }
}
