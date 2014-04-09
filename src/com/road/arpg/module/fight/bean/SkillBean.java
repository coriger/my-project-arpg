/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-11  下午3:07:25
 */
package com.road.arpg.module.fight.bean;

/**
 * 技能的模板类<br>
 * 先放在这儿吧，之后再换位置
 * 
 * @author shandong.su
 */
public class SkillBean {

    /**
     * 技能ID
     */
    private int skillID;

    /**
     * 技能名称
     */
    private String skillName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 等级
     */
    private int level;

    /**
     * buff列表"|"分割
     */
    private String buff;

    /**
     * 技能冷却时间
     */
    private int coolTime;

    /**
     * 公共冷却时间
     */
    private int publicCoolTime;

    /**
     * 技能吟唱时间
     */
    private int readTime;

    /**
     * 技能类型（普通攻击，主动，被动等）
     */
    private int type;

    /**
     * 伤害类型（1.物理伤害 2.魔法伤害 3.治疗）
     */
    private int hurtType;

    /**
     * 伤害比例
     */
    private float hurtRate;

    /**
     * 伤害附加值
     */
    private int hurtValue;

    /**
     * 作用间隔
     */
    private String effectInterval;

    /**
     * 伤害范围类型（矩形，圆形等）
     */
    private int hurtRangeType;

    /**
     * 伤害范围x
     */
    private int hurtRangeX;

    /**
     * 伤害范围y
     */
    private int hurtRangeY;

    /**
     * 目标策略
     */
    private int targetStrategy;

    /**
     * 目标个数
     */
    private int targetMax;

    /**
     * 消耗怒气
     */
    private int consumeMp;

    /**
     * 添加怒气
     */
    private int addMp;

    /**
     * 飞行速度（像素/每毫米）
     */
    private float flySpeed;

    /**
     * 僵直时间
     */
    private int rigidityTime;

    /**
     * 支持远距离施放
     */
    private int releaseRang;

    /**
     * 0表示对一个点施放，1表示对自己施放，2表示对友方单位施放(包括宠物，队友，帮会等，根据玩家攻击模式定)，3对敌方生物施放
     */
    private byte canUseType;

    /**
     * @return the skillID
     */
    public int getSkillID() {
        return skillID;
    }

    /**
     * @param skillID
     *            the skillID to set
     */
    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    /**
     * @return the skillName
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * @param skillName
     *            the skillName to set
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the buff
     */
    public String getBuff() {
        return buff;
    }

    /**
     * @param buff
     *            the buff to set
     */
    public void setBuff(String buff) {
        this.buff = buff;
    }

    /**
     * @return the coolTime
     */
    public int getCoolTime() {
        return coolTime;
    }

    /**
     * @param coolTime
     *            the coolTime to set
     */
    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    /**
     * @return the publicCoolTime
     */
    public int getPublicCoolTime() {
        return publicCoolTime;
    }

    /**
     * @param publicCoolTime
     *            the publicCoolTime to set
     */
    public void setPublicCoolTime(int publicCoolTime) {
        this.publicCoolTime = publicCoolTime;
    }

    /**
     * @return the readTime
     */
    public int getReadTime() {
        return readTime;
    }

    /**
     * @param readTime
     *            the readTime to set
     */
    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the hurtType
     */
    public int getHurtType() {
        return hurtType;
    }

    /**
     * @param hurtType
     *            the hurtType to set
     */
    public void setHurtType(int hurtType) {
        this.hurtType = hurtType;
    }

    /**
     * @return the hurtRate
     */
    public float getHurtRate() {
        return hurtRate;
    }

    /**
     * @param hurtRate
     *            the hurtRate to set
     */
    public void setHurtRate(float hurtRate) {
        this.hurtRate = hurtRate;
    }

    /**
     * @return the hurtValue
     */
    public int getHurtValue() {
        return hurtValue;
    }

    /**
     * @param hurtValue
     *            the hurtValue to set
     */
    public void setHurtValue(int hurtValue) {
        this.hurtValue = hurtValue;
    }

    /**
     * @return the effectInterval
     */
    public String getEffectInterval() {
        return effectInterval;
    }

    /**
     * @param effectInterval
     *            the effectInterval to set
     */
    public void setEffectInterval(String effectInterval) {
        this.effectInterval = effectInterval;
    }

    /**
     * @return the hurtRangeType
     */
    public int getHurtRangeType() {
        return hurtRangeType;
    }

    /**
     * @param hurtRangeType
     *            the hurtRangeType to set
     */
    public void setHurtRangeType(int hurtRangeType) {
        this.hurtRangeType = hurtRangeType;
    }

    /**
     * @return the hurtRangeX
     */
    public int getHurtRangeX() {
        return hurtRangeX;
    }

    /**
     * @param hurtRangeX
     *            the hurtRangeX to set
     */
    public void setHurtRangeX(int hurtRangeX) {
        this.hurtRangeX = hurtRangeX;
    }

    /**
     * @return the hurtRangeY
     */
    public int getHurtRangeY() {
        return hurtRangeY;
    }

    /**
     * @param hurtRangeY
     *            the hurtRangeY to set
     */
    public void setHurtRangeY(int hurtRangeY) {
        this.hurtRangeY = hurtRangeY;
    }

    /**
     * @return the targetStrategy
     */
    public int getTargetStrategy() {
        return targetStrategy;
    }

    /**
     * @param targetStrategy
     *            the targetStrategy to set
     */
    public void setTargetStrategy(int targetStrategy) {
        this.targetStrategy = targetStrategy;
    }

    /**
     * @return the targetMax
     */
    public int getTargetMax() {
        return targetMax;
    }

    /**
     * @param targetMax
     *            the targetMax to set
     */
    public void setTargetMax(int targetMax) {
        this.targetMax = targetMax;
    }

    /**
     * @return the consumeMp
     */
    public int getConsumeMp() {
        return consumeMp;
    }

    /**
     * @param consumeMp
     *            the consumeMp to set
     */
    public void setConsumeMp(int consumeMp) {
        this.consumeMp = consumeMp;
    }

    /**
     * @return the addMp
     */
    public int getAddMp() {
        return addMp;
    }

    /**
     * @param addMp
     *            the addMp to set
     */
    public void setAddMp(int addMp) {
        this.addMp = addMp;
    }

    /**
     * @return the flySpeed
     */
    public float getFlySpeed() {
        return flySpeed;
    }

    /**
     * @param flySpeed
     *            the flySpeed to set
     */
    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }

    /**
     * @return the rigidityTime
     */
    public int getRigidityTime() {
        return rigidityTime;
    }

    /**
     * @param rigidityTime
     *            the rigidityTime to set
     */
    public void setRigidityTime(int rigidityTime) {
        this.rigidityTime = rigidityTime;
    }

    /**
     * @return the releaseRang
     */
    public int getReleaseRang() {
        return releaseRang;
    }

    /**
     * @param releaseRang
     *            the releaseRang to set
     */
    public void setReleaseRang(int releaseRang) {
        this.releaseRang = releaseRang;
    }

    /**
     * @return the canUseType
     */
    public byte getCanUseType() {
        return canUseType;
    }

    /**
     * @param canUseType
     *            the canUseType to set
     */
    public void setCanUseType(byte canUseType) {
        this.canUseType = canUseType;
    }

}
