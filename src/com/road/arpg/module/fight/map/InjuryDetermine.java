/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.arpg.module.fight.map;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D.Float;
import java.util.ArrayList;
import java.util.List;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.type.HurtRangeType;

/**
 * @author shandong.su
 * @version
 * 
 *          update yutao.chen 加入四叉树，碰撞矩形
 */
public class InjuryDetermine {

    /**
     * 
     */
    private QuadTree rectTrees;

    /**
     * 
     * @param baseGame
     */
    public InjuryDetermine(BaseScene baseGame) {
        rectTrees = baseGame.getQuadTree();
    }

    /**
     * 
     * @param x
     *            中心点X
     * @param y
     *            中心点Y
     * @param rangeX
     *            注意范围有方向（为负时表示朝左，为正时表示朝右） 范围X
     * @param rangeY
     *            范围Y
     * @return 范围内目标
     */
    public final List<Living> getRangeLivings(int rangType, int x, int y,
                    int rangeX, int rangeY) {
        if (rangeX == 0 || rangeY == 0) {
            return new ArrayList<>();
        }
        switch (rangType) {
            case HurtRangeType.OVAL:
                return getOvalLivings(x, y, rangeX, rangeY);
            case HurtRangeType.RECTANGLE:
                return getRectLivings(x, y, rangeX, rangeY);
            default:
                return getOvalLivings(x, y, rangeX, rangeY);
        }
    }

    /**
     * 
     * @param x
     * @param y
     * @param a
     * @param b
     * @param campType
     * @return
     */
    private List<LivingRectangle> getRects(int x, int y, int a, int b) {
        a = Math.abs(a);
        return rectTrees.retrieve(new Rectangle(x - a, y - b, a * 2, b * 2));
    }

    /**
     * 获取矩形区域内目标阵营玩家列表， 矩形区域指的是 (x - rectX <= living.x <= x + rectX) && ( y -
     * rectY <= living.y <= y + rectY)
     * 
     * @param x
     * @param y
     * @param rectX
     * @param rectY
     */
    private List<Living> getRectLivings(int x, int y, int rectX, int rectY) {
        List<LivingRectangle> list = getRects(x, y, rectX, rectY);
        List<Living> resultList = new ArrayList<Living>();
        for (LivingRectangle rect : list) {
            resultList.add(rect.getLiving());
        }
        return resultList;
    }

    /**
     * 获取椭圆型区域内目标 Math.pow((living.x - x),2) / Math.pow(circleX,2) +
     * Math.pow((living.y - y),2)/Math.pow(circleY,2) = 1;
     * 
     * @param campType
     * @param x
     * @param y
     * @param circleX
     * @param circleY
     * @return
     */
    private List<Living> getOvalLivings(int x, int y, int ovalX, int ovalY) {
        List<LivingRectangle> list = getRects(x, y, ovalX, ovalY);
        ovalX = Math.abs(ovalX);
        List<Living> resultList = new ArrayList<Living>();
        Float oval = new Float(x - ovalX, y - ovalY, ovalX * 2, ovalY * 2);
        for (LivingRectangle rect : list) {
            Living living = rect.getLiving();
            // 椭圆范围内的生物
            if (oval.intersects(rect)) {
                if (!resultList.contains(living)) {
                    resultList.add(living);
                }
            }
        }
        return resultList;
    }

}
