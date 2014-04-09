/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.arpg.module.fight.util;

import com.road.arpg.module.fight.scene.BaseScene;

/**
 * @author : Cookie
 * @version 扩展方法类，对系统类Point进行方法扩展
 */
public final class PointHelper extends Point {

    /**
     * 
     * @param point
     * @param len
     * @return
     */
    public static Point normalize(Point point, int len) {
        double l = PointHelper.length(point);
        return new Point((int) (point.getX() * len / l), (int) (point.getY()
                        * len / l));
    }

    /**
     * 
     * @param point
     * @return
     */
    public static double length(Point point) {
        return Math.sqrt(point.getX() * point.getX() + point.getY()
                        * point.getY());
    }

    /**
     * 
     * @param point
     * @return
     */
    public static double length(PointF point) {
        return Math.sqrt(point.getX() * point.getX() + point.getY()
                        * point.getY());
    }

    /**
     * 
     * @param point
     * @param target
     * @return
     */
    public static double distance(Point point, Point target) {
        int dx = point.getX() - target.getX();
        int dy = point.getY() - target.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 
     * @param point
     * @param target
     * @return
     */
    public static double distance(PointF point, PointF target) {
        float dx = point.getX() - target.getX();
        float dy = point.getY() - target.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 
     * @param point
     * @param tx
     * @param ty
     * @return
     */
    public static double distance(Point point, int tx, int ty) {
        int dx = point.getX() - tx;
        int dy = point.getY() - ty;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 
     * @param point
     * @param tx
     * @param ty
     * @return
     */
    public static double distance(PointF point, int tx, int ty) {
        float dx = point.getX() - tx;
        float dy = point.getY() - ty;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 
     * @param point
     * @param tx
     * @param ty
     * @return
     */
    public static double distance(PointF point, float tx, float ty) {
        float dx = point.getX() - tx;
        float dy = point.getY() - ty;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 
     * @param point
     * @param len
     * @return
     */
    public static PointF normalize(PointF point, float len) {
        double l = Math.sqrt(point.getX() * point.getX() + point.getY()
                        * point.getY());
        return new PointF((float) (point.getX() * len / l),
                        (float) (point.getY() * len / l));
    }

    /**
     * 两个点X坐标的距离
     * 
     * @return
     */
    public static int distanceX(Point pointA, Point pointB) {
        int dis = pointA.getX() - pointB.getX();
        if (dis >= 0) {
            return dis;
        } else {
            return -dis;
        }
    }

    /**
     * 两个点Y坐标的距离
     * 
     * @return
     */
    public static int distanceY(Point pointA, Point pointB) {
        int dis = pointA.getY() - pointB.getY();
        if (dis >= 0) {
            return dis;
        } else {
            return -dis;
        }
    }

    /**
     * 两个点Y坐标的距离
     * 
     * @return
     */
    public static float distanceY(PointF pointA, PointF pointB) {
        float dis = pointA.getY() - pointB.getY();
        if (dis >= 0) {
            return dis;
        } else {
            return -dis;
        }
    }

    /**
     * 两个点X坐标的距离
     * 
     * @return
     */
    public static float distanceX(PointF pointA, PointF pointB) {
        float dis = pointA.getX() - pointB.getX();
        if (dis >= 0) {
            return dis;
        } else {
            return -dis;
        }
    }

    /**
     * 返回两点之间的方向向量
     * 
     * @param startPos
     * @param endPos
     * @return
     */
    public static PointF dirVector(PointF startPos, PointF endPos) {
        return new PointF(endPos.getX() - startPos.getX(), endPos.getY()
                        - startPos.getY());
    }

    /**
     * @param pos
     * @param endX
     * @param endY
     * @return
     */
    public static PointF dirVector(PointF pos, int endX, int endY) {
        return new PointF(endX - pos.getX(), endY - pos.getY());
    }

    /**
     * 朝目标方向移动length
     * 
     * @param point
     * @param length
     * @param targetX
     * @param targetY
     */
    public static void moveTo(Point point, int length, int targetX, int targetY) {
        int len = (int) Math.sqrt((targetX - point.getX())
                        * (targetX - point.getX()) + (targetY - point.getY())
                        * (targetY - point.getY()));
        point.setX((int) ((Math.abs(targetX - point.getX()) / (float) len)
                        * length + point.getX()));
        point.setY((int) ((Math.abs(targetY - point.getY()) / (float) len)
                        * length + point.getY()));
    }

    /**
     * 有些地方既然已经计算了一次平方值了，那么就不再次计算了，浪费，传进来吧
     * 
     * 朝目标方向移动length
     * 
     * @param point
     * @param length
     * @param targetX
     * @param targetY
     * @param length2
     */
    public static void moveTo(Point point, int length, int targetX,
                    int targetY, int length2) {
        int len = (int) Math.sqrt((targetX - point.getX())
                        * (targetX - point.getX()) + (targetY - point.getY())
                        * (targetY - point.getY()));
        point.setX((int) ((Math.abs(targetX - point.getX()) / (float) len)
                        * length + point.getX()));
        point.setY((int) ((Math.abs(targetY - point.getY()) / (float) len)
                        * length + point.getY()));
    }

    /**
     * 
     * @param endX
     * @param endY
     * @param speed
     */
    public static int calMove(int endX, int endY, float speed, Point pos) {
        // 计算路径需要的时间
        double pathLong = PointHelper.distance(pos, endX, endY);
        int frameCount = (int) ((pathLong + speed - 1) / speed); // 距离/速度 = 要刷几帧
        int timeLong = frameCount * BaseScene.MOVE_INTERVAL; // 每个移动帧为40ms
        return timeLong;
    }

    /**
     * 两个点的平方和，没有开方
     * 
     * @param point
     * @param point
     * @return
     */
    public static int distanceSquare(Point point, Point point2) {
        return (point.getX() - point2.getX()) * (point.getX() - point2.getX())
                        + (point.getY() - point2.getY())
                        * (point.getY() - point2.getY());
    }

    /**
     * 两个点的平方和，没有开方
     * 
     * @param point
     * @param point
     * @return
     */
    public static int distanceSquare(Point point, int x, int y) {
        return (point.getX() - x) * (point.getX() - x) + (point.getY() - y)
                        * (point.getY() - y);
    }
}
