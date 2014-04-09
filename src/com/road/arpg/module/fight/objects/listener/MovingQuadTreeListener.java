/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-6  上午10:28:48
 */
package com.road.arpg.module.fight.objects.listener;

import com.road.arpg.core.event.Event;
import com.road.arpg.core.event.IEventListener;
import com.road.arpg.module.fight.map.QuadTree;
import com.road.arpg.module.fight.objects.Living;

/**
 * 四叉树的中为移动做的监听器
 * 
 * @author shandong.su
 */
public class MovingQuadTreeListener implements IEventListener {

    @Override
    public void onEvent(Event arg) {
        Living living = (Living) arg.getSource();
        QuadTree quaredTree = living.getLivingRect().getTree();
        quaredTree.deleteObject(living.getLivingRect());
        //从当前节点起，找到一个完全能包括自己的矩形，如果找不到就最后插入超级父类节点
        for (; quaredTree.getParent() != null; quaredTree = quaredTree
                        .getParent()) {
            if (quaredTree.getRectangle().getBounds()
                            .contains(living.getLivingRect())) {
                break;
            }
        }
        quaredTree.insert(living.getLivingRect());
    }

}
