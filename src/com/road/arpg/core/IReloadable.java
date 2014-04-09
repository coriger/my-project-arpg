/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-17  下午3:59:14
 */
package com.road.arpg.core;

/**
 * 可以重新加载的类需要实现的接口。切记：为了实现重新load，必须要让实现类有非private的构造方法。
 * 
 * @param <T>
 *            参数类型
 * @author Dream.xie
 */
public interface IReloadable<T> {

    /**
     * 重新加载.
     * 
     * @return
     */
    void reload(T arg) throws Exception;
}
