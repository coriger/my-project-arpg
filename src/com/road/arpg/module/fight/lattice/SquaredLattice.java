/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-3  上午10:51:46
 */
package com.road.arpg.module.fight.lattice;

import java.util.HashSet;
import java.util.Set;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.objects.Physic;
import com.road.arpg.module.fight.objects.listener.MovingLatticeListener;
import com.road.arpg.module.fight.type.LivingEventType;
import com.road.arpg.module.fight.type.LivingState;

/**
 * 用九宫格实现的分块
 * 
 * @author shandong.su
 */
public class SquaredLattice {

    /**
     * 块移动监听器
     */
    public static final MovingLatticeListener LATTICE_LISTENER = new MovingLatticeListener();

    /**
     * 块在块handler中的标识
     */
    private int latticeID;

    /**
     * 块中玩家的列表
     */
    private Set<Living> players;

    /**
     * 块中怪物列表
     */
    private Set<Living> monsters;

    /**
     * npc，这部分npc不移动，只需要校验位置就好
     */
    private Set<Physic> npcs;

    /**
     * 技能产生的物件，这个比较难控制
     */
    private Set<Physic> skillPhys;

    /**
     * 物品物件
     */
    private Set<Physic> itemPhys;

    /**
     * 
     */
    private boolean isActive;

    /**
     * 上次激活时间
     */
    private long lastActiveTime;

    /**
     * 
     */
    private LatticeHandler latticeHandler;

    /**
     * 临近的8个格子的在SceneLatticeHandler中的id
     */
    private int[] closeLattice = { -1, -1, -1, -1, -1, -1, -1, -1 };

    /**
     * 
     */
    public SquaredLattice(int latticeID, LatticeHandler latticeHandler, int w,
                    int h, int maxW, int maxH) {
        this.latticeID = latticeID;
        players = new HashSet<Living>();
        monsters = new HashSet<Living>();
        isActive = false;
        init(latticeHandler, w, h, maxW, maxH);
    }

    /**
     * 
     * @param latticeHandler
     */
    private void init(LatticeHandler latticeHandler, int w, int h, int maxW,
                    int maxH) {
        this.initCloseLattice(w, h, maxW, maxH);
        this.latticeHandler = latticeHandler;
    }

    /**
     * 比较繁琐，但是没技术含量 初始化旁边周围相关的格子
     * 
     * @param w
     * @param h
     * @param maxW
     * @param maxH
     */
    private void initCloseLattice(int w, int h, int maxW, int maxH) {
        if (w > 0 && w < maxW - 1) {
            if (h > 0 && h < maxH - 1) {
                closeLattice[0] = (w - 1) * maxH + h - 1;
                closeLattice[1] = (w - 1) * maxH + h;
                closeLattice[2] = (w - 1) * maxH + h + 1;
                closeLattice[3] = (w + 1) * maxH + h - 1;
                closeLattice[4] = (w + 1) * maxH + h;
                closeLattice[5] = (w + 1) * maxH + h + 1;
                closeLattice[6] = (w) * maxH + h - 1;
                closeLattice[7] = (w) * maxH + h + 1;
            } else if (h > 0 && h == maxH - 1) {
                closeLattice[0] = (w - 1) * maxH + h - 1;
                closeLattice[1] = (w - 1) * maxH + h;
                closeLattice[2] = (w + 1) * maxH + h - 1;
                closeLattice[3] = (w + 1) * maxH + h;
                closeLattice[4] = (w) * maxH + h - 1;
            } else if (h == 0 && h < maxH - 1) {
                closeLattice[0] = (w - 1) * maxH + h;
                closeLattice[1] = (w - 1) * maxH + h + 1;
                closeLattice[2] = (w + 1) * maxH + h;
                closeLattice[3] = (w + 1) * maxH + h + 1;
                closeLattice[4] = (w) * maxH + h + 1;
            } else {
                closeLattice[0] = (w - 1) * maxH + h;
                closeLattice[1] = (w + 1) * maxH + h;
            }
        } else if (w > 0 && w == maxW - 1) {
            if (h > 0 && h < maxH - 1) {
                closeLattice[0] = (w - 1) * maxH + h - 1;
                closeLattice[1] = (w - 1) * maxH + h;
                closeLattice[2] = (w - 1) * maxH + h + 1;
                closeLattice[3] = (w) * maxH + h - 1;
                closeLattice[4] = (w) * maxH + h + 1;
            } else if (h > 0 && h == maxH - 1) {
                closeLattice[0] = (w - 1) * maxH + h - 1;
                closeLattice[1] = (w - 1) * maxH + h;
                closeLattice[2] = (w) * maxH + h - 1;
            } else if (h == 0 && h < maxH - 1) {
                closeLattice[0] = (w - 1) * maxH + h;
                closeLattice[1] = (w - 1) * maxH + h + 1;
                closeLattice[2] = (w) * maxH + h + 1;
            }
        } else if (w == 0 && w < maxW - 1) {
            if (h > 0 && h < maxH - 1) {
                closeLattice[0] = (w + 1) * maxH + h - 1;
                closeLattice[1] = (w + 1) * maxH + h;
                closeLattice[2] = (w + 1) * maxH + h + 1;
                closeLattice[3] = (w) * maxH + h - 1;
                closeLattice[4] = (w) * maxH + h + 1;
            } else if (h > 0 && h == maxH - 1) {
                closeLattice[0] = (w + 1) * maxH + h - 1;
                closeLattice[1] = (w + 1) * maxH + h;
                closeLattice[2] = (w) * maxH + h - 1;
            } else if (h == 0 && h < maxH - 1) {
                closeLattice[0] = (w + 1) * maxH + h;
                closeLattice[1] = (w + 1) * maxH + h + 1;
                closeLattice[2] = (w) * maxH + h + 1;
            }
        }
    }

