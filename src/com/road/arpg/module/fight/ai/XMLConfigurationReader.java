/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.fight.ai;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.road.arpg.core.util.LogUtil;
import com.road.arpg.module.fight.ai.entity.LivingAiInfo;
import com.road.arpg.module.fight.scene.info.GameNPCInfo;
import com.road.arpg.module.fight.scene.info.SceneInfo;
import com.road.arpg.module.fight.trigger.ActionInfo;
import com.road.arpg.module.fight.trigger.AiType;
import com.road.arpg.module.fight.trigger.ConditionInfo;
import com.road.arpg.module.fight.trigger.TriggerInfo;
import com.road.arpg.module.fight.type.LivingEventType;

/**
 * 解析config目录下的文件
 * 
 * @author shandong.su
 */
public final class XMLConfigurationReader {

    /**
     * 静态类，私有初始化
     */
    private XMLConfigurationReader() {

    }

    /**
     * 读取怪物战士的Ai配置
     * 
     * @param configs
     * @param filePath
     */
    @SuppressWarnings("unchecked")
    public static boolean readLivingAiConfigs(
                    Map<Integer, LivingAiInfo> configs, String filePath) {
        File file = new File(filePath);
        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(file);
            Element element = document.getRootElement();

            List<Element> soldierAiConfigs = element.elements();
            for (Element soldier : soldierAiConfigs) {
                LivingAiInfo soldierAiInfo = new LivingAiInfo();

                // 读取基本配置
                String configID = soldier.attributeValue("configID");
                soldierAiInfo.setConfigID(Integer.parseInt(configID));

                // 读取触发器组配置
                List<Element> triggerList = soldier.element("TriggersGroup")
                                .elements();
                Map<LivingEventType, Map<Integer, String>> triggerMap = new HashMap<>();
                parseTriggerGroup(triggerMap, triggerList);
                soldierAiInfo.setLivingTriggers(triggerMap);

                // 添加进配置缓存
                configs.put(soldierAiInfo.getConfigID(), soldierAiInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("readSoldierConfigs err:", e);
            return false;
        }
        return true;
    }

    /**
     * 解析生物触发器组
     * 
     * @param triggerMap
     * @param triggerList
     */
    private static void parseTriggerGroup(
                    Map<LivingEventType, Map<Integer, String>> triggerMap,
                    List<Element> triggerList) {
        for (Element trigger : triggerList) {
            LivingEventType eventType = LivingEventType.valueOf(trigger
                            .attributeValue("eventType"));
            String params = trigger.attributeValue("params");
            int triggerID = Integer.valueOf(trigger.getText());
            Map<Integer, String> triggerIDs = triggerMap.get(eventType);
            if (triggerIDs == null) {
                triggerIDs = new HashMap<>();
                triggerMap.put(eventType, triggerIDs);
            }
            triggerIDs.put(triggerID, params);
        }
    }

    /**
     * 读取触发器的配置
     * 
     * @param triggerConfigs
     * @param filePath
     */
    @SuppressWarnings("unchecked")
    public static boolean readTriggerConfig(
                    Map<Integer, TriggerInfo> triggerConfigs, String filePath,
                    AiType aiType) {
        File file = new File(filePath);
        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(file);
            Element element = document.getRootElement();

            List<Element> triggerList = element.elements();
            for (Element trigger : triggerList) {
                TriggerInfo triggerInfo = new TriggerInfo();

                // 读取基本配置
                triggerInfo.setTirggerID(Short.parseShort(trigger
                                .attributeValue("triggerID")));
                triggerInfo.setType(aiType);

                // 读取条件配置
                Element conElement = trigger.element("Condition");
                if (conElement != null) {
                    ConditionInfo conditionInfo = new ConditionInfo();
                    parseConditionInfos(conditionInfo, conElement);
                    triggerInfo.setConditionInfo(conditionInfo);
                }

                // 读取动作配置（此时没有区分Conditioin的boolean类型）
                Element actElement = trigger.element("Action");
                if (actElement != null) {
                    ActionInfo actionInfo = new ActionInfo();
                    parseActionInfos(actionInfo, actElement);
                    triggerInfo.setYesActionInfo(actionInfo);
                }

                // condition为true时要执行的节点
                Element yesElement = trigger.element("YesActions");
                if (yesElement != null) {
                    Element yesActElement = yesElement.element("Action");
                    if (yesActElement != null) {
                        ActionInfo actionInfo = new ActionInfo();
                        parseActionInfos(actionInfo, yesActElement);
                        triggerInfo.setYesActionInfo(actionInfo);
                    }
                }

                // condition为false时要执行的节点
                Element noElement = trigger.element("NoActions");
                if (noElement != null) {
                    Element noActElement = noElement.element("Action");
                    if (noActElement != null) {
                        ActionInfo actionInfo = new ActionInfo();
                        parseActionInfos(actionInfo, noActElement);
                        triggerInfo.setNoActionInfo(actionInfo);
                    }
                }
                // 添加进配置缓存
                triggerConfigs.put(triggerInfo.getTirggerID(), triggerInfo);
            }
        } catch (Exception e) {
            LogUtil.error("readTriggerConfig err:", e);
            return false;
        }
        return true;

    }

