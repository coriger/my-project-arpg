/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-11  下午8:31:19
 */
package com.road.arpg.module.fight.objects.abiotic;

import com.road.arpg.module.fight.objects.Physic;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 非生物<br>
 * 派生出物品，技能特效
 * 
 * @author shandong.su
 */
public abstract class Abiotic extends Physic {

    /**
     * @param phyID
     * @param x
     * @param y
     * @param phyType
     */
    public Abiotic(int phyID, int x, int y, PhysicsType phyType) {
        super(phyID, x, y);
    }

    @Override
    public void tick(long tickTime) {
    }
}
