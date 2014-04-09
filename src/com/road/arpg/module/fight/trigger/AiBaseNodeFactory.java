/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.road.arpg.core.util.ClassUtil;
import com.road.arpg.core.util.LogUtil;
import com.road.arpg.module.fight.ai.AiTreeNode;

/**
 * @param <T>
 * @param <M>
 * @param <N>
 * @author : shandong.su
 * @version Ai结点创建工厂
 */
public abstract class AiBaseNodeFactory<M, N, T extends Annotation> implements
                IAiNodeFactory<M, N> {

    /**
     * handle的实例，这里保存每个AiTreeNode的实例，如果能够重复利用的AiTreeNode就用这里的
     */
    private Map<Integer, AiTreeNode<?, ?>> handlesInstance = new HashMap<Integer, AiTreeNode<?, ?>>();

    /**
     * handle的class，有些类有参数可能需要实时创建
     */
    private Map<Integer, Class<?>> handlesClass = new HashMap<Integer, Class<?>>();

    /**
     * 
     * @return
     */
    public boolean init() {
        reload();
        return true;
    }

    /**
     * 
     */
    public void reload() {
        handlesInstance.clear();
        handlesClass.clear();
        int count = searchCommandHandlers(getPackPath(), getAnnoClass());
        LogUtil.info(String.format("total load %d gameConditions.", count));
    }

    /**
     * 取得注释的类型
     * 
     * @return
     */
    public abstract Class<T> getAnnoClass();

    /**
     * 取得包路径
     * 
     * @return
     */
    protected abstract String getPackPath();

    /**
     * 取得类型
     * 
     * @param annotation
     * @return
     */
    public abstract Integer getNodeType(T annotation);

    /**
     * 创建一个游戏条件
     * 
     * @param type
     *            类型
     * @param needNew
     *            是否需要重新创建
     * @param args
     *            构造函数的参数
     * @return
     */
    @Override
    public AiTreeNode<?, ?> createAiTreeNode(int type, boolean needNew,
                    String args) {
        // 如果不需要重新建的话，就用原有的
        if (!needNew) {
            return handlesInstance.get(type);
        }

        // 如果需要重新创建的话，就用附加参数构造一个
        Class<?> class1 = handlesClass.get(type);
        AiTreeNode<?, ?> baseNode = null;
        try {
            baseNode = (AiTreeNode<?, ?>) ClassUtil.newInstance(class1, args);
        } catch (Exception e) {
            LogUtil.error("BaseNodeFactory:Create new AiTreeNode err. nodePack: "
                            + getPackPath() + "nodeNum:" + type, e);
        }
        return baseNode;
    }

    /**
     * 查找加了GameCmdAnnotation标注的类作为游戏数据包处理器
     * 
     * @return
     */
    protected int searchCommandHandlers(String packPath, Class<T> class2) {
        int count = 0;
        Package pack = Package.getPackage(packPath);
        Set<Class<?>> allClasses = ClassUtil.getClasses(pack);
        for (Class<?> class1 : allClasses) {
            T annotation = class1.getAnnotation(class2);

            if (annotation != null) {
                try {
                    handlesClass.put(getNodeType(annotation), class1);
                    handlesInstance.put(getNodeType(annotation),
                                    (AiTreeNode<?, ?>) class1.newInstance());
                    count++;
                } catch (InstantiationException e) {
                    LogUtil.error("BaseNodeFactory:SearchCommandHandlers", e);
                } catch (IllegalAccessException e) {
                    LogUtil.error("BaseNodeFactory:SearchCommandHandlers", e);
                }
            }
        }
        return count;
    }
}
