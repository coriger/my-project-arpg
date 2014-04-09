/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:58:34
 */
package com.road.arpg.core.fsm;

import org.junit.Test;

/**
 * @author Dream.xie
 */
public class FsmTester {

    /**
     * 
     */
    @Test
    public void test() throws Exception {
        Person person = new Person();
        person.setName("谢国俊");
        person.execute();
        //20毫秒后
        person.setResetTime(20);
        person.execute();

        //
        person.execute();
        person.execute();
    }

    /**
     * 测试延迟消息
     */
    public static void main(String[] args) throws Exception {
        new Thread(FsmMessageDispatcher.getInstance()).start();
        Person person = new Person();
        person.setName("谢国俊");
        person.execute();
        //20毫秒后
        person.setResetTime(20);
        person.execute();

        //
        person.execute();
        person.execute();
    }
}
