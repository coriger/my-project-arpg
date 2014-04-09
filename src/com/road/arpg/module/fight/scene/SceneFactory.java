/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.scene;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.road.arpg.core.util.LogUtil;
import com.road.arpg.module.fight.scene.info.GameNPCInfo;
import com.road.arpg.module.fight.scene.info.SceneInfo;

/**
 * 
 * @author shandong.su
 */
public final class SceneFactory {

    /**
     * 
     */
    private static int objID = 0;

    /**
     * 
     */
    private Map<Integer, SceneInfo> maps = new HashMap<Integer, SceneInfo>();

    /**
     * 
     */
    private SceneFactory() {
    }

    /**
     * 初始化场景、怪物、ai等
     */
    @SuppressWarnings("unchecked")
    public void init() {
        File file = new File(getConfigFile());
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element element = document.getRootElement();
            List<Element> scenes = element.elements();
            for (Element ele : scenes) {
                SceneInfo sceneInfo = new SceneInfo();
                sceneInfo.setHeigh(Integer.parseInt(ele.attributeValue("heigh")));
                sceneInfo.setWidth(Integer.parseInt(ele.attributeValue("width")));
                sceneInfo.setName(ele.attributeValue("name"));
                sceneInfo.setSceneID(Integer.parseInt(ele
                                .attributeValue("sceneID")));
                Map<Integer, GameNPCInfo> talkNPCMap = new HashMap<Integer, GameNPCInfo>();
                Element npcEL = ele.element("npcs");
                List<Element> npcELementList = npcEL.elements();
                for (Element npcEle : npcELementList) {
                    GameNPCInfo npcInfo = parseGameNPCInfo(npcEle);
                    talkNPCMap.put(npcInfo.getMapObjID(), npcInfo);
                }
                sceneInfo.setTalkNPCMap(talkNPCMap);

                Map<Integer, GameNPCInfo> gameNPCMap = new HashMap<Integer, GameNPCInfo>();
                Element gameMonsterEL = ele.element("monsters");
                List<Element> monsterELementList = gameMonsterEL.elements();
                for (Element npcEle : monsterELementList) {
                    GameNPCInfo npcInfo = parseGameNPCInfo(npcEle);
                    gameNPCMap.put(npcInfo.getMapObjID(), npcInfo);
                }
                sceneInfo.setMonsterMap(gameNPCMap);
                maps.put(sceneInfo.getSceneID(), sceneInfo);
            }

        } catch (Exception e) {
            LogUtil.error(" 加载场景错误", e);
        }

    }

    /**
     * 
     * @param element
     * @return
     */
    private GameNPCInfo parseGameNPCInfo(Element element) {
        GameNPCInfo npcInfo = new GameNPCInfo();
        npcInfo.setAiID(Integer.parseInt(element.attributeValue("aiID")));
        npcInfo.setMapObjID(objID++);
        npcInfo.setName(element.attributeValue("name"));
        npcInfo.setObjectID(Integer.parseInt(element.attributeValue("objID")));
        npcInfo.setPosX(Integer.parseInt(element.attributeValue("posX")));
        npcInfo.setPosY(Integer.parseInt(element.attributeValue("posY")));
        return npcInfo;
    }

    /**
     * 配置文件
     * 
     * @return
     */
    public String getConfigFile() {
        return "./module/internalModule/fight/scene/xiangyang_scene.xml";
    }

    /**
     * 
     * @author shandong.su
     */
    private static class SingletionHolder {
        /**
         * 利用内部类，即做到了需要使用的时候才加载，又做到了加载线程安全
         */
        public static final SceneFactory INSTANCE = new SceneFactory();
    }

    /**
     * 
     * @return
     */
    public static SceneFactory getInstance() {
        return SingletionHolder.INSTANCE;
    }

    /**
     * 
     * @return
     */
    public Map<Integer, SceneInfo> getSceneMap() {
        return this.maps;
    }
}
