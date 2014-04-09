/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-26  下午12:18:35
 */
package com.road.arpg.module.fight.map;

import java.awt.Rectangle;
import java.util.List;

import com.road.arpg.module.fight.objects.Hero;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * @author Dream.xie
 */
public class QuadTreeTest {
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
//        QuadTree quadTree = new QuadTree(0, new Rectangle(0, 0, 600, 600), null);
//        Living living = new Hero(1, 273, 10, PhysicsType.HERO, null);
//        LivingRectangle searchRect = new LivingRectangle(50, 10, living);
//        test();
    }

    /**
     * 
     * @param args
     */
    public void test() {
        long startTime = System.currentTimeMillis();
        QuadTree quadTree = new QuadTree(0, new Rectangle(0, 0, 600, 600), null);
        quadTree.clear();

        Living pf = null;
        LivingRectangle lr = null;

        pf = new Hero(1, 280, 10, PhysicsType.HERO, null);
        lr = new LivingRectangle(50, 10, pf);
        quadTree.insert(lr);
        System.out.println((System.currentTimeMillis() - startTime));
    }

    /**
     * 
     */
    public void test2() {
        QuadTree quadTree = new QuadTree(0, new Rectangle(0, 0, 600, 600), null);
        quadTree.clear();
        Living pf = null;
        LivingRectangle lr = null;

        pf = new Hero(1, 280, 10, PhysicsType.HERO, null);
        lr = new LivingRectangle(50, 10, pf);

        Living living = new Hero(2, 273, 10, PhysicsType.HERO, null);
        LivingRectangle searchRect = new LivingRectangle(10, 10, living);

        quadTree.insert(lr);
        quadTree.insert(searchRect);
        List<LivingRectangle> list = quadTree.retrieve(searchRect);
        System.out.println(list);
    }
}
