/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-6  上午10:13:01
 */
package com.road.arpg.module.fight.objects.listener;

import com.road.arpg.core.event.Event;
import com.road.arpg.core.event.IEventListener;
import com.road.arpg.module.fight.lattice.LatticeHandler;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.util.Point;

/**
 * 生物移动后，块的相关的监听
 * 
 * @author shandong.su
 */
public class MovingLatticeListener implements IEventListener {

    @Override
    public void onEvent(Event arg) {
        Living living = (Living) arg.getSource();
        Point srcPos = (Point) arg.getData();
        LatticeHandler handler = living.getBaseScene().getLatticeHandler();
        handler.handlerMoving(srcPos, living);
    }

}
