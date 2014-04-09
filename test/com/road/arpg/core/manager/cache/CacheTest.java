/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-10  下午3:51:01
 */
package com.road.arpg.core.manager.cache;

import java.util.Map;

import org.junit.Test;

import com.road.arpg.core.manager.cache.CacheManager.CacheType;

/**
 * @author Dream.xie
 */
public final class CacheTest {
    /**
     * 
     */
    public CacheTest() {

    }

    /**
     * 
     */
    @Test
    public void test() {
        CacheManager cacheManager = CacheManager.getInstance();
        try {
            cacheManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, CacheTest> test = (Map<String, CacheTest>) cacheManager
                        .getCache(CacheType.PLAYER);
        for (int i = 0; i < 10; i++) {
            test.put(i + "", new CacheTest());
        }

        System.out.println(test.size());

        for (Map.Entry<String, CacheTest> entry : test.entrySet()) {
            System.out.println(entry.getKey() + "-----" + entry.getValue());

        }
    }
}
