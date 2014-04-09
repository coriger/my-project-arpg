/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:55:39
 */
package com.road.arpg.core.manager.cache;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import com.road.arpg.core.IManager;

/**
 * 缓存管理器,无状态单例类.对缓存进行管理.
 * 
 * @author Dream.xie
 */
@SuppressWarnings("unchecked")
public final class CacheManager implements IManager {
    /**
     * 
     */
    private static CacheManager instance = new CacheManager();

    /**
     * 
     */
    private net.sf.ehcache.CacheManager ehcacheManager = new net.sf.ehcache.CacheManager();
    /**
     * 缓存
     */
    private Map<CacheType, Map<?, ?>> allCache = null;

    /**
     * 构造函数
     */
    private CacheManager() {
    }

    /**
     * 
     * @return
     */
    public static CacheManager getInstance() {
        return instance;
    }

    /**
     * 获得连接缓存
     * 
     * @param type
     * 
     * @return
     */
    public Map<?, ?> getCache(CacheType type) {
        return allCache.get(type);
    }

    /**
     * 
     */
    @Override
    public void start() throws Exception {
        allCache = new HashMap<CacheManager.CacheType, Map<?, ?>>();
        //放入连接缓存.
        allCache.put(CacheType.CONNECTION, new ConcurrentHashMap<>());
        //Encache缓存.
        initEhcache();
    }

    /**
     * 
     */
    @Override
    public void stop() throws Exception {
        allCache.clear();
    }

    /**
     * 缓存类型
     * 
     * @author Dream.xie
     */
    public static enum CacheType {
        /**
         * 连接.
         */
        CONNECTION,
        /**
         * 玩家.
         */
        PLAYER;
    }

    /**
     * 
     */
    private void initEhcache() {
        //玩家缓存.
        PersistenceConfiguration persistenceConfiguration = new PersistenceConfiguration();
        persistenceConfiguration
                        .strategy(PersistenceConfiguration.Strategy.NONE);

        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName(CacheType.PLAYER.toString());
        cacheConfiguration.setMaxEntriesLocalHeap(10);
        cacheConfiguration
                        .setMemoryStoreEvictionPolicyFromObject(MemoryStoreEvictionPolicy.LRU);
        cacheConfiguration.addPersistence(persistenceConfiguration);

        Cache cache = new Cache(cacheConfiguration);
        ehcacheManager.addCache(cache);
        EhcacheCache<?, ?> ehcacheCache = new EhcacheCache<>(cache);
        allCache.put(CacheType.PLAYER, ehcacheCache);
    }

    /**
     * 
     * @author Dream.xie
     */
    private static final class EhcacheCache<K, V> extends AbstractMap<K, V> {

        /**
         * 
         */
        private Cache ehcache = null;

        /**
         * 
         * @param ehcache
         */
        private EhcacheCache(Cache ehcache) {
            this.ehcache = ehcache;
        }

        /**
         * 
         */
        @Override
        public int size() {
            return ehcache.getSize();
        }

        /**
         * 
         */
        @Override
        public V put(K key, V value) {
            Element element = new Element(key, value);
            ehcache.put(element);
            return value;
        }

        /**
         * 
         */
        @Override
        public V get(Object key) {
            Element element = ehcache.get(key);
            if (element != null) {
                return (V) element.getObjectValue();
            } else {
                return null;
            }
        }

        /**
         * 
         */
        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> result = new HashSet<>();
            Map.Entry<K, V> entryTmp = null;
            for (Map.Entry<Object, Element> entry : ehcache.getAll(
                            ehcache.getKeys()).entrySet()) {
                entryTmp = (Map.Entry<K, V>) new Entry<>(entry.getKey(), entry
                                .getValue().getObjectValue());
                result.add(entryTmp);
            }
            return result;
        }

        /**
         * 
         * @author Dream.xie
         */
        static class Entry<K, V> implements Map.Entry<K, V> {
            /**
             * 
             */
            final K key;
            /**
             * 
             */
            V value;

            /**
             * Creates new entry.
             */
            Entry(K k, V v) {
                value = v;
                key = k;
            }

            /**
             * 
             */
            public final K getKey() {
                return key;
            }

            /**
             * 
             */
            public final V getValue() {
                return value;
            }

            /**
             * 
             */
            public final V setValue(V newValue) {
                V oldValue = value;
                value = newValue;
                return oldValue;
            }

            /**
             * 
             */
            @Override
            public final boolean equals(Object o) {
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<K, V> e = (Map.Entry<K, V>) o;
                Object k1 = getKey();
                Object k2 = e.getKey();
                if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                    Object v1 = getValue();
                    Object v2 = e.getValue();
                    if (v1 == v2 || (v1 != null && v1.equals(v2))) {
                        return true;
                    }
                }
                return false;
            }

            /**
             * 
             */
            @Override
            public final int hashCode() {
                return (key == null ? 0 : key.hashCode())
                                ^ (value == null ? 0 : value.hashCode());
            }

            /**
             * 
             */
            public final String toString() {
                return getKey() + "=" + getValue();
            }

        }

    }
}
