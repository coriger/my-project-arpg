/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects.state;

import java.awt.Color;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.type.LivingState;
import com.road.arpg.module.fight.util.Point;

/**
 * 非活跃状态<br>
 * 当地图的某个区域长期无人后，地图上的怪物都将处于该状态
 * 
 * @author shandong.su
 */
public class InactivingState implements IState {

    @Override
    public void updateFrame(Living self, long tick) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enter(Living self, long tick) {
        Point pos = new Point(self.getX(), self.getY());
        self.setX(self.getStContext().getBornPoint().getX());
        self.setY(self.getStContext().getBornPoint().getY());
        self.onMoving(pos);
        self.setColor(Color.GRAY);
    }

    @Override
    public void beHurt(Living self, long tick) {

    }

    @Override
    public void moving(Living self, int posX, int posY, long tick) {

    }

    @Override
    public void releaseSkill(Living self, long tick, int skillID, Point pos) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.road.arpg.module.fight.objects.state.IState#getLivingState()
     */
    @Override
    public LivingState getLivingState() {
        return LivingState.INACTIVE;
    }

}
