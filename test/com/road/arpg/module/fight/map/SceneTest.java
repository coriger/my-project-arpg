/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-4  上午10:57:14
 */
package com.road.arpg.module.fight.map;

import java.awt.Color;
import java.util.Date;

import com.road.arpg.module.fight.ai.AiTriggersFactory;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.scene.SceneFactory;
import com.road.arpg.module.fight.scene.SceneManager;
import com.road.arpg.module.fight.type.PhysicsType;

/**
 * @author shandong.su
 */
public final class SceneTest {

    /**
     * 
     */
    private static Living hero = null;

    /**
     * 
     */
    private SceneTest() {

    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        AiTriggersFactory.getInstance().init();
        SceneFactory factory = SceneFactory.getInstance();
        factory.init();
        SceneManager manager = SceneManager.getInstance();
        manager.init();
        AiTriggersFactory.getInstance().init();
        BaseScene scene = new BaseScene(factory.getSceneMap().get(1));
        System.out.println(new Date());
        hero = scene.addLiving(0, 750, 850, PhysicsType.HERO, 1);
        hero.setName("超级赛亚人");
        hero.setColor(Color.ORANGE);
        manager.submit(scene);
    }

    /**
     * 
     * @return
     */
    public static Living getHero() {
        return hero;
    }
}
