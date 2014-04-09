/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:56:15
 */
package com.road.arpg.core.manager.groovy;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.road.arpg.core.IManager;
import com.road.arpg.core.util.AtomicLock;

/**
 * groovy动态语言管理器。
 * 特别注意，经测试java调用groovy对象的效率是直接调用的1/20，而且解析一个groovy到java对象是非常消耗性能的
 * ，所以GroovyManager用一个缓存存放下来，预防外部直接调用解析获取对象
 * ，值得欣慰的是JAVA调用groovy和C++调用lua的性能比是一样的。都是1/20左右。所以我们使用groovy的过程应该是这样的：
 * 类静态属性->groovy对象，而不是java对象->groovy对象。
 * 
 * @author Dream.xie
 */
@SuppressWarnings("unchecked")
public final class GroovyManager implements IManager {
    /**
     * 
     */
    private static GroovyManager instance = new GroovyManager();

    /**
     * groovy类加载器.
     */
    private GroovyClassLoader loader = new GroovyClassLoader();

    /**
     * 原子锁
     */
    private AtomicLock lock = new AtomicLock();

    /**
     * groovy对象缓存
     */
    private Map<String, Object> groovyObjectMap = new HashMap<String, Object>();

    /**
     * 构造函数.
     * 
     */
    private GroovyManager() {

    }

    /**
     * 
     * @return
     */
    public static GroovyManager getInstance() {
        return instance;
    }

    /**
     * 
     */
    @Override
    public void start() throws Exception {
    }

    /**
     * 获取groovy对象
     * 
     * @param groovyFile
     * @return
     */
    public <T> T getGroovyObj(String groovyFile) throws Exception {
        if (!groovyObjectMap.containsKey(groovyFile)) {
            Class<T> groovyObjClass = loader.parseClass(new File(groovyFile));
            T t = (T) groovyObjClass.newInstance();
            try {
                lock.lock();
                groovyObjectMap.put(groovyFile, t);
            } finally {
                lock.unlock();
            }
            return t;
        } else {
            return (T) groovyObjectMap.get(groovyFile);
        }
    }

    /**
    * 
    */
    @Override
    public void stop() throws Exception {
    }

}
