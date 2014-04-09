/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */

package com.road.arpg.module.fight.map;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.road.arpg.module.fight.objects.listener.MovingQuadTreeListener;

/**
 * 四叉树(用来做碰撞检测)，MAX_LEVELS和MAX_OBJECTS的配置会对性能有直接影响。层数太大，而且插入的数据为原点很接近的数据时候，
 * 时间复杂度是n的MAX_LEVELS次方。
 * 
 * @author shandong.su
 */
public class QuadTree {

    /**
     * 四叉树 移动监听器
     */
    public static final MovingQuadTreeListener QUADTREE_LISTENER = new MovingQuadTreeListener();
    /**
     * 每个节点所容纳的最大对象数.
     */
    private static final int MAX_OBJECTS = 10;
    /**
     * 最大层数.
     */
    private static final int MAX_LEVELS = 5;

    /**
     * 当前层数.
     */
    private int level;
    /**
     * 为了根据Living快速删除四叉树所
     */
    private Set<LivingRectangle> objects;
    /**
     * 矩形范围.
     */
    private Rectangle bounds;
    /**
     * 4个子节点数组.
     */
    private QuadTree[] nodes;

    /**
     * 此节点的父节点
     */
    private QuadTree parent;

    /**
     * Constructor
     */
    public QuadTree(int pLevel, Rectangle pBounds, QuadTree parent) {
        level = pLevel;
        objects = new HashSet<LivingRectangle>();
        bounds = pBounds;
        nodes = new QuadTree[4];
        this.parent = parent;
    }

    /**
     * Clears the quadtree
     */
    public void clear() {
        objects.clear();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    /**
     * 获得当前节点的Rectangle（矩形边界）
     * 
     * @return
     */
    public Rectangle getRectangle() {
        return bounds;
    }

    /**
     * 插入对象到四叉树里面. 如果超过了当前块的数量, 那么四叉树会再把当前块里面的对象进行再分配。
     */
    public void insert(LivingRectangle pRect) {
        if (nodes[0] != null) {
            int index = getIndex(pRect);

            if (index != -1) {
                nodes[index].insert(pRect);
                pRect.setTree(nodes[index]);
                return;
            }
        }
        objects.add(pRect);
        pRect.setTree(this);
        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            split();
            Set<LivingRectangle> tempObjects = this.objects;
            this.objects = new HashSet<LivingRectangle>();
            for (LivingRectangle rect : tempObjects) {
                int index = getIndex(rect);
                if (index != -1) {
                    nodes[index].insert(rect);
                } else {
                    objects.add(rect);
                }
            }
        }
    }

    /**
     * 移除
     * 
     * @param pRect
     */
    public void removeRect(LivingRectangle pRect) {
        objects.remove(pRect);
    }

    /**
     * 返回所有和给定的objects产生碰撞的物体.
     */
    public List<LivingRectangle> retrieve(Rectangle pRect) {
        List<LivingRectangle> returnObjects = new ArrayList<LivingRectangle>();
        return subRetrieve(returnObjects, pRect);
    }

    /**
     * 返回所有和给定的objects产生碰撞的所有物体.具体方法
     */
    private List<LivingRectangle> subRetrieve(
                    List<LivingRectangle> returnObjects, Rectangle pRect) {

        int index = getIndex(pRect);
        if (index == -1 && nodes[0] != null) {
            for (QuadTree node : nodes) {
                if (node.getRectangle().intersects(pRect)) {
                    node.subRetrieve(returnObjects, pRect);
                }
            }
        } else if (index != -1 && nodes[0] != null) {
            nodes[index].subRetrieve(returnObjects, pRect);
        }
        for (LivingRectangle livingRectangle : objects) {
            if (pRect.intersects(livingRectangle)) {
                returnObjects.add(livingRectangle);
            }
        }

        return returnObjects;
    }

    /**
     * Splits the node into 4 subnodes
     */
    private void split() {
        int subWidth = (int) (bounds.getWidth() / 2);
        int subHeight = (int) (bounds.getHeight() / 2);
        int x = (int) bounds.getX();
        int y = (int) bounds.getY();
        //第一象限
        nodes[0] = new QuadTree(level + 1, new Rectangle(x + subWidth, y,
                        subWidth, subHeight), this);
        //第二象限
        nodes[1] = new QuadTree(level + 1, new Rectangle(x, y, subWidth,
                        subHeight), this);
        //第三象限
        nodes[2] = new QuadTree(level + 1, new Rectangle(x, y + subHeight,
                        subWidth, subHeight), this);
        //第四象限
        nodes[3] = new QuadTree(level + 1, new Rectangle(x + subWidth, y
                        + subHeight, subWidth, subHeight), this);
    }

    /**
     * Determine which node the object belongs to. -1 means object cannot
     * completely fit within a child node and is part of the parent node
     */
    private int getIndex(Rectangle pRect) {
        int index = -1;
        //中心点x,y的坐标
        double xMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double yMidpoint = bounds.getY() + (bounds.getHeight() / 2);
        //把1，2象限叫做上象限。下面就是判断是否属于上象限。
        boolean topQuadrant = (pRect.getY() < yMidpoint && pRect.getY()
                        + pRect.getHeight() < yMidpoint);
        //把3，4象限叫做下象限。下面就是判断是否属于下象限。
        boolean bottomQuadrant = (pRect.getY() > yMidpoint);

        //把2，3象限叫做左象限。下面就是判断是否属于左象限。
        boolean leftQuadrant = pRect.getX() < xMidpoint
                        && pRect.getX() + pRect.getWidth() < xMidpoint;

        //把1，4象限叫做右象限。下面就是判断是否属于右象限。
        boolean rightQuadrant = pRect.getX() > xMidpoint;

        //判断属于第几象限
        if (leftQuadrant) {
            if (topQuadrant) {
                index = 1;
            } else if (bottomQuadrant) {
                index = 2;
            }
        } else if (rightQuadrant) {
            if (topQuadrant) {
                index = 0;
            } else if (bottomQuadrant) {
                index = 3;
            }
        }

        return index;
    }

    /**
     * 获取父节点
     * 
     * @return
     */
    public QuadTree getParent() {
        return this.parent;
    }

    /**
     * 删除树中的节点
     * 
     * @param livingRectangle
     */
    public void deleteObject(LivingRectangle livingRectangle) {
        this.objects.remove(livingRectangle);
    }
}
