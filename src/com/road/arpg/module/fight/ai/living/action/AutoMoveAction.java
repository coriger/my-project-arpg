/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-4  下午2:14:40
 */
package com.road.arpg.module.fight.ai.living.action;

import com.road.arpg.core.event.Event;
import com.road.arpg.core.util.RandomUtil;
import com.road.arpg.module.fight.ai.living.AiLivingActAnnotation;
import com.road.arpg.module.fight.ai.living.LivingAiActionType;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.type.LivingState;

/**
 * 行走动作
 * 
 * @author shandong.su
 */
@AiLivingActAnnotation(type = LivingAiActionType.AUTO_MOVE, aiType = AiType.LIVING, desc = "自动向对方基地移动")
public class AutoMoveAction extends LivingAiAction {

    @Override
    public Void actionExecut(Event context) {
        Living living = (Living) context.getSource();
        // 如果不是移动状态
        if (living.getCurStateType() != LivingState.MOVING) {
            //只有距离上次移动超过10秒，才移动，当然这个数据可能要改到数据库配置中去
            if (System.currentTimeMillis()
                            - living.getStContext().getLastMoveTick() > (living
                            .getPhyID() % 10 + 5) * 1000) {
                living.moveTo(living.getX() + (RandomUtil.randInt(120) - 60),
                                living.getY() + (RandomUtil.randInt(100) - 50));
                living.getStContext().setLastMoveTick(
                                System.currentTimeMillis());
            }
        }
        return null;
    }

}
