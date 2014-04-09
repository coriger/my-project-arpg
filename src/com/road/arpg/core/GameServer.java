/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  上午11:04:25
 */
package com.road.arpg.core;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.road.arpg.core.util.LogUtil;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 游戏服务器，里面可以获得各种其他管理器，比如数据库，日志，定时器,模块管理,错误码机制,通信管理等
 * 
 * @author Dream.xie
 */
@SuppressWarnings("unchecked")
public final class GameServer {
    /**
     * 游戏配置文件.
     */
    private static final String GAME_CFG_FILE = "game.xml";
    /**
     * log4j配置.
     */
    private static final String LOG4J_FILE = "log4j.properties";
    /**
     * 单列累
     */
    private static GameServer instance = new GameServer();
    /**
     * 管齐锂
     */
    private List<IManager> managers = new ArrayList<IManager>();
    /**
     * 国家
     */
    private String country;
    /**
     * 语言
     */
    private String language;
    /**
     * 游戏服务器端口
     */
    private Integer port;
    /**
     * 游戏站点
     */
    private String gameSite;
    /**
     * 版本号
     */
    private String version;

    /**
     * 杂项配置
     */
    private MiscConfig miscConfig = MiscConfig.getInstance();

    /**
     * 默认构造方法
     */
    private GameServer() {
    }

