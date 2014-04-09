/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * @author : shandong.su
 * @version 单个触发器配置
 */
public class TriggerInfo {
    /**
     * 触发器编号
     */
    private int tirggerID;

    /**
     * ai类型
     */
    private AiType type;

    /**
     * 条件信息
     */
    private ConditionInfo conditionInfo;

    /**
     * 如果条件为true，执行的动作，默认执行的动作
     */
    private ActionInfo yesActionInfo;

    /**
     * 如果条件为false，执行的动作
     */
    private ActionInfo noActionInfo;

    /**
     * 参数，很少
     */
    private String param;

    /**
     * @return the tirggerID
     */
    public int getTirggerID() {
        return tirggerID;
    }

    /**
     * @param tirggerID
     *            the tirggerID to set
     */
    public void setTirggerID(int tirggerID) {
        this.tirggerID = tirggerID;
    }

    /**
     * @return the type
     */
    public AiType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(AiType type) {
        this.type = type;
    }

    /**
     * @return the conditionInfo
     */
    public ConditionInfo getConditionInfo() {
        return conditionInfo;
    }

    /**
     * @param conditionInfo
     *            the conditionInfo to set
     */
    public void setConditionInfo(ConditionInfo conditionInfo) {
        this.conditionInfo = conditionInfo;
    }

    /**
     * @return the actionInfo
     */
    public ActionInfo getYesActionInfo() {
        return yesActionInfo;
    }

    /**
     * @param actionInfo
     *            the actionInfo to set
     */
    public void setYesActionInfo(ActionInfo actionInfo) {
        this.yesActionInfo = actionInfo;
    }

    /**
     * @param noActionInfo
     *            the noActionInfo to set
     */
    public void setNoActionInfo(ActionInfo noActionInfo) {
        this.noActionInfo = noActionInfo;
    }

    /**
     * @return the noActionInfo
     */
    public ActionInfo getNoActionInfo() {
        return noActionInfo;
    }

    /**
     * @return the param
     */
    public String getParam() {
        return param;
    }

    /**
     * @param param
     *            the param to set
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 
     */
    public String toString() {
        return " triggerID:" + this.tirggerID + " param:" + param + " if ( "
                        + conditionInfo + " ) \n{ " + this.yesActionInfo
                        + " } \nelse {" + this.noActionInfo + "}  \n";

    }
}
