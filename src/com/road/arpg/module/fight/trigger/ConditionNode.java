/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

import com.road.arpg.module.fight.ai.AiTreeNode;

/**
 * @author : shandong.su
 * @version
 * @param <M>
 *            条件结点，用来作为AI树的叶子结点
 */
public abstract class ConditionNode<M> extends AiTreeNode<M, Boolean> {
    /**
     * @param name
     * @param desc
     * @param errMsg
     */
    public ConditionNode(String name, String desc, String errMsg) {
        super(name, desc, errMsg);
    }

    /**
     * 
     */
    public ConditionNode() {
        super();
    }

    /**
     * 当前条件结点适用于哪种AI类型
     * 
     * @return
     */
    public abstract AiType getAiType();

}
