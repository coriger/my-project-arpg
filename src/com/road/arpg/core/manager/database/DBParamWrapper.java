/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:56:44
 */
package com.road.arpg.core.manager.database;

import java.util.HashMap;
import java.util.Map;

/**
 * sql脚本参数的包装类
 * 
 * @author Dream.xie
 */
public class DBParamWrapper {
    /**
     * 参数
     */
    private Map<Integer, DBParameter> params = null;
    /**
     * 
     */
    private int p = 0;

    /**
     * 
     */
    public DBParamWrapper() {
        this.params = new HashMap<Integer, DBParameter>();
    }

    /**
     * 添加一个参数
     * 
     * @param type
     *            参数的类型
     * @param o
     *            参数的值
     */
    public void put(int type, Object o) {
        params.put(++p, new DBParameter(type, o));
    }

    /**
     * 
     * @return
     */
    public Map<Integer, DBParameter> getParams() {
        return params;
    }

    /**
     * 重新初始化该sql参数包装类
     */
    public void clear() {
        params.clear();
        p = 0;
    }

}
