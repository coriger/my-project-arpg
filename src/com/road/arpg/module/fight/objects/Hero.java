/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects;

import java.util.ArrayList;
import java.util.List;

import com.road.arpg.core.util.AtomicLock;
import com.road.arpg.module.fight.action.LivingAction;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 玩家类
 * 
 * @author shandong.su
 */
public class Hero extends Living {

    /**
     * 英雄独有的指令执行队列，如果英雄收到试用技能，移动等指令，都先进入此处
     */
    private List<LivingAction> actions;

    /**
     * 指令队列锁
     */
    private AtomicLock actionLock;

    /**
     * @param phyID
     * @param x
     * @param y
     * @param phyType
     * @param scene
     */
    public Hero(int phyID, int x, int y, PhysicsType phyType, BaseScene scene) {
        super(phyID, x, y, phyType, scene);
        actions = new ArrayList<>();
        actionLock = new AtomicLock();
    }

    /**
     * 给场景添加活动
     * 
     * @param action
     */
    public final void addAction(LivingAction action) {
        try {
            actionLock.lock();
            actions.add(action);
        } finally {
            actionLock.unlock();
        }
    }

    /**
     * 
     */
    @Override
    public void tick(long tickTime) {
        //先tick玩家上来的命令
        List<LivingAction> tempActions = null;
        try {
            actionLock.lock();
            tempActions = actions;
            actions = new ArrayList<LivingAction>();
        } finally {
            actionLock.unlock();
        }
        for (LivingAction action : tempActions) {
            long start = System.currentTimeMillis();
            action.execute(this, start);
            if (!action.isFinished(this, start)) {
                this.addAction(action);
            }
        }
        super.tick(tickTime);
    }

    @Override
    public PhysicsType getPhyType() {
        return PhysicsType.HERO;
    }
}
