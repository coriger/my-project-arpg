/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.road.arpg.core.event.Event;
import com.road.arpg.module.fight.ai.config.LivingAiConfig;
import com.road.arpg.module.fight.ai.config.LivingTriggerConfig;
import com.road.arpg.module.fight.ai.entity.LivingAiInfo;
import com.road.arpg.module.fight.ai.living.action.AiLivingActionFactory;
import com.road.arpg.module.fight.ai.living.condition.AiLivingConditionFactory;
import com.road.arpg.module.fight.map.LivingRectangle;
import com.road.arpg.module.fight.objects.state.AttackingState;
import com.road.arpg.module.fight.objects.state.DeadingState;
import com.road.arpg.module.fight.objects.state.IState;
import com.road.arpg.module.fight.objects.state.InactivingState;
import com.road.arpg.module.fight.objects.state.LivingStateContext;
import com.road.arpg.module.fight.objects.state.MovingState;
import com.road.arpg.module.fight.objects.state.ReturnBornState;
import com.road.arpg.module.fight.objects.state.SleepingState;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.skill.SkillHandler;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.trigger.BaseTrigger;
import com.road.arpg.module.fight.trigger.TriggerInfo;
import com.road.arpg.module.fight.type.LivingEventType;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.type.PhysicsType;
import com.road.arpg.module.fight.util.Point;
import com.road.arpg.module.fight.util.PointHelper;

/**
 * 生物类
 * 
 * @author shandong.su
 */
public abstract class Living extends Physic {

    /**
     * 追击的最大距离
     */
    public static final int MAX_FOLLOW_DISTANCE = 200;

    /**
     * 状态存储器
     */
    private static final Map<LivingState, IState> STATE_MAP = new HashMap<>();
    /**
     * 状态机上下文
     */
    protected LivingStateContext stContext;

    /**
     * 玩家当前状态
     */
    protected IState curState;

    /**
     * 生物的ai编号
     */
    protected int livingAiID;

    /**
     * 生物的矩形
     */
    protected LivingRectangle livingRect;

    /**
     * 当前状态类型
     */
    private LivingState livingState;
    /**
     * 行为树节点
     */
    private Map<Integer, List<BaseTrigger<Living>>> triGroup = new HashMap<>();

    /**
     * 
     */
    private BaseScene scene;

    /**
     * 技能处理器
     */
    private SkillHandler skillHandler;
    /**
     * 血量
     */
    private int hp;

    /**
     * 最大血量
     */
    private int hpMax;

    /**
     * 静态加载生物的所有状态机
     */
    static {
        STATE_MAP.put(LivingState.DEAD, new DeadingState());
        STATE_MAP.put(LivingState.MOVING, new MovingState());
        STATE_MAP.put(LivingState.INACTIVE, new InactivingState());
        STATE_MAP.put(LivingState.RESTING, new SleepingState());
        STATE_MAP.put(LivingState.ATTACKING, new AttackingState());
        STATE_MAP.put(LivingState.RETURNBORN, new ReturnBornState());
    }

    /**
     * @param phyID
     * @param x
     * @param y
     */
    public Living(int phyID, int x, int y, PhysicsType phyType, BaseScene scene) {
        super(phyID, x, y);
        this.stContext = new LivingStateContext(this);
        this.livingRect = new LivingRectangle(30, 60, this);
        this.scene = scene;
        this.stContext.setBornPoint(new Point(x, y));
        this.skillHandler = new SkillHandler(this);
    }

    /**
     * 生物初始化
     */
    public void init() {
    }

    /**
     * 挂载初始Ai触发器
     * 
     * @param triggerMap
     *            所有事件对应的
     */
    public void mountTrigger(
                    Map<LivingEventType, Map<Integer, String>> triggerMap) {
        if (triggerMap != null && triggerMap.size() > 0) {
            for (LivingEventType eventType : triggerMap.keySet()) {
                Map<Integer, String> triggerIDs = triggerMap.get(eventType);
                for (Entry<Integer, String> triggerEntry : triggerIDs
                                .entrySet()) {
                    int triggerID = triggerEntry.getKey();
                    String params = triggerEntry.getValue();
                    TriggerInfo triggerInfo = LivingTriggerConfig.getInstance()
                                    .getConfig(triggerID);
                    BaseTrigger<Living> trigger = new BaseTrigger<Living>(
                                    triggerInfo,
                                    AiLivingConditionFactory.getInstance(),
                                    AiLivingActionFactory.getInstance(),
                                    AiType.LIVING, params);
                    trigger.mount(this, eventType.getValue());
                    // 添加到触发器组
                    List<BaseTrigger<Living>> triGroup = this.triGroup
                                    .get((int) eventType.getValue());
                    if (triGroup == null) {
                        triGroup = new ArrayList<BaseTrigger<Living>>();
                        this.triGroup.put((int) eventType.getValue(), triGroup);
                    }
                    triGroup.add(trigger);
                }
            }
        }
    }

    /**
     * 每帧操作
     * 
     * @param tickTime
     */
    public void tick(long tickTime) {
        this.curState.updateFrame(this, tickTime);
    }