    /**
     * 解析action结点
     * 
     * @param actionInfo
     * @param actElement
     */
    @SuppressWarnings("unchecked")
    private static void parseActionInfos(ActionInfo actionInfo,
                    Element actElement) {
        actionInfo.setActionID(actElement.getTextTrim());
        actionInfo.setLogicType(actElement.attributeValue("logicType"));
        actionInfo.setNeedNew(true);
        actionInfo.setParams(actElement.attributeValue("param"));
        if (actionInfo.getParams() == null || actionInfo.getParams().isEmpty()) {
            actionInfo.setNeedNew(false);
        }
        List<Element> sonActs = actElement.elements();
        if (sonActs.size() > 0) {
            List<ActionInfo> sonActionInfos = new ArrayList<ActionInfo>();
            actionInfo.setSonActions(sonActionInfos);
            for (Element sonAct : sonActs) {
                ActionInfo sonActionInfo = new ActionInfo();
                sonActionInfos.add(sonActionInfo);
                parseActionInfos(sonActionInfo, sonAct);
            }
        }
    }

    /**
     * 解析条件结点
     * 
     * @param conditionInfo
     * @param conElement
     */
    @SuppressWarnings("unchecked")
    private static void parseConditionInfos(ConditionInfo conditionInfo,
                    Element conElement) {
        // 如果是用来作为父结点的condition有可能没有ID
        conditionInfo.setConditionID(conElement.getTextTrim());
        conditionInfo.setErrMsg(conElement.attributeValue("errMsg"));
        conditionInfo.setLogicType(conElement.attributeValue("logicType"));
        conditionInfo.setNeedNew(true);
        conditionInfo.setParams(conElement.attributeValue("param"));
        if (conditionInfo.getParams() == null
                        || conditionInfo.getParams().isEmpty()) {
            conditionInfo.setNeedNew(false);
        }
        List<Element> subElements = conElement.elements();
        if (subElements.size() > 0) {
            List<ConditionInfo> conList = new ArrayList<ConditionInfo>();
            conditionInfo.setSonInfo(conList);
            for (Element element : subElements) {
                ConditionInfo subConditionInfo = new ConditionInfo();
                conList.add(subConditionInfo);
                parseConditionInfos(subConditionInfo, element);
            }
        }
    }

    /**
     * 读取场景中怪物配置
     * 
     * @param sceneMap
     * @param filePath
     */
    public static boolean reloadSenceMonster(Map<Integer, SceneInfo> sceneMap,
                    String filePath) {
        File file = new File(filePath);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element element = document.getRootElement();
            @SuppressWarnings("unchecked")
            List<Element> sceneList = element.elements();
            for (Element sceneElement : sceneList) {
                SceneInfo sceneInfo = new SceneInfo();
                sceneInfo.setName(sceneElement.attributeValue("name"));
                sceneInfo.setHeigh(Integer.parseInt(sceneElement
                                .attributeValue("heigh")));
                sceneInfo.setWidth(Integer.parseInt(sceneElement
                                .attributeValue("width")));
                sceneInfo.setSceneID(Integer.parseInt(sceneElement
                                .attributeValue("width")));
                @SuppressWarnings("unchecked")
                List<Element> npcElementList = sceneElement.element("npcs")
                                .elements();
                Map<Integer, GameNPCInfo> npcMap = new HashMap<Integer, GameNPCInfo>();
                for (Element npcElt : npcElementList) {
                    GameNPCInfo npc = new GameNPCInfo();
                    parseGameNPCInfo(npcElt, npc);
                    npcMap.put(npc.getMapObjID(), npc);
                }
                sceneInfo.setTalkNPCMap(npcMap);
                @SuppressWarnings("unchecked")
                List<Element> monsterElementList = sceneElement.element(
                                "monsters").elements();
                Map<Integer, GameNPCInfo> monsterList = new HashMap<Integer, GameNPCInfo>();
                for (Element npcElt : monsterElementList) {
                    GameNPCInfo npc = new GameNPCInfo();
                    parseGameNPCInfo(npcElt, npc);
                    npcMap.put(npc.getMapObjID(), npc);
                }
                sceneInfo.setMonsterMap(monsterList);
                sceneMap.put(sceneInfo.getSceneID(), sceneInfo);
            }
        } catch (Exception e) {
            LogUtil.error("加载场景文件错误", e);
            return false;
        }

        return true;
    }

    /**
     * 将Element转化成具体的ganeNPCinfo
     * 
     * @param npcElment
     * @param gameNPC
     */
    private static void parseGameNPCInfo(Element npcElment, GameNPCInfo gameNPC) {
        gameNPC.setAiID(Integer.parseInt(npcElment.attributeValue("aiID")));
        gameNPC.setPosX(Integer.parseInt(npcElment.attributeValue("posX")));
        gameNPC.setPosY(Integer.parseInt(npcElment.attributeValue("posY")));
        gameNPC.setObjectID(Integer.parseInt(npcElment.attributeValue("objID")));
        gameNPC.setName(npcElment.attributeValue("name"));
        gameNPC.setMapObjID(Integer.parseInt(npcElment
                        .attributeValue("mapObjID")));
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        //初始化怪物ai
        Map<Integer, TriggerInfo> map = new HashMap<Integer, TriggerInfo>();
        XMLConfigurationReader.readTriggerConfig(map,
                        "./config/aiConfig/LivingTriggerConfig.xml",
                        AiType.LIVING);
        //System.out.println(map);

        Map<Integer, LivingAiInfo> livingAiMap = new HashMap<Integer, LivingAiInfo>();
        XMLConfigurationReader.readLivingAiConfigs(livingAiMap,
                        "./config/aiConfig/LivingAiConfig.xml");
        //System.out.println(livingAiMap);

        Map<Integer, SceneInfo> sceneInfoMap = new HashMap<Integer, SceneInfo>();
        XMLConfigurationReader.reloadSenceMonster(sceneInfoMap,
                        "./config/scene/xiangyang_scene.xml");
        System.out.println(sceneInfoMap);
    }
}
