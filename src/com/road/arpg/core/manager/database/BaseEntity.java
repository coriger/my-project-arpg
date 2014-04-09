/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-26  下午9:18:13
 */
package com.road.arpg.core.manager.database;

/**
 * @param <K>
 *            主键
 * @author Dream.xie
 */
public abstract class BaseEntity<K> {
    /**
     * 主键
     */
    private K id;

    /**
     * 操作
     */
    private EntityOperation operation = EntityOperation.NONE;

    /**
     * 
     */
    public BaseEntity() {

    }

    /**
     * @return the id
     */
    public K getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(K id) {
        this.id = id;
    }

    /**
     * @return the operation
     */
    public EntityOperation getOperation() {
        return operation;
    }

    /**
     * @param operation
     *            the operation to set
     */
    public void setOperation(EntityOperation operation) {
        this.operation = operation;
    }

    /**
     * 
     * @author Dream.xie
     */
    public static enum EntityOperation {
        /**
         * 无
         */
        NONE,
        /**
         * INSERT
         */
        INSERT,
        /**
         * 
         */
        UPDATE,
        /**
         * 
         */
        DELETE
    }
}