    /**
     * @return the instance
     */
    public static GameServer getInstance() {
        return instance;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @return the gameSite
     */
    public String getGameSite() {
        return gameSite;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @return
     */
    public MiscConfig getMiscConfig() {
        return miscConfig;
    }

    /**
     * 启动
     */
    public void start() {
        try {
            long startTime = System.currentTimeMillis();
            // 配置Log4j
            initLog();
            //初始化杂项配置
            miscConfig.init();
            //初始化配置文件.
            initConfig();
            //初始化管理器.
            initManager();

            LogUtil.info("");
            LogUtil.info(String.format(
                            "-----------游戏服务器启动成功，启动用了%dMS!-----------",
                            System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            System.out.println("-----------服务器启动失败!-----------");
            e.printStackTrace();
            LogUtil.fatal(e);
            System.exit(-1);
        }

    }

    /**
     * 停止
     */
    public void stop() {
        long startTime = System.currentTimeMillis();
        //停止管理器
        for (int i = managers.size() - 1; i >= 0; i--) {
            try {
                managers.get(i).stop();
            } catch (Exception e) {
                LogUtil.fatal(String.format("停止管理器【%s】错误", managers.get(i)
                                .getClass().getName()), e);
            }
        }
        LogUtil.info(String.format("-----------游戏服务器停服成功，停服用了%dms!-----------",
                        System.currentTimeMillis() - startTime));
        System.exit(0);
    }

    /**
     * 初始化log4j
     */
    private void initLog() throws Exception {
        PropertyConfigurator.configure(ProjectPathUtil.getConfigDirPath()
                        + File.separator + LOG4J_FILE);
    }

    /**
     * 初始化管理器 启服的时候严格按照XML配置顺序加载，停服的时候是相反的顺序执行
     * 
     * @throws Exception
     */
    private void initManager() throws Exception {
        Map<String, Boolean> managerMapTmp = new LinkedHashMap<>();

        File configFile = new File(ProjectPathUtil.getConfigDirPath()
                        + File.separator + GAME_CFG_FILE);
        SAXReader reader = new SAXReader();
        Document document = reader.read(configFile);
        List<Element> managerElmts = document.selectNodes("//manager");

        //准备模块匹配是否开启数据.
        String classStr = null;
        Boolean useBool = null;
        for (Element element : managerElmts) {
            useBool = Boolean.parseBoolean(element.attributeValue("use"));
            classStr = element.attributeValue("class");
            managerMapTmp.put(classStr, useBool);
        }

        //设置模块.
        Class<?> clazz = null;
        IManager manager = null;
        for (Map.Entry<String, Boolean> entry : managerMapTmp.entrySet()) {
            if (!entry.getValue()) {
                LogUtil.warn(String.format("【%s】管理器未启用！", entry.getKey()));
            } else {
                clazz = Class.forName(entry.getKey());
                manager = (IManager) clazz.getMethod("getInstance").invoke(
                                clazz);
                long startTime = System.currentTimeMillis();
                //启动
                manager.start();
                LogUtil.info(String.format(
                                "===========【%s】管理器启动成功，启动用了%dMS===========",
                                manager.getClass().getName(),
                                System.currentTimeMillis() - startTime));
                managers.add(manager);
            }
        }
    }

    /**
     * 初始化game.xml文件的基本信息
     */
    private void initConfig() throws Exception {
        File configFile = new File(ProjectPathUtil.getConfigDirPath()
                        + File.separator + GAME_CFG_FILE);
        SAXReader reader = new SAXReader();
        Document document = reader.read(configFile);
        Element countryElmt = (Element) document.selectSingleNode("//country");
        Element languageElmt = (Element) document
                        .selectSingleNode("//language");
        Element portElmt = (Element) document.selectSingleNode("//port");
        Element gameSiteElmt = (Element) document
                        .selectSingleNode("//gameSite");
        Element versionElmt = (Element) document.selectSingleNode("//version");
        this.country = countryElmt.getTextTrim();
        this.language = languageElmt.getTextTrim();
        this.port = Integer.parseInt(portElmt.getTextTrim());
        this.gameSite = gameSiteElmt.getTextTrim();
        this.version = versionElmt.getTextTrim();
    }

    /**
     * 杂项配置
     * 
     * @author Dream.xie
     */
    public static final class MiscConfig {
        /**
         * 
         */
        private static MiscConfig instance = new MiscConfig();
        /**
         * socket类型
         */
        private String socketType = "";
        /**
         * 是否采用密文
         */
        private Boolean ciphertext = true;

        /**
         * command执行时间，超过这个时间记录人日志 毫秒
         */
        private Integer cmdRuntime = 0;

        /**
         * 
         */
        private MiscConfig() {
        }

        /**
         * @throws DocumentException
         */
        private void init() throws DocumentException {
            //加载自带的moudle
            File configFile = new File(ProjectPathUtil.getConfigDirPath()
                            + File.separator + GAME_CFG_FILE);
            SAXReader reader = new SAXReader();
            Document document = reader.read(configFile);
            Element socketTypeElmt = (Element) document
                            .selectSingleNode("//misc/socketType");
            String socketType = socketTypeElmt.getTextTrim().toLowerCase();
            Element cmdRuntimeElmt = (Element) document
                            .selectSingleNode("//misc/cmdRuntime");
            Integer cmdRuntime = Integer.parseInt(cmdRuntimeElmt.getTextTrim());
            Boolean isCiphertext = Boolean.parseBoolean(socketTypeElmt
                            .attributeValue("isCiphertext"));
            setSocketType(socketType);
            setCmdRuntime(cmdRuntime);
            setCiphertext(isCiphertext);
        }

        /**
         * 返回MiscConfig
         */
        private static MiscConfig getInstance() {
            return instance;
        }

        /**
         * @return the socketType
         */
        public String getSocketType() {
            return socketType;
        }

        /**
         * @param socketType
         *            the socketType to set
         */
        private void setSocketType(String socketType) {
            this.socketType = socketType;
        }

        /**
         * @return the cmdRuntime
         */
        public Integer getCmdRuntime() {
            return cmdRuntime;
        }

        /**
         * @param cmdRuntime
         *            the cmdRuntime to set
         */
        private void setCmdRuntime(Integer cmdRuntime) {
            this.cmdRuntime = cmdRuntime;
        }

        /**
         * @return the ciphertext
         */
        public Boolean getCiphertext() {
            return ciphertext;
        }

        /**
         * @param ciphertext
         *            the ciphertext to set
         */
        private void setCiphertext(Boolean ciphertext) {
            this.ciphertext = ciphertext;
        }

    }
}
