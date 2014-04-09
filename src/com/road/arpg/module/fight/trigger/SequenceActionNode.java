/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

import java.util.List;

import com.road.arpg.core.event.Event;
import com.road.arpg.core.util.RandomUtil;
import com.road.arpg.module.fight.ai.AiTreeNode;

/**
 * @author : shandong.su
 * @version action列表，所有的子结点都要执行
 */
public class SequenceActionNode extends AiTreeNode<Event, Void> {
    /**
     * 初始以and开始
     */
    private LogicType logicType = LogicType.AND;

    /**
     * 
     * @param logicType
     */
    public SequenceActionNode(LogicType logicType) {
        super();
        this.logicType = logicType;
    }

    /**
     * 
     * @param actionInfo
     * @param nodeFactory
     */
    public SequenceActionNode(ActionInfo actionInfo,
                    IAiNodeFactory<Event, Void> nodeFactory) {
        super();
        this.logicType = actionInfo.getLogicType();
        List<ActionInfo> sonActionInfos = actionInfo.getSonActions();
        if (sonActionInfos != null && sonActionInfos.size() > 0) {
            for (ActionInfo sonAction : sonActionInfos) {
                if (sonAction.getSonActions() != null) {
                    SequenceActionNode sonNode = new SequenceActionNode(
                                    sonAction, nodeFactory);
                    this.addSonNode(sonNode);
                } else {
                    boolean isNeedNew = sonAction.isNeedNew();
                    @SuppressWarnings("unchecked")
                    ActionNode<Event> sonNode = (ActionNode<Event>) nodeFactory
                                    .createAiTreeNode(sonAction.getActionID(),
                                                    isNeedNew,
                                                    sonAction.getParams());
                    this.addSonNode(sonNode);
                }
            }
        }
    }

    @Override
    public Void actionExecut(Event context) {
        if (sonNodes == null || sonNodes.size() == 0) {
            return null;
        }

        switch (logicType) {
            case AND:
                sequenceNode(context);
                break;
            case RANDOM:
                randomNode(context);
            default:
                sequenceNode(context);
                break;
        }
        return null;
    }

    /**
     * 随机执行一个action
     * 
     * @param context
     */
    private void randomNode(Event context) {
        int index = RandomUtil.notRandInt(sonNodes.size());
        AiTreeNode<Event, Void> sonNode = sonNodes.get(index);
        sonNode.actionExecut(context);
    }

    /**
     * 顺序执行每个action
     * 
     * @param context
     */
    private void sequenceNode(Event context) {
        for (AiTreeNode<Event, Void> sonNode : sonNodes) {
            sonNode.actionExecut(context);
        }
    }
}
