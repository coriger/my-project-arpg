/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects.npc;

import com.road.arpg.module.fight.bean.MonsterBean;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.scene.info.GameNPCInfo;
import com.road.arpg.module.fight.type.LivingEventType;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.type.PhysicsType;
import com.road.arpg.module.fight.util.Point;
import com.road.arpg.module.fight.util.PointHelper;

/**
 * 场景怪物,包括宠物
 * 
 * @author shandong.su
 */
public class Monster extends Robot {

    /**
     * 玩家的怪物地图文件配置信息
     */
    private GameNPCInfo npcInfo;

    /**
     * 怪物模板
     */
    private MonsterBean monsterBean;

    /**
     * 复活时间
     */
    private long revivalTime;

    /**
     * @param phyID
     * @param x
     * @param y
     * @param phyType
     * @param scene
     */
    public Monster(int phyID, int x, int y, PhysicsType phyType, BaseScene scene) {
        super(phyID, x, y, phyType, scene);
        this.stContext.setBornPoint(new Point(x, y));
        this.monsterBean = new MonsterBean();
        this.monsterBean.setEyeRang(1000);
        this.monsterBean.setMonsterID(1);
    }

    /**
     * 重载下怪物的移动，不让怪物移动出活动区，如果怪物和目标之间过大，也让怪物回到出生点
     */
    @Override
    public void moveTo(int endX, int endY) {
        if (PointHelper.distanceSquare(this.getPos(), endX, endY) > Living.MAX_FOLLOW_DISTANCE
                        * Living.MAX_FOLLOW_DISTANCE
                        || PointHelper.distanceSquare(this.getPos(), this
                                        .getStContext().getBornPoint()) > Living.MAX_FOLLOW_DISTANCE
                                        * Living.MAX_FOLLOW_DISTANCE) {
            super.setState(LivingState.RETURNBORN);
        } else {
            super.moveTo(endX, endY);
        }
    }

    /**
     * 当没有目标的时候就激活lazzy事件，表示生物在没有目标时候的操作，就巡逻等行为
     */
    public void onLazzy() {
        this.notifyListeners(LivingEventType.UPDATE_LAZZY.getValue());
    }

    /**
     * @return the npcInfo
     */
    public GameNPCInfo getNpcInfo() {
        return npcInfo;
    }

    /**
     * @param npcInfo
     *            the npcInfo to set
     */
    public void setNpcInfo(GameNPCInfo npcInfo) {
        this.npcInfo = npcInfo;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.module.fight.objects.Physic#getPhyType()
     */
    @Override
    public PhysicsType getPhyType() {
        return PhysicsType.MONSTER;
    }

    /**
     * @return the monsterBean
     */
    public MonsterBean getMonsterBean() {
        return monsterBean;
    }

    /**
     * @param monsterBean
     *            the monsterBean to set
     */
    public void setMonsterBean(MonsterBean monsterBean) {
        this.monsterBean = monsterBean;
    }
}
