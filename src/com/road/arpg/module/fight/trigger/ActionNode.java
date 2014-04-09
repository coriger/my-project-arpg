/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

import com.road.arpg.module.fight.ai.AiTreeNode;

/**
 * @param <M>
 * @author : shandong.su
 * @version 执行结点，不关注返回值
 */
public abstract class ActionNode<M> extends AiTreeNode<M, Void> {
    /**
     * 当前条件结点适用于哪种AI类型
     * 
     * @return
     */
    public abstract AiType getAiType();
}
