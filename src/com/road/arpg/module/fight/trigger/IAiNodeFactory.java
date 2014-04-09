/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

import com.road.arpg.module.fight.ai.AiTreeNode;

/**
 * @param <M>
 * @param <N>
 * @author : shandong.su
 * @version Ai结点的创建工厂
 */
public interface IAiNodeFactory<M, N> {
    /**
     * 创建ai节点
     * 
     * @param type
     * @param needNew
     * @param args
     * @return
     */
    AiTreeNode<?, ?> createAiTreeNode(int type, boolean needNew, String args);
}
