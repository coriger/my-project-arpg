/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects;

import java.awt.Color;

import com.road.arpg.core.event.EventSource;
import com.road.arpg.module.fight.type.PhysicsType;
import com.road.arpg.module.fight.util.Point;

/**
 * 地图上的物件
 * 
 * @author shandong.su
 */
public abstract class Physic extends EventSource {

    /**
     * 默认速度
     */
    public static final int DEFAULT_SPEED = 30;
    /**
     * 
     */
    protected Point pos;

    /**
     * 玩家名，就测试的时候用，建议之后就别存了，没必要
     */
    private String name;

    /**
     * 地图中物体编号
     */
    private int phyID;

    /**
     * 
     */
    private Color color;

    /**
     * 
     * @param phyID
     * @param x
     * @param y
     */
    public Physic(int phyID, int x, int y) {
        super();
        this.pos = new Point(x, y);
        this.phyID = phyID;
    }

    /**
     * 每帧更新
     * 
     * @param tickTime
     */
    public abstract void tick(long tickTime);

    /**
     * 
     * @return
     */
    public final int getX() {
        return this.pos.getX();
    }

    /**
     * 
     */
    public final void setX(int x) {
        this.pos.setX(x);
    }

    /**
     * 
     * @return
     */
    public final int getY() {
        return this.pos.getY();
    }

    /**
     * 
     */
    public final void setY(int y) {
        this.pos.setY(y);
    }

    /**
     * 
     * @return
     */
    public final int getPhyID() {
        return phyID;
    }

    /**
     * 
     */
    public final void setPhyID(int phyID) {
        this.phyID = phyID;
    }

    /**
     * @return the phyType
     */
    public abstract PhysicsType getPhyType();

    /**
     * 
     * @return
     */
    public final Point getPos() {
        return this.pos;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
