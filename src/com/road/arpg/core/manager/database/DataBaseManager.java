/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:56:44
 */
package com.road.arpg.core.manager.database;

import java.io.File;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.road.arpg.core.IManager;
import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 数据库管理器.
 * 
 * @author Dream.xie
 */
public final class DataBaseManager implements IManager {
    /**
     * 
     */
    private static DataBaseManager instance = new DataBaseManager();
    /**
     * 数据库配置文件
     */
    private static final String GAME_CFG_FILE = "game.xml";
    /**
     * gameDB connection pool
     */
    private BoneCP gameDBCP = null;
    /**
     * baseDB connection pool
     */
    private BoneCP baseDBCP = null;
    /**
     * logDB connection pool
     */
    private BoneCP logDBCP = null;

    /**
     * 传入配置文件路径.
     * 
     * @param cfgFile
     */
    private DataBaseManager() {
    }

    /**
     * 
     * @return
     */
    public static DataBaseManager getInstance() {
        return instance;
    }

    /**
     * 
     */
    @Override
    public void stop() throws Exception {
    }

    /**
     * 获取Base数据库的连接.
     * 
     * @return
     */
    BoneCP getGameDBPool() {
        return gameDBCP;
    }

    /**
     * 获取Base数据库的连接.
     * 
     * @return
     */
    BoneCP getBaseDBPool() {
        return baseDBCP;
    }

    /**
     * 获取Base数据库的连接.
     * 
     * @return
     */
    BoneCP getLogDBPool() {
        return logDBCP;
    }

    /**
     * 获得DBHelp。
     * 
     * @param dbHelperType
     * @return
     */
    public DBHelper obtainDBHelper(DBHelperType dbHelperType) {
        DBHelper dbHelper = new DBHelper();
        switch (dbHelperType) {
            case GAME:
                dbHelper.setPool(DataBaseManager.getInstance().getGameDBPool());
                break;
            case BASE:
                dbHelper.setPool(DataBaseManager.getInstance().getBaseDBPool());
                break;
            case LOG:
                dbHelper.setPool(DataBaseManager.getInstance().getLogDBPool());
                break;
            default:
                break;
        }
        return dbHelper;
    }

    /**
     * 
     */
    @Override
    public void start() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(ProjectPathUtil
                        .getConfigDirPath() + File.separator + GAME_CFG_FILE));
        gameDBCP = new BoneCP(getBoneCPConfig(document, "gameDB"));
        baseDBCP = new BoneCP(getBoneCPConfig(document, "baseDB"));
        logDBCP = new BoneCP(getBoneCPConfig(document, "logDB"));
    }

    /**
     * 根据前缀获得不同数据库的BonceCPConfig
     * 
     * @param databaseCfg
     * @param prefix
     * @throws SQLException
     */
    private BoneCPConfig getBoneCPConfig(Document document, String prefix)
                    throws SQLException {
        Element urlElmt = (Element) document.selectSingleNode(String.format(
                        "//%s/url", prefix));
        Element usernameElmt = (Element) document.selectSingleNode(String
                        .format("//%s/username", prefix));
        Element passwordElmt = (Element) document.selectSingleNode(String
                        .format("//%s/password", prefix));
        Element partitionCountElmt = (Element) document.selectSingleNode(String
                        .format("//%s/partitionCount", prefix));
        Element minConnectionsPerPartitionElmt = (Element) document
                        .selectSingleNode(String.format(
                                        "//%s/minConnectionsPerPartition",
                                        prefix));
        Element maxConnectionsPerPartitionElmt = (Element) document
                        .selectSingleNode(String.format(
                                        "//%s/maxConnectionsPerPartition",
                                        prefix));
        Element acquireIncrementElmt = (Element) document
                        .selectSingleNode(String.format(
                                        "//%s/acquireIncrement", prefix));

        String url = urlElmt.getTextTrim();
        String username = usernameElmt.getTextTrim();
        String password = passwordElmt.getTextTrim();
        int partitionCount = Integer.parseInt(partitionCountElmt.getTextTrim());
        int minConnectionsPerPartition = Integer
                        .parseInt(minConnectionsPerPartitionElmt.getTextTrim());
        int maxConnectionsPerPartition = Integer
                        .parseInt(maxConnectionsPerPartitionElmt.getTextTrim());
        int acquireIncrement = Integer.parseInt(acquireIncrementElmt
                        .getTextTrim());
        BoneCPConfig boneConf = new BoneCPConfig();
        boneConf.setJdbcUrl("jdbc:mysql://" + url);
        boneConf.setUsername(username);
        boneConf.setPassword(password);
        boneConf.setPartitionCount(partitionCount);
        boneConf.setMinConnectionsPerPartition(minConnectionsPerPartition);
        boneConf.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
        boneConf.setAcquireIncrement(acquireIncrement);
        return boneConf;
    }

    /**
     * 数据库帮助类类型
     * 
     * @author Dream.xie
     */
    public static enum DBHelperType {
        /**
         * 游戏库
         */
        GAME,
        /**
         * 基础库
         */
        BASE,
        /**
         * 日志库
         */
        LOG;

    }

}
