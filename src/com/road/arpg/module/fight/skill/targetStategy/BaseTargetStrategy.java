/**
 * Date: 2013-7-10
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.skill.targetStategy;

import java.util.List;

import com.road.arpg.module.fight.bean.SkillBean;
import com.road.arpg.module.fight.objects.Living;

/**
 * 技能目标策略<br>
 * 这儿目前没有做攻击模式的设置，全部将搜索到的全部处理，后面再处理
 * 
 * @author shandong.su
 */
public abstract class BaseTargetStrategy {

    /**
     * 搜索指定类型的目标 部分策略会重写此函数，大部分都直接用
     * 
     * @param shiftX
     * @param shiftY
     * @param rangeX
     * @param rangeY
     * @param count
     * @param living
     * @param hurtRangeType
     * @param y
     * @param x
     * @return
     */
    public List<Living> getTarget(int rangeX, int rangeY, int count,
                    Living living, int hurtRangeType, int x, int y) {
        List<Living> list = living.getBaseScene().getInjuryDetermine()
                        .getRangeLivings(hurtRangeType, x, y, rangeX, rangeY);
        return calTargets(list, count, living);
    }

    /**
     * 搜索指定类型的目标 部分策略会重写此函数，大部分都直接用
     * 
     * @param skillBean
     * @param living
     * @return
     */
    public List<Living> getTarget(SkillBean skillBean, Living living) {
        List<Living> list = living
                        .getBaseScene()
                        .getInjuryDetermine()
                        .getRangeLivings(skillBean.getHurtRangeType(),
                                        living.getX(), living.getY(),
                                        skillBean.getHurtRangeX(),
                                        skillBean.getHurtRangeY());
        return this.calTargets(list, skillBean.getTargetMax(), living);
    }

    /**
     * 过滤规则，其实也主要看过滤
     * 
     * @param list
     * @param count
     * @param living
     * @return
     */
    protected abstract List<Living> calTargets(List<Living> list, int count,
                    Living living);
}