    /**
     * 块更新的帧
     */
    public void tick(long tickTime) {
        //如果已经过了上次激活10秒的时间了，那么将自己从handler中将自己删除
        if (tickTime > this.lastActiveTime) {
            this.latticeHandler.inactiveLattice(this);
        }
        //如果移出方块呢？  直接删掉自身么
        Living tempLiving = null;
        for (Living living : this.players) {
            //这一步是为了缓存一个玩家，拿来刷新其他模块的更新时间
            tempLiving = living;
            living.tick(tickTime);
        }
        if (tempLiving != null) {
            //用最后一个玩家去激活相邻的场景
            for (int i = 0; i < 8 && this.closeLattice[i] != -1; i++) {
                this.latticeHandler.active(this.closeLattice[i], tempLiving);
            }
            this.activeLattice(tempLiving);
        }
        //如果移出方块呢？  直接删掉自身么
        for (Living living : this.monsters) {
            living.tick(tickTime);
        }

    }

    /**
     * 这个接口值用来添加生物刚进场景时候的添加，主要会在这个步骤中添加生物的移动的触发器
     * 
     * @param living
     */
    public void enterLiving(Physic living) {
        switch (living.getPhyType()) {
            case HERO:
                this.players.add((Living) living);
                this.latticeHandler.active(latticeID, (Living) living);
                //用最后一个玩家去激活相邻的场景
                for (int i = 0; i < 8 && this.closeLattice[i] != -1; i++) {
                    this.latticeHandler.active(this.closeLattice[i],
                                    (Living) living);
                }
                break;
            case MONSTER:
                this.monsters.add((Living) living);
                break;
            case ITEMPHY:
                this.itemPhys.add(living);
                break;
            case SKILLPHY:
                this.skillPhys.add(living);
                break;
            case GAMENPC:
                this.npcs.add(living);
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @param living
     */
    public void activeLattice(Living living) {
        //如果不是激活状态，那么设置成激活，并且将每个怪物激活
        if (!isActive) {
            for (Living monster : monsters) {
                monster.setState(LivingState.RESTING);
                monster.notifyListeners(LivingEventType.UPDATE_REST.getValue());
            }
            this.isActive = true;
        }
        //10秒钟的激活时间
        this.lastActiveTime = System.currentTimeMillis() + 10 * 1000;
    }

    /**
     * 将块设置为非激活状态
     */
    public void inactiveLattice() {
        for (Living monster : monsters) {
            monster.setState(LivingState.INACTIVE);
        }
        this.isActive = false;
    }

    /**
     * @return the closeLattice
     */
    public int[] getCloseLattice() {
        return closeLattice;
    }

    /**
     * @param closeLattice
     *            the closeLattice to set
     */
    public void setCloseLattice(int[] closeLattice) {
        this.closeLattice = closeLattice;
    }

    /**
     * 将生物删除
     * 
     * @param living
     */
    public void leavingLattice(Physic living) {
        switch (living.getPhyType()) {
            case HERO:
                this.players.remove(living);
                break;
            case MONSTER:
                this.monsters.remove(living);
                break;
            case ITEMPHY:
                this.itemPhys.remove(living);
                break;
            case SKILLPHY:
                this.skillPhys.remove(living);
                break;
            case GAMENPC:
                this.npcs.remove(living);
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @return the latticeID
     */
    public int getLatticeID() {
        return latticeID;
    }
}
