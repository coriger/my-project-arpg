/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.map;

import java.awt.Rectangle;

import com.road.arpg.module.fight.objects.Living;

/**
 * 
 * @author yutao.chen
 */
public class LivingRectangle extends Rectangle {
    /**
     * 
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 生物
     */
    private Living living;

    /**
     * 当前所在的节点
     */
    private QuadTree tree;

//    /**
//     * x偏移量
//     */
//    private int shiftX;
//
//    /**
//     * y偏移量
//     */
//    private int shiftY;

    /**
     * @param width
     * @param heigh
     */
    public LivingRectangle(int width, int height, Living living) {

        super();
//        this.shiftX = shiftX;
//        this.shiftY = shiftY;
        this.living = living;
        // 判断朝向
        this.setLocation(living.getX(), living.getY());
        this.setSize(width, height);
    }

    /**
     * @return the living
     */
    public Living getLiving() {
        return living;
    }

    /**
     * @param living
     *            the living to set
     */
    public void setLiving(Living living) {
        this.living = living;
    }

    /**
     * @return the tree
     */
    public QuadTree getTree() {
        return tree;
    }

    /**
     * @param quadTree
     *            the tree to set
     */
    public void setTree(QuadTree quadTree) {
        this.tree = quadTree;
    }

    /**
     * 重新设置矩形位置
     */
    public void reset() {
//        int shiftX = this.shiftX;
//        if (living.getDir().isLeft())
//            shiftX = -shiftX - width;
//        // TODO Auto-generated method stub
//        this.setLocation(shiftX + (int) living.getPos().x, shiftY
//                        + (int) living.getPos().y);
    }

    @Override
    public int hashCode() {
        return this.living.getPhyID();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LivingRectangle) {
            return this.living.getPhyID() == ((LivingRectangle) obj).living
                            .getPhyID();
        }
        return false;
    }

}
