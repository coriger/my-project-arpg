/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.scene;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.road.arpg.core.manager.module.ModuleManager;
import com.road.arpg.core.manager.module.ModuleManager.ModuleType;
import com.road.arpg.core.util.AtomicLock;
import com.road.arpg.core.util.LogUtil;
import com.road.arpg.module.fight.FighterModule;
import com.road.arpg.module.fight.action.SceneAction;
import com.road.arpg.module.fight.lattice.LatticeHandler;
import com.road.arpg.module.fight.map.InjuryDetermine;
import com.road.arpg.module.fight.map.QuadTree;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.objects.PhysicFactory;
import com.road.arpg.module.fight.objects.npc.Monster;
import com.road.arpg.module.fight.scene.info.GameNPCInfo;
import com.road.arpg.module.fight.scene.info.SceneInfo;
import com.road.arpg.module.fight.type.LivingEventType;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 基础场景
 * 
 * @author shandong.su
 */
public class BaseScene implements Runnable {

    /** 服务器每帧的间隔 从配置文件读取 */
    public static final int FRAME_INTERVAL = ((FighterModule) ModuleManager
                    .getInstance().getModule(ModuleType.FIGHTER)).getConfig()
                    .getTick();

    /** 移动更新的帧率，生物移动时即服务器每三帧更新一次位置 */
    public static final int MOVE_INTERVAL = FRAME_INTERVAL;

    /**
     * 场景的指令队列
     */
    private List<SceneAction> actions;

    /**
     * 指令队列锁
     */
    private AtomicLock actionLock;

    /**
     * 场景中的生物id编号
     */
    private int phyID;

    /**
     * 场景id，有SceneManager生成 {@link SceneManager.sceneID}
     */
    private Integer sceneID;

    /**
     * 该场景的正在执行时间
     */
    private long tick;

    /**
     * 场景下次执行的时间
     */
    private long passTick;

    /**
     * 场景的基础信息，都是不变模板信息
     */
    private SceneInfo sceneInfo;

    /**
     * 分块管理
     */
    private LatticeHandler latticehandler;

    /**
     * 场景四叉树
     */
    private QuadTree quadTree;

    /**
     * 伤害判定
     */
    private InjuryDetermine injuryDetermie;

    /**
     * 
     */
    public BaseScene(SceneInfo sceneInfo) {
        phyID = 0;
        actionLock = new AtomicLock();
        actions = new ArrayList<>();
        tick = System.currentTimeMillis();
        sceneID = SceneManager.getInstance().getSceneID();
        init();
    }

    /**
     * 
     * @param sceneInfo
     * @return
     */
    private boolean init() {
        latticehandler = new LatticeHandler(this);
        Map<Integer, GameNPCInfo> monsters = sceneInfo.getMonsterMap();
        GameNPCInfo npcInfo = null;
        quadTree = new QuadTree(1, new Rectangle(this.sceneInfo.getWidth(),
                        this.sceneInfo.getHeigh()), null);
        for (Entry<Integer, GameNPCInfo> entry : monsters.entrySet()) {
            npcInfo = entry.getValue();
            Living living = this.addLiving(npcInfo.getAiID(),
                            npcInfo.getPosX(), npcInfo.getPosY(),
                            PhysicsType.MONSTER, npcInfo.getObjectID());
            switch (npcInfo.getObjectID()) {
                case 2:
                    living.setColor(Color.BLACK);
                    break;
                case 3:
                    living.setColor(Color.YELLOW);
                    break;
                case 4:
                    living.setColor(Color.RED);
                    break;
                case 5:
                    living.setColor(Color.BLUE);
                    break;
                case 6:
                    living.setColor(Color.GREEN);
                    break;
                default:
            }
            //给怪物设置这个，主要为了怪物复活
            ((Monster) living).setNpcInfo(npcInfo);
            living.setName(npcInfo.getName());
            living.setState(LivingState.INACTIVE);
        }
        injuryDetermie = new InjuryDetermine(this);
        return true;
    }

    @Override
    public final void run() {
        long startTime = System.currentTimeMillis();
        try {
            if (startTime > passTick) {
                try {
                    List<SceneAction> tempActions = null;
                    try {
                        actionLock.lock();
                        tempActions = actions;
                        actions = new ArrayList<SceneAction>();
                    } finally {
                        actionLock.unlock();
                    }
                    for (SceneAction action : tempActions) {
                        long start = System.currentTimeMillis();
                        action.execute(this, start);
                        if (!action.isFinished(this, start)) {
                            this.addAction(action);
                        }
                    }
                    this.latticehandler.tick(startTime);
                } finally {
                    this.passTick = startTime + BaseScene.FRAME_INTERVAL;
                }
            }
        } catch (Exception e) { //无论如何都需要catch, 避免线程崩溃
            LogUtil.error(" 场景线程发生异常", e);
        } finally {
            SceneManager.getInstance().submit(this);
        }
    }

    /**
     * 在地图上添加一个生物,只添加普通生物，英雄在Game对象初始化的时候已经单独添加
     * 
     * @param livingAiID
     *            AiID
     * @param x
     *            X坐标
     * @param y
     *            y坐标
     * @param type
     *            生物类型
     */
    public Living addLiving(int livingAiID, int x, int y, PhysicsType type,
                    int beanID) {
        Living living = (Living) PhysicFactory.createPhysics(x, y, type, this);
        if (living != null) {
            // 初始化AI配置
            living.initAiConfig(livingAiID);
        }
        this.latticehandler.addLiving(living);
        this.quadTree.insert(living.getLivingRect());
        //给生物加上移动后的四叉树监听器
        living.addListener(LivingEventType.MOVING.getValue(),
                        QuadTree.QUADTREE_LISTENER);
        return living;
    }

    /**
     * 给场景添加活动
     * 
     * @param action
     */
    public final void addAction(SceneAction action) {
        try {
            actionLock.lock();
            actions.add(action);
        } finally {
            actionLock.unlock();
        }
    }

    /**
     * @return the sceneId
     */
    public final Integer getSceneId() {
        return sceneID;
    }

    /**
     * @param sceneId
     *            the sceneId to set
     */
    public void setSceneId(Integer sceneId) {
        this.sceneID = sceneId;
    }

    /**
     * 返回一个生物id，这个生物id递增,每获取一次，递增一次
     * 
     * @return
     */
    public final int getPhyID() {
        return phyID++;
    }

    /**
     * @return the sceneInfo
     */
    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    /**
     * @return the quadTree
     */
    public QuadTree getQuadTree() {
        return quadTree;
    }

    /**
     * 
     * @return
     */
    public final LatticeHandler getLatticeHandler() {
        return this.latticehandler;
    }

    /**
     * 返回当前帧的开始执行时间
     * 
     * @return
     */
    public final long getTick() {
        return this.tick;
    }

    /**
     * 伤害判定处理
     */
    public final InjuryDetermine getInjuryDetermine() {
        return this.injuryDetermie;
    }
}
