/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-17  下午4:30:22
 */
package com.road.arpg.core.manager.groovy;

/**
 * @author Dream.xie
 */
public class Cal implements ICal {

    /* (non-Javadoc)
     * @see com.road.arpg.manager.groovy.ICal#cal(int, int)
     */
    @Override
    public int cal(int x, int y) {
        return x + y;
    }

}
