/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects.npc;

import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 场景上的生物
 * 
 * @author shandong.su
 */
public class GameNPC extends Robot {

    /**
     * @param phyID
     * @param x
     * @param y
     * @param phyType
     * @param scene
     */
    public GameNPC(int phyID, int x, int y, PhysicsType phyType, BaseScene scene) {
        super(phyID, x, y, phyType, scene);
    }

    @Override
    public PhysicsType getPhyType() {
        return PhysicsType.GAMENPC;
    }

}
