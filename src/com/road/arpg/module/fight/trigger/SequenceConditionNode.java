/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */

import java.util.List;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.AiTreeNode;

/**
 * @author : shandong.su
 * @version 顺序结点,根据逻辑关系字段决定怎么样进行判定
 */
public class SequenceConditionNode extends AiTreeNode<Event, Boolean> {
    /**
     * 
     */
    private LogicType logicType = LogicType.AND;

    /**
     * 
     * @param logicType
     */
    public SequenceConditionNode(LogicType logicType) {
        super();
        this.logicType = logicType;
    }

    /**
     * @param conditionInfo
     */
    @SuppressWarnings("unchecked")
    public SequenceConditionNode(ConditionInfo conditionInfo,
                    IAiNodeFactory<Event, Boolean> nodeFactory) {
        super();
        this.logicType = conditionInfo.getLogicType();
        setErrMsg(conditionInfo.getErrMsg());
        List<ConditionInfo> sonConditionInfos = conditionInfo.getSonInfo();
        if (sonConditionInfos != null && sonConditionInfos.size() > 0) {
            for (ConditionInfo sonCondition : sonConditionInfos) {
                // 如果有子结点，那么当前结点必须创建
                if (sonCondition.getSonInfo() != null) {
                    SequenceConditionNode sonNode = new SequenceConditionNode(
                                    sonCondition, nodeFactory);
                    this.addSonNode(sonNode);
                } else {
                    boolean isNeedNew = sonCondition.isNeedNew();
                    ConditionNode<Event> sonNode = (ConditionNode<Event>) nodeFactory
                                    .createAiTreeNode(sonCondition
                                                    .getConditionID(),
                                                    isNeedNew,
                                                    sonCondition.getParams());
                    this.addSonNode(sonNode);
                }
            }
        }
    }

    @Override
    public Boolean actionExecut(Event context) {
        // 如果子结点为0，说明没有子结点，即无条件触发
        if (sonNodes == null || sonNodes.size() == 0) {
            return true;
        }

        boolean result = false;
        switch (logicType) {
            case AND:
                result = andNode(context);
                break;
            case OR:
                result = orNode(context);
                break;
            default:
                result = andNode(context);
                break;
        }
        return result;
    }

    /**
     * and关系处理子结点，必须只要有一个false,就返回false;
     * 
     * @param context
     * @return
     */
    private boolean andNode(Event context) {
        for (AiTreeNode<Event, Boolean> sonNode : sonNodes) {
            // 只要找到一个执行失败的子结点，则返回失败
            if (!sonNode.actionExecut(context)) {
                setErrMsg(sonNode.getErrMsg());
                return false;
            }
        }
        return true;
    }

    /**
     * or关系处理子结点，只要有一个返回true，就返回true
     * 
     * @param context
     * @return
     */
    private boolean orNode(Event context) {
        for (AiTreeNode<Event, Boolean> sonNode : sonNodes) {
            // 只要找到一个执行成功的子结点，则返回成功
            if (sonNode.actionExecut(context)) {
                return true;
            }
        }
        return false;
    }
}
