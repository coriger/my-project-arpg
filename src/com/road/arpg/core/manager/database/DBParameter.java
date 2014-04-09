/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:56:44
 */
package com.road.arpg.core.manager.database;

/**
 * DB参数
 * 
 * @author Dream.xie
 */
public class DBParameter {
    /**
     * 
     */
    private String direction;
    /**
     * 
     */
    private int dbtype;
    /**
     * 
     */
    private Object info;

    /**
     * 设置存储过程访问参数
     * 
     * @param parameterDirection
     *            设置参数传入、传出,指定范围:ParameterDirection.Input;ParameterDirection.
     *            Output ; ParameterDirection.InputOutput
     * @param types
     *            设置参数数据类型，指定范围：Types.INTEGER;Types.VARCHAR;Types.DATE;
     * @param info
     *            设置参数值，类型为输入时，不能为空；
     */
    public DBParameter(String parameterDirection, int types, Object info) {
        this.direction = parameterDirection;
        this.dbtype = types;
        this.info = info;
    }

    /**
     * 
     * @param types
     *            设置参数数据类型，指定范围：Types.INTEGER;Types.VARCHAR;Types.DATE;
     * @param info
     *            设置参数值，类型为输入时，不能为空；
     */
    public DBParameter(int types, Object info) {
        this.dbtype = types;
        this.info = info;
    }

    /**
     * 设置存储过程访问参数（限为输出类型）
     * 
     * @param parameterDirection
     *            设置参数传入、传出,指定范围:ParameterDirection.Input;ParameterDirection.
     *            Output ;ParameterDirection.InputOutput
     * @param types
     *            设置参数数据类型，指定范围：Types.INTEGER;Types.VARCHAR;Types.DATE;
     */
    public DBParameter(String parameterDirection, int types) {
        this.direction = parameterDirection;
        this.dbtype = types;
    }

    /**
     * 
     * @return
     */
    public String getDirection() {
        return direction;
    }

    /**
     * 
     * @return
     */
    public int getDbtype() {
        return dbtype;
    }

    /**
     * 
     * @return
     */
    public Object getResult() {
        return info;
    }

    /**
     * 
     * @return
     */
    public void setResult(Object result) {
        this.info = result;
    }
}