    /**
     * @param livingAiID
     */
    public void initAiConfig(int livingAiID) {
        // 配置Ai
        LivingAiInfo livingAiInfo = LivingAiConfig.getInstance().getConfig(
                        livingAiID);
        if (livingAiInfo != null) {
            this.livingAiID = livingAiID;
            Map<LivingEventType, Map<Integer, String>> triggerMap = livingAiInfo
                            .getLivingTriggers();
            if (triggerMap != null) {
                mountTrigger(triggerMap);
            }
        }
    }

    /**
     * 设置当前状态
     * 
     * @param livingState
     */
    public void setState(LivingState livingState) {
        this.livingState = livingState;
        if (Living.STATE_MAP.containsKey(livingState)) {
            this.curState = (Living.STATE_MAP.get(livingState));
        } else {
            this.curState = (STATE_MAP.get(LivingState.RESTING));
        }
        curState.enter(this, this.getBaseScene().getTick());
    }

    /**
     * 
     * @param skillID
     */
    public void useSkill(short skillID, long tickTime) {
        this.curState.releaseSkill(this, tickTime, skillID, null);
    }

    /**
     * 生物移动
     * 
     * @param tick
     * 
     * @param endX
     */
    public void moveTo(int endX, int endY) {
        int timeLong = PointHelper.calMove(endX, endY, DEFAULT_SPEED, pos);
        curState.moving(this, endX, endY, timeLong);
    }

    /**
     * 玩家走一步
     */
    public void moveStep(long timeLong) {
        moveStep(timeLong, DEFAULT_SPEED);
    }

    /**
     * 
     * @param timeLong
     * @param speed
     */
    public void moveStep(long timeLong, float speed) {
        float len = speed * timeLong / (BaseScene.MOVE_INTERVAL);

        int disSquart = stContext.getEndPointX();
        int y = stContext.getEndPointY();
        disSquart = (disSquart - pos.getX()) * (disSquart - pos.getX())
                        + (y - pos.getY()) * (y - pos.getY());
        Point point = new Point(pos);
        // 如果超出了终点，那就到达终点吧
        if (disSquart <= len * len) {
            pos.setX(stContext.getEndPointX());
            pos.setY(stContext.getEndPointY());
        } else {
            moveTheLength(this, (int) len, stContext.getEndPointX(), y,
                            disSquart);
        }
        //触发下移动事件
        this.onMoving(point);
    }

    /**
     * 
     * @param point
     * @param length
     * @param targetX
     * @param targetY
     * @param length2
     */
    private void moveTheLength(Living living, int length, int targetX,
                    int targetY, int length2) {
        Point point = living.getPos();
        int len = (int) Math.sqrt(length2);
        point.setX((int) ((targetX - point.getX()) / (float) len * length + point
                        .getX()));
        point.setY((int) ((targetY - point.getY()) / (float) len * length + point
                        .getY()));
        living.getLivingRect().setLocation(pos.getX(), pos.getY());
    }

    /**
     * 移动事件,触发移动事件，让四叉树重排，模块重排
     * 
     * @param pos
     */
    public void onMoving(Point pos) {
        this.notifyListeners(new Event(this, LivingEventType.MOVING.getValue(),
                        pos));
    }

    /**
     * 
     */
    public void onResting() {
        this.notifyListeners(LivingEventType.UPDATE_REST.getValue());
    }

    /**
     * 是否为友方单位
     * 
     * @param living
     * @return
     */
    public final boolean isFriendly(Living living) {
        //TODO 先不写具体的实现，等组队，公会，宠物，家将系统做起来，再写逻辑
        if (living.getClass().equals(this.getClass())) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前玩家的技能handler
     * 
     * @return
     */
    public final SkillHandler getSkillHandler() {
        return this.skillHandler;
    }

    /**
     * @return the hp
     */
    public final int getHp() {
        return hp;
    }

    /**
     * @param hp
     *            the hp to set
     */
    public final void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return the hpMax
     */
    public final int getHpMax() {
        return hpMax;
    }

    /**
     * @param hpMax
     *            the hpMax to set
     */
    public final void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    /**
     * @return the livingRect
     */
    public LivingRectangle getLivingRect() {
        return livingRect;
    }

    /**
     * @param livingRect
     *            the livingRect to set
     */
    public void setLivingRect(LivingRectangle livingRect) {
        this.livingRect = livingRect;
    }

    /**
     * 
     */
    public BaseScene getBaseScene() {
        return this.scene;
    }

    /**
     * @return the livingStateType
     */
    public LivingState getCurStateType() {
        return livingState;
    }

    /**
     * @return the stContext
     */
    public LivingStateContext getStContext() {
        return stContext;
    }

    /**
     * @param stContext
     *            the stContext to set
     */
    public void setStContext(LivingStateContext stContext) {
        this.stContext = stContext;
    }

    /**
     * 
     */
    public String toString() {
        return " phyID:" + this.getPhyID() + " X:" + this.getX() + " Y:"
                        + this.getY();
    }
}
