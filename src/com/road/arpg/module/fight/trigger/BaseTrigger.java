/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

import com.road.arpg.core.event.Event;
import com.road.arpg.core.event.EventSource;
import com.road.arpg.core.event.IEventListener;
import com.road.arpg.core.util.LogUtil;

/**
 * @param <M>
 * @author : shandong.su
 * @version 触发器基类,一个触发器可以注册任意多个事件
 */
public class BaseTrigger<M extends EventSource> implements IEventListener ,
                ITrigger<M, Event> {

    /**
     * 条件列表
     */
    private SequenceConditionNode conditions;

    /** yes执行结点必须有 */
    private SequenceActionNode yesActions;

    /** no执行结点可能没有 */
    private SequenceActionNode noActions;

    /** 触发器参数 */
    private String params;

    /** 触发器类型，是注册游戏事件，还是注册玩家事件 */
    private AiType aiType;

    /**
     * 
     * @param triggerInfo
     * @param conNodeFactory
     * @param actNodeFactory
     * @param aiType
     * @param params
     */
    public BaseTrigger(TriggerInfo triggerInfo,
                    IAiNodeFactory<Event, Boolean> conNodeFactory,
                    IAiNodeFactory<Event, Void> actNodeFactory, AiType aiType,
                    String params) {
        super();
        this.yesActions = new SequenceActionNode(
                        triggerInfo.getYesActionInfo(), actNodeFactory);
        if (triggerInfo.getNoActionInfo() != null) {
            this.noActions = new SequenceActionNode(
                            triggerInfo.getNoActionInfo(), actNodeFactory);
        }

        this.conditions = new SequenceConditionNode(
                        triggerInfo.getConditionInfo(), conNodeFactory);
        this.aiType = aiType;
        this.setParams(params);
    }

    @Override
    public void onEvent(Event arg) {
        try {
            if (params != null && !params.isEmpty()) {
                arg.setTriParams(params);
            }
            // 事件作为上下文参数
            if (conditions.actionExecut(arg)) {
                yesActions.actionExecut(arg);
            } else {
                if (noActions != null) {
                    noActions.actionExecut(arg);
                }
                LogUtil.info(conditions.getErrMsg());
            }
        } catch (Throwable e) {
            e.printStackTrace();
            LogUtil.error("trigger respond err:", e);
        }
    }

    @Override
    public void addConditionNode(ConditionNode<Event> condition) {
        if (condition.getAiType() == this.aiType) {
            conditions.addSonNode(condition);
        } else {
            // 不能将不同AI类型的条件结点添加
            LogUtil.error("can not add " + condition.getAiType()
                            + "conditionNode to " + aiType + " trigger.");
        }
    }

    @Override
    public void addYesActionNode(ActionNode<Event> actionNode) {
        if (actionNode.getAiType() == this.aiType) {
            yesActions.addSonNode(actionNode);
        } else {
            // 不能将不同AI类型的action结点添加
            LogUtil.error("can not add " + actionNode.getAiType()
                            + "actionNode to " + aiType + " trigger.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.road.dota.ai.trigger.ITrigger#addNoActionNode(com.road.dota.util.
     * bt.ActionNode)
     */
    @Override
    public void addNoActionNode(ActionNode<Event> actionNode) {
        if (noActions != null && actionNode.getAiType() == this.aiType) {
            noActions.addSonNode(actionNode);
        } else {
            // 不能将不同AI类型的action结点添加
            LogUtil.error("can not add " + actionNode.getAiType()
                            + "actionNode to " + aiType + " trigger.");
        }
    }

    @Override
    public void mount(M host, int eventType) {
        if (host != null) {
            host.addListener(eventType, this);
        }
    }

    @Override
    public void unMount(M host, int eventType) {
        if (host != null) {
            host.removeListener(eventType, this);
        }
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(AiType type) {
        this.aiType = type;
    }

    /**
     * @return the type
     */
    public AiType getType() {
        return aiType;
    }

    /**
     * @return the params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(String params) {
        this.params = params;
    }
}
