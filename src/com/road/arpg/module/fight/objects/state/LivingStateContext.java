/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.objects.state;

import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.objects.Physic;
import com.road.arpg.module.fight.util.Point;

/**
 * 用来保存玩家战斗中状态的上下文，比如移动状态的终点神马的
 * 
 * @author : shandong.su
 */
public class LivingStateContext {

    /** 状态的宿主 */
    private Physic host;

    /** 移动状态下，移动的终点X坐标 */
    private int endPointX;

    /** 移动状态下，移动的终点Y坐标 */
    private int endPointY;

    /** 上一移动帧更新的时间 */
    private long lastMoveUpdateTick;

    /** 最后受击时间 **/
    private long lastBehurtTick;

    /** 记录被伤害的次数 */
    private int beHurtNum;

    /**
     * 第一位为1表示不能移动 第二位为1表示不能释放技能 第三位为1表示不能普通攻击
     */
    private byte effectState;

    /** 延迟状态结束时间 */
    private long delayEndTick;

    /** 锁定的生物 */
    private Living lockLiving;

    /** 出生点,创建生物的起点，出生后不会被改变 */
    private Point bornPoint;

    /** 最后攻击时间 */
    private long lastAttackTick;

    /** 上一次开始移动的时间 */
    private long lastMoveTick;

    /** 生物的初始血量 */
    private int initHp;

    /** 坐骑模板id */
    private int mountId;

    /** 计时（ai用） */
    private long clockTime;

    /** 是在逃跑中（逃跑中不能进行攻击） */
    private boolean isEscape;

    /**
     * 冲刺状态临时速度
     */
    private float speed;

    /**
     * 构造上下文
     * 
     * @param host
     *            上下文宿主
     */
    public LivingStateContext(Living host) {
        super();
        this.host = host;
        this.lastMoveUpdateTick = 0;
        this.delayEndTick = 0;
        effectState = 0;
    }

    /**
     * 
     * @param x
     * @param y
     */
    public void setEndPoint(int x, int y) {
        this.endPointX = x;
        this.endPointY = y;
    }

    /**
     * 
     */
    public void clearEffectState() {
        effectState = 0;
    }

//    /**
//     * 设置某个状态
//     * 
//     * @param effectState
//     */
//    public void setEffectState(EffectStateType effectState)
//    {
//        int index = effectState.getValue();
//        this.effectState |= 1 << (index - 1);
//    }
//
//    /**
//     * effectState检测 true 表示玩家不能进行某种状态
//     * 
//     * @param effectState
//     * @return
//     */
//    public boolean checkEffectState(EffectStateType effectState)
//    {
//        int index = effectState.getValue();
//        // 英雄服务器不验证移动，，客户端先行
//        if (host instanceof Hero && effectState == EffectStateType.NO_MOVE)
//            return false;
//        int result = this.effectState & (1 << (index - 1));
//        return result > 0;
//    }

    /**
     * effectState检测 true 表示玩家不能进行某种状态
     * 
     * @param effectState
     *            checkHero 是否检查英雄移动状态
     * @return
     */
//    public boolean checkEffectState(EffectStateType effectState,
//            boolean checkHero)
//    {
//        int index = effectState.getValue();
//        // 英雄服务器不验证移动，，客户端先行
//        if (checkHero && host instanceof Hero
//                && effectState == EffectStateType.NO_MOVE)
//            return false;
//        int result = this.effectState & (1 << (index - 1));
//        return result > 0;
//    }

    /**
     * @param lastUpdateTick
     *            the lastUpdateTick to set
     */
    public void setLastUpdateTick(long lastUpdateTick) {
        this.lastMoveUpdateTick = lastUpdateTick;
    }

    /**
     * @return the lastUpdateTick
     */
    public long getLastUpdateTick() {
        return lastMoveUpdateTick;
    }

    /**
     * @param lastAttackMeID
     *            the lastAttackMeID to set
     */
//    public void setLastAttackMeHero(Hero lastAttackMeHero)
//    {
//        this.lastAttackMe = lastAttackMeHero;
//    }

    /**
     * @return the lastAttackMeID
     */
//    public Hero getLastAttackMeHero()
//    {
//        return lastAttackMe;
//    }

    /**
     * @return the endPointY
     */
    public int getEndPointY() {
        return endPointY;
    }

    /**
     * @param endPointY
     *            the endPointY to set
     */
    public void setEndPointY(int endPointY) {
        this.endPointY = endPointY;
    }

