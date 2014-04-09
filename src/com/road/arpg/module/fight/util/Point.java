/**
 * Date: 2013-7-10
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.arpg.module.fight.util;

/**
 * 坐标
 * 
 * @author jiayi.wei
 */
public class Point {
    /**
     * 
     */
    private int x;
    /**
     * 
     */
    private int y;

    /**
     * 
     */
    public Point() {
        x = 0;
        y = 0;
    }

    /**
     * 
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     */
    public Point(Point p) {
        x = p.getX();
        y = p.getY();
    }

    /**
     * @return the x
     */
    public final int getX() {
        return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public final void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public final int getY() {
        return y;
    }

    /**
     * @param y
     *            the y to set
     */
    public final void setY(int y) {
        this.y = y;
    }

    /**
     * X，Y 坐标递加1。
     */
    public final void increase() {
        x++;
        y++;
    }

    /**
     * @param x2
     * @param y2
     */
    public final void setLocation(int x2, int y2) {
        x = x2;
        y = y2;
    }

    /**
     * 
     */
    public boolean equals(PointF pointF) {
        return x == (int) pointF.getX() && y == (int) pointF.getY();
    }

    /**
     * hashCode，随便写的
     */
    public int hashCode() {
        return x << y + x;
    }
}
