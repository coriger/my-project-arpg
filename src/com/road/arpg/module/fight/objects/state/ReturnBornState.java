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
 * 死亡状态，客户端表现为怪物消失，位置初始到出生点
 * 
 * @author shandong.su
 */
public class ReturnBornState implements IState {

    @Override
    public void updateFrame(Living self, long tick) {
        if ((int) (self.getX()) != self.getStContext().getEndPointX()
                        || (int) (self.getY()) != self.getStContext()
                                        .getEndPointY()) {
            self.moveStep(tick - self.getStContext().getLastUpdateTick());
            self.getStContext().setLastUpdateTick(tick);
        } else {
            self.setState(LivingState.RESTING);
        }
    }

    @Override
    public void enter(Living self, long tick) {
        self.setColor(Color.ORANGE);
        self.getStContext().setLockLiving(null);
        self.getStContext().setEndPoint(
                        self.getStContext().getBornPoint().getX(),
                        self.getStContext().getBornPoint().getY());
    }

    @Override
    public void beHurt(Living self, long tick) {
    }

    @Override
    public void moving(Living self, int posX, int posY, long tick) {
    }

    @Override
    public void releaseSkill(Living self, long tick, int skillID, Point pos) {
    }

    @Override
    public LivingState getLivingState() {
        return LivingState.RESTING;
    }

}
