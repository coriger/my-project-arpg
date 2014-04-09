/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.trigger;

/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */

import java.util.List;

/**
 * @author : shandong.su
 * @version 条件的配置信息
 */
public class ConditionInfo {
    /**
     * 条件id
     */
    private short conditionID;

    /**
     * 逻辑类型AND，OR
     */
    private LogicType logicType;

    /**
     * 是否需要重新创建action或condition
     */
    private boolean needNew;

    /**
     * 条件，用','分割
     */
    private String params;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 子条件
     */
    private List<ConditionInfo> sonInfo;

    /**
     * @return the conditionID
     */
    public short getConditionID() {
        return conditionID;
    }

    /**
     * @param conditionID
     *            the conditionID to set
     */
    public void setConditionID(String conditionID) {
        if (conditionID == null || conditionID.isEmpty()) {
            this.conditionID = 0;
            return;
        }
        this.conditionID = Short.parseShort(conditionID);
    }

    /**
     * @return the logicType
     */
    public LogicType getLogicType() {
        return logicType;
    }

    /**
     * @param logicType
     *            the logicType to set
     */
    public void setLogicType(String logicType) {
        // 如果没有设置，则认为是and
        if (logicType == null || logicType.isEmpty()) {
            this.logicType = LogicType.AND;
            return;
        }
        if (logicType.equals("or")) {
            this.logicType = LogicType.OR;
        } else {
            this.logicType = LogicType.AND;
        }
    }

    /**
     * @return the needNew
     */
    public boolean isNeedNew() {
        return needNew;
    }

    /**
     * @param needNew
     *            the needNew to set
     */
    public void setNeedNew(boolean needNew) {
        this.needNew = needNew;
    }

    /**
     * @return the params
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * @param errMsg
     *            the errMsg to set
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * @return the errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param sonInfo
     *            the sonInfo to set
     */
    public void setSonInfo(List<ConditionInfo> sonInfo) {
        this.sonInfo = sonInfo;
    }

    /**
     * @return the sonInfo
     */
    public List<ConditionInfo> getSonInfo() {
        return sonInfo;
    }

    /**
     * 
     */
    public String toString() {
        return " conditionID: " + this.conditionID + " sonConds : "
                        + this.sonInfo;
    }

}
