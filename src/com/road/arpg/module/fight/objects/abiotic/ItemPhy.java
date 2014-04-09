/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-11  下午8:33:36
 */
package com.road.arpg.module.fight.objects.abiotic;

import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 掉落物品，比如大药膏，屠龙等
 * 
 * @author shandong.su
 */
public class ItemPhy extends Abiotic {

    /**
     * @param phyID
     * @param x
     * @param y
     * @param phyType
     */
    public ItemPhy(int phyID, int x, int y, PhysicsType phyType) {
        super(phyID, x, y, phyType);
    }

    @Override
    public PhysicsType getPhyType() {
        return PhysicsType.ITEMPHY;
    }

}
