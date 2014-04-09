/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-17  下午4:28:35
 */
package com.road.arpg.core.manager.groovy;

import org.junit.Assert;
import org.junit.Test;

import com.road.arpg.BaseTester;

/**
 * @author Dream.xie
 */
public final class GroovyTester extends BaseTester {
    /**
     * 
     */
    @Test
    public void testGroovy() {
        try {
            ICal cal = GroovyManager
                            .getInstance()
                            .getGroovyObj("E:/eclipseworkspace/arpg/test/com/road/arpg/manager/groovy/Cal.java");
            Assert.assertEquals(15, cal.cal(5, 10));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
