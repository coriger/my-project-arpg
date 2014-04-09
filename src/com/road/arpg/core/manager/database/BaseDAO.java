/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-26  下午5:41:37
 */
package com.road.arpg.core.manager.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.road.arpg.core.util.LogUtil;

/**
 * 数据库基础DAO，所有的DAO都应该是无状态的。
 * 
 * @param <V>
 * @author Dream.xie
 */
public abstract class BaseDAO<V> {
    /**
     * 数据库Helper
     */
    protected DBHelper dbhelper = null;

    /**
     * 
     * @param helper
     */
    public BaseDAO(DBHelper dbhelper) {
        this.dbhelper = dbhelper;
    }

    /**
     * 
     * @return
     */
    protected DBHelper getDBHelper() {
        return dbhelper;
    }

    /**
     * 根据脚本执行更新
     * 
     * @param sql
     *            查询的脚本
     * @param paramWrapper
     *            参数
     * @return
     */
    public boolean update(String sql, DBParamWrapper paramWrapper) {
        boolean result = false;
        result = dbhelper.executeUpdate(sql, paramWrapper) > -1;
        return result;
    }

    /**
     * 根据脚本执行查询操作，不带参数
     * 
     * @param sql查询的脚本
     * @return
     */
    public V query(String sql) {
        return query(sql, null);
    }

    /**
     * 根据脚本执行查询操作，带参数
     * 
     * @param sql
     *            查询的脚本
     * @param paramWrapper
     *            参数
     * @return
     */
    public V query(String sql, DBParamWrapper paramWrapper) {
        V result = dbhelper.executeQuery(sql, paramWrapper,
                        new DataReader<V>() {
                            @Override
                            public V readData(ResultSet rs, Object... objects)
                                            throws Exception {
                                if (rs.last()) {
                                    return BaseDAO.this.rsToEntity(rs);
                                }
                                return null;
                            }
                        });
        return result;
    }

    /**
     * 根据脚本执行查询操作,不带参数
     * 
     * @param sql
     *            查询的脚本
     * @return
     */
    public List<V> queryList(String sql) {
        return queryList(sql, null);
    }

    /**
     * 根据脚本执行查询操作
     * 
     * @param sql
     *            sql 查询的脚本
     * @param paramWrapper
     *            参数
     * @return 返回查询结果对象集合
     */
    public List<V> queryList(String sql, DBParamWrapper paramWrapper) {
        List<V> entitis = dbhelper.executeQuery(sql, paramWrapper,
                        new DataReader<List<V>>() {
                            @Override
                            public List<V> readData(ResultSet rs,
                                            Object... objects) throws Exception {
                                return BaseDAO.this.rsToEntityList(rs);
                            }
                        });
        return entitis;
    }

    /**
     * 根据脚本查询实体，返回key字段作为hash的Map
     * 
     * @param sql
     *            脚本
     * @param paramWrapper
     *            参数
     * @param key
     *            列名，它的值作为哈希的key值 如果key只有一个的时候，返回的泛型可以是任意类型，但是key传多个进行的时候，
     *            返回的泛型只能是String
     * @return
     */
    @SuppressWarnings("unchecked")
    public <K> Map<K, V> queryMap(String sql, DBParamWrapper paramWrapper,
                    Object... key) {
        Map<K, V> resultMap = dbhelper.executeQuery(sql, paramWrapper,
                        new DataReader<Map<K, V>>() {
                            @Override
                            public Map<K, V> readData(ResultSet rs,
                                            Object... objects) throws Exception {
                                Map<K, V> resultMap = new HashMap<K, V>();
                                while (rs.next()) {
                                    if (objects.length > 1) {
                                        String hashKey = "";
                                        for (Object string : objects) {
                                            hashKey += rs.getObject((String) string)
                                                            + "_";
                                        }
                                        hashKey = hashKey.substring(0,
                                                        hashKey.length() - 1);
                                        resultMap.put((K) hashKey,
                                                        rsToEntity(rs));
                                    } else if (objects.length == 1) {
                                        resultMap.put((K) rs
                                                        .getObject((String) objects[0]),
                                                        rsToEntity(rs));
                                    }
                                }
                                return resultMap;
                            }
                        }, key);

        return resultMap;
    }

    /**
     * 将ResultSet转换成List
     * 
     * @param rs
     * @return
     */
    protected List<V> rsToEntityList(ResultSet rs) {
        List<V> entitis = new ArrayList<V>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    V entity = rsToEntity(rs);
                    entitis.add(entity);
                }
            } catch (Exception e) {
                LogUtil.error("Resultset转成实体出错", e);
            }
        }
        return entitis;
    }

    /**
     * 将resultset转为实体对象
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    protected abstract V rsToEntity(ResultSet rs) throws SQLException;

}
