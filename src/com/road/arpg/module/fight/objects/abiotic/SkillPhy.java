/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-11  下午8:34:07
 */
package com.road.arpg.module.fight.objects.abiotic;

import com.road.arpg.module.fight.type.PhysicsType;

/**
 * 技能效果，比如法球，火墙
 * 
 * @author shandong.su
 */
public class SkillPhy extends Abiotic {

    /**
     * @param phyID
     * @param x
     * @param y
     * @param phyType
     */
    public SkillPhy(int phyID, int x, int y, PhysicsType phyType) {
        super(phyID, x, y, phyType);
    }

    @Override
    public PhysicsType getPhyType() {
        return PhysicsType.SKILLPHY;
    }

}
