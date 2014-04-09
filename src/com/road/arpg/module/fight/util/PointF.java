/**
 * All rights reserved. This material is confidential and proprietary to ROAD
 */
package com.road.arpg.module.fight.util;

/**
 * @author : Cookie
 * @version 浮点型的点。。（蛋疼的JAVA没有内置的。从android源码复制过来）
 */
public class PointF {
    /**
     * 
     */
    private float x;
    /**
     * 
     */
    private float y;

    /**
     * 
     */
    public PointF() {
    }

    /**
     * 
     * @param pF
     */
    public PointF(PointF pF) {
        this.x = pF.x;
        this.y = pF.y;
    }

    /**
     * 
     * @param x
     * @param y
     */
    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param p
     */
    public PointF(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /***
     * Set the point's x and y coordinates
     */
    public final void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /***
     * Set the point's x and y coordinates to the coordinates of p
     */
    public final void set(PointF p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * 反一下
     */
    public final void negate() {
        x = -x;
        y = -y;
    }

    /**
     * 
     * @param dx
     * @param dy
     */
    public final void offset(float dx, float dy) {
        x += dx;
        y += dy;
    }

    /***
     * Returns true if the point's coordinates equal (x,y)
     */
    public final boolean equals(float x, float y) {
        return Math.abs(this.x - x) < 0.1 && Math.abs(this.y - y) < 0.1;
    }

    /***
     * * Return the euclidian distance from (,) to the point
     */
    public final float length() {
        return length(x, y);
    }

    /***
     * * Returns the euclidian distance from (,) to (x,y)
     */
    public static float length(float x, float y) {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 
     */
    public String toString() {
        return " pos:" + x + "," + y;
    }

    /**
     * 
     * @return
     */
    public final float getX() {
        return this.x;
    }

    /**
     * 
     * @return
     */
    public final float getY() {
        return this.y;
    }
}
