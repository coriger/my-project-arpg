/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  上午10:48:39
 */
package com.road.arpg.core;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 游戏服务器启动类。
 * 
 * @author Dream.xie
 */
@SuppressWarnings("unchecked")
public final class ServerStarter {

    /**
     * 默认的构造方法
     */
    private ServerStarter() {

    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        new ServerStarter().start();
    }

    /**
     * 启动
     */
    private void start() {
        try {
            //初始化自定义类加载器，然后加载包
            final ClassLoader parent = findParentClassLoader();
            File libDir = new File(ProjectPathUtil.getLibDirPath());
            URL[] urls = new URL[] { libDir.toURI().toURL() };
            ArpgClassLoader loader = new ArpgClassLoader(parent, urls);
            Thread.currentThread().setContextClassLoader(loader);
            Class<GameServer> gameServerClass = (Class<GameServer>) loader
                            .loadClass("com.road.arpg.core.GameServer");
            Method method = gameServerClass.getMethod("getInstance");
            Object gameServer = method.invoke(gameServerClass);
            method = gameServerClass.getMethod("start");
            method.invoke(gameServer);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * 查找最适合的父类classloader
     * 
     * @return
     */
    private ClassLoader findParentClassLoader() {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        if (parent == null) {
            parent = this.getClass().getClassLoader();
            if (parent == null) {
                parent = ClassLoader.getSystemClassLoader();
            }
        }
        return parent;
    }

}