    /**
     * @return the endPointX
     */
    public int getEndPointX() {
        return endPointX;
    }

    /**
     * @param endPointX
     *            the endPointX to set
     */
    public void setEndPointX(int endPointX) {
        this.endPointX = endPointX;
    }

    /**
     * @return the nextState
     */
//    public LivingState getNextState()
//    {
//        return nextState;
//    }

    /**
     * @param nextState
     *            the nextState to set
     */
//    public void setNextState(LivingState nextState)
//    {
//        this.nextState = nextState;
//    }

    /**
     * @return the delayEndTick
     */
    public long getDelayEndTick() {
        return delayEndTick;
    }

    /**
     * @param delayEndTick
     *            the delayEndTick to set
     */
    public void setDelayEndTick(long delayEndTick) {
        this.delayEndTick = delayEndTick;
    }

    /**
     * @return the followLockLiving
     */
    public Living getLockLiving() {
        return lockLiving;
    }

    /**
     * @param followLockLiving
     *            the followLockLiving to set
     */
    public void setLockLiving(Living followLockLiving) {
        this.lockLiving = followLockLiving;
    }

    /**
     * @return the beginFollowPoint
     */
//    public Point getBeginFollowPoint()
//    {
//        return beginFollowPoint;
//    }

    /**
     * @param beginFollowPoint
     *            the beginFollowPoint to set
     */
//    public void setBeginFollowPoint(Point beginFollowPoint)
//    {
//        this.beginFollowPoint = beginFollowPoint;
//    }

    /**
     * @return the bornPoint
     */
//    public Point getBornPoint()
//    {
//        return new Point(bornPoint);
//    }

    /**
     * @param bornPoint
     *            the bornPoint to set
     */
//    public void setBornPoint(Point bornPoint)
//    {
//        this.bornPoint = bornPoint;
//    }

    /**
     * @return the lastBehurtTick
     */
    public long getLastBehurtTick() {
        return lastBehurtTick;
    }

    /**
     * @param lastBehurtTick
     *            the lastBehurtTick to set
     */
    public void setLastBehurtTick(long lastBehurtTick) {
        this.lastBehurtTick = lastBehurtTick;
    }

    /**
     * 
     * @return
     */
    public int getBeHurtNum() {
        return beHurtNum;
    }

    /**
     * 
     * @param beHurtNum
     */
    public void setBeHurtNum(int beHurtNum) {
        this.beHurtNum = beHurtNum;
    }

    /**
     * 
     * @return
     */
    public long getLastAttackTick() {
        return lastAttackTick;
    }

    /**
     * 
     * @param lastAttackTick
     */
    public void setLastAttackTick(long lastAttackTick) {
        this.lastAttackTick = lastAttackTick;
    }

    /**
     * 
     * @return
     */
    public int getInitHp() {
        return initHp;
    }

    /**
     * 
     * @param initHp
     */
    public void setInitHp(int initHp) {
        this.initHp = initHp;
    }

    /**
     * 
     * @return
     */
    public int getMountId() {
        return mountId;
    }

    /**
     * 
     * @param mountId
     */
    public void setMountId(int mountId) {
        this.mountId = mountId;
    }

//    public Attribute getHero_mountAttr()
//    {
//        return hero_mountAttr;
//    }
//
//    public void setHero_mountAttr(Attribute hero_mountAttr)
//    {
//        this.hero_mountAttr = hero_mountAttr;
//    }

    /**
     * @return the clockTime
     */
    public long getClockTime() {
        return clockTime;
    }

    /**
     * @param clockTime
     *            the clockTime to set
     */
    public void setClockTime(long clockTime) {
        this.clockTime = clockTime;
    }

    /**
     * 
     * @return
     */
    public boolean isEscape() {
        return isEscape;
    }

    /**
     * 
     * @param isEscape
     */
    public void setEscape(boolean isEscape) {
        this.isEscape = isEscape;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed
     *            the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * @return the lastMoveTick
     */
    public long getLastMoveTick() {
        return lastMoveTick;
    }

    /**
     * @param lastMoveTick
     *            the lastMoveTick to set
     */
    public void setLastMoveTick(long lastMoveTick) {
        this.lastMoveTick = lastMoveTick;
    }

    /**
     * @return the bornPoint
     */
    public Point getBornPoint() {
        return bornPoint;
    }

    /**
     * @param bornPoint
     *            the bornPoint to set
     */
    public void setBornPoint(Point bornPoint) {
        this.bornPoint = bornPoint;
    }
}
