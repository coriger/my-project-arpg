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
 * 移动状态<br>
 * 生物移动时，一直处于此状态
 * 
 * @author shandong.su
 */
public class MovingState implements IState {

    @Override
    public void updateFrame(Living host, long tick) {
        // 如果没有走到目的地
        if ((int) (host.getX()) != host.getStContext().getEndPointX()
                        || (int) (host.getY()) != host.getStContext()
                                        .getEndPointY()) {
            host.moveStep(tick - host.getStContext().getLastUpdateTick());
            host.getStContext().setLastUpdateTick(tick);
        } else {
            host.setState(LivingState.RESTING);
            host.onResting();
        }
    }

    @Override
    public void enter(Living self, long tick) {
        self.setColor(Color.RED);
    }

    @Override
    public void beHurt(Living self, long tick) {

    }

    @Override
    public void moving(Living self, int posX, int posY, long tick) {
        // 如果和之前的目的地不同，则用新的目的地
        if (posX != self.getStContext().getEndPointX()
                        || posY != self.getStContext().getEndPointY()) {
            self.getStContext().setEndPointX(posX);
            self.getStContext().setEndPointY(posY);
            self.getStContext().setLastUpdateTick(System.currentTimeMillis());
//            self.getGame()
//                    .sendLivingMove(host, endPointX, endPointY, timeStamp);
        }
    }

    @Override
    public void releaseSkill(Living self, long tick, int skillID, Point pos) {
        self.getSkillHandler().useSkill(skillID, tick, pos);
    }

    @Override
    public LivingState getLivingState() {
        return LivingState.MOVING;
    }

}
