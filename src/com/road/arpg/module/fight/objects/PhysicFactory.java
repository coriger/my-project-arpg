/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects;

import com.road.arpg.module.fight.objects.npc.GameNPC;
import com.road.arpg.module.fight.objects.npc.Monster;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 工厂创建类
 * 
 * @author shandong.su
 */
public final class PhysicFactory {

    /**
     * 
     */
    private PhysicFactory() {

    }

    /**
     * 工厂创建生物
     * 
     * @param phyID
     * @param x
     * @param y
     * @param gameMap
     * @param phType
     * @param canCollide
     * @param campType
     * @param beanID
     * @param game
     * @return
     */
    public static Physic createPhysics(int x, int y, PhysicsType phType,
                    BaseScene scene) {
        Physic result = null;
        int phyID = scene.getPhyID();
        switch (phType) {

        // 为了兼容性，还是保留前面的这几种类型
            case GAMENPC:
                result = createGameNPC(phyID, x, y, phType, scene);
                break;
            case MONSTER:
                result = createMonster(phyID, x, y, phType, scene);
                break;
            case HERO:
                result = createPlayerFighter(phyID, x, y, phType, scene);
                ((Living) result).setState(LivingState.RESTING);
                break;

            default:
                result = createGameNPC(phyID, x, y, phType, scene);
                break;
        }
        return result;
    }

    /**
     * 创建一个游戏对象
     * 
     * @param phyID
     * @param x
     * @param y
     * @param phType
     * @param gameMap
     * @param campType
     * @return
     */
    private static Living createGameNPC(int phyID, int x, int y,
                    PhysicsType phType, BaseScene scene) {
        Living gameNpc = new GameNPC(phyID, x, y, phType, scene);
        return gameNpc;
    }

    /**
     * 
     * @param phyID
     * @param x
     * @param y
     * @param phType
     * @param scene
     * @return
     */
    private static Monster createMonster(int phyID, int x, int y,
                    PhysicsType phType, BaseScene scene) {
        Monster gameNpc = new Monster(phyID, x, y, phType, scene);
        return gameNpc;
    }

    /**
     * 创建英雄
     * 
     * @param phyID
     * @param x
     * @param y
     * @param gameMap
     * @param canCollide
     * @return
     */
    private static Hero createPlayerFighter(int phyID, int x, int y,
                    PhysicsType phyType, BaseScene scene) {
        Hero hero = new Hero(phyID, x, y, phyType, scene);
        return hero;
    }

}
