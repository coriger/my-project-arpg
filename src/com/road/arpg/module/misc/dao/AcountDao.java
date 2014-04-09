/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.misc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.road.arpg.core.manager.database.BaseDAO;
import com.road.arpg.core.manager.database.DBParamWrapper;
import com.road.arpg.core.manager.database.DataBaseManager;
import com.road.arpg.core.manager.database.DataExecutor;
import com.road.arpg.core.manager.database.DataReader;
import com.road.arpg.module.misc.entity.AcountEntity;

/**
 * 
 * @author shandong.su
 */
@SuppressWarnings("unchecked")
public final class AcountDao extends BaseDAO<AcountEntity> {
    /**
     * 
     */
    private static final AcountDao INSTANCE = new AcountDao();

    /**
     * 
     * @param helper
     */
    private AcountDao() {
        super(DataBaseManager.getInstance().obtainDBHelper(
                        DataBaseManager.DBHelperType.GAME));
    }

    /**
     * @return the instance
     */
    public static AcountDao getInstance() {
        return INSTANCE;
    }

    /**
     * 
     * @param acount
     * @return
     */
    public boolean addAndGet(AcountEntity acount) {
        boolean result = false;
        String sql = "insert into t_g_acount(`ID_`,`USER_NAME_`,`SITE_`,`ON_LINE_`,"
                        + "`BAN_LOGIN_END_TIME_`,`BAN_CHAT_END_TIME_`,`TYPE_`,"
                        + "`AD_TYPE_`,`CREATE_TIME_`,`LAST_LOGIN_TIME_`) values(?,?,?,?,?,?,?,?,?,?);";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.BIGINT, acount.getPlayerID());
        params.put(Types.VARCHAR, acount.getUserName());
        params.put(Types.VARCHAR, acount.getSite());
        params.put(Types.TINYINT, acount.getOnLine());
        params.put(Types.TIMESTAMP, acount.getBanLoginEndTime());
        params.put(Types.TIMESTAMP, acount.getBanChatEndTime());
        params.put(Types.TINYINT, acount.getType());
        params.put(Types.VARCHAR, acount.getAdType());
        params.put(Types.TIMESTAMP, acount.getCreateTime());
        params.put(Types.TIMESTAMP, acount.getLastLoginTime());
        result = getDBHelper().executeUpdate(sql, params) > -1;
        if (!result) {
            return false;
        }
        String querySql = "select ID_ from t_g_acount where USER_NAME_=? and SITE_=?";
        DBParamWrapper queryParams = new DBParamWrapper();
        queryParams.put(Types.VARCHAR, acount.getUserName());
        queryParams.put(Types.VARCHAR, acount.getSite());
        Long id = getDBHelper().executeQuery(querySql, queryParams,
                        new DataReader<Long>() {
                            @Override
                            public Long readData(ResultSet rs,
                                            Object... objects) throws Exception {
                                return rs.getLong("ID_");
                            }
                        });
        acount.setPlayerID(id);
        return true;
    }

    /**
     * 
     * @param acount
     * @return
     */
    public boolean add(AcountEntity acount) {
        boolean result = false;
        String sql = "insert into t_g_acount(`ID_`,`USER_NAME_`,`SITE_`,`ON_LINE_`,"
                        + "`BAN_LOGIN_END_TIME_`,`BAN_CHAT_END_TIME_`,`TYPE_`,"
                        + "`AD_TYPE_`,`CREATE_TIME_`,`LAST_LOGIN_TIME_`) values(?,?,?,?,?,?,?,?,?,?);";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.BIGINT, acount.getPlayerID());
        params.put(Types.VARCHAR, acount.getUserName());
        params.put(Types.VARCHAR, acount.getSite());
        params.put(Types.TINYINT, acount.getOnLine());
        params.put(Types.TIMESTAMP, acount.getBanLoginEndTime());
        params.put(Types.TIMESTAMP, acount.getBanChatEndTime());
        params.put(Types.TINYINT, acount.getType());
        params.put(Types.VARCHAR, acount.getAdType());
        params.put(Types.TIMESTAMP, acount.getCreateTime());
        params.put(Types.TIMESTAMP, acount.getLastLoginTime());
        result = getDBHelper().executeUpdate(sql, params) > -1;
        return result;
    }

    /**
     * 
     * @param acount
     * @return
     */
    public boolean update(AcountEntity acount) {
        boolean result = false;
        String sql = "update t_g_acount set `USER_NAME_`=?,`SITE_`=?,`ON_LINE_`=?,"
                        + "`BAN_LOGIN_END_TIME_`=?,`BAN_CHAT_END_TIME_`=?,`TYPE_`=?,`AD_TYPE_`=?,"
                        + "`CREATE_TIME_`=?,`LAST_LOGIN_TIME_`=? where `ID_`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.VARCHAR, acount.getUserName());
        params.put(Types.VARCHAR, acount.getSite());
        params.put(Types.TINYINT, acount.getOnLine());
        params.put(Types.TIMESTAMP, acount.getBanLoginEndTime());
        params.put(Types.TIMESTAMP, acount.getBanChatEndTime());
        params.put(Types.TINYINT, acount.getType());
        params.put(Types.VARCHAR, acount.getAdType());
        params.put(Types.TIMESTAMP, acount.getCreateTime());
        params.put(Types.TIMESTAMP, acount.getLastLoginTime());
        params.put(Types.BIGINT, acount.getPlayerID());
        result = getDBHelper().executeUpdate(sql, params) > -1;
        return result;
    }

    /**
     * 
     * @param acount
     * @return
     */
    public boolean delete(AcountEntity acount) {
        boolean result = false;
        String sql = "delete from t_g_acount where `ID_`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.BIGINT, acount.getPlayerID());
        result = getDBHelper().executeUpdate(sql, params) > -1;
        return result;
    }

    /**
     * 
     * @param acount
     * @return
     */
    public boolean addOrUpdate(AcountEntity acount) {
        boolean result = false;
        String sql = "insert into t_g_acount(`ID_`,`USER_NAME_`,`SITE_`,`ON_LINE_`,"
                        + "`BAN_LOGIN_END_TIME_`,`BAN_CHAT_END_TIME_`,`TYPE_`,`AD_TYPE_`,"
                        + "`CREATE_TIME_`,`LAST_LOGIN_TIME_`) values(?,?,?,?,?,?,?,?,?,?) on"
                        + "DUPLICATE KEY update `USER_NAME_`=?,`SITE_`=?,`ON_LINE_`=?,`BAN_LOGIN_END_TIME_`=?,"
                        + "`BAN_CHAT_END_TIME_`=?,`TYPE_`=?,`AD_TYPE_`=?,`CREATE_TIME_`=?,`LAST_LOGIN_TIME_`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.BIGINT, acount.getPlayerID());
        params.put(Types.VARCHAR, acount.getUserName());
        params.put(Types.VARCHAR, acount.getSite());
        params.put(Types.TINYINT, acount.getOnLine());
        params.put(Types.TIMESTAMP, acount.getBanLoginEndTime());
        params.put(Types.TIMESTAMP, acount.getBanChatEndTime());
        params.put(Types.TINYINT, acount.getType());
        params.put(Types.VARCHAR, acount.getAdType());
        params.put(Types.TIMESTAMP, acount.getCreateTime());
        params.put(Types.TIMESTAMP, acount.getLastLoginTime());
        params.put(Types.VARCHAR, acount.getUserName());
        params.put(Types.VARCHAR, acount.getSite());
        params.put(Types.TINYINT, acount.getOnLine());
        params.put(Types.TIMESTAMP, acount.getBanLoginEndTime());
        params.put(Types.TIMESTAMP, acount.getBanChatEndTime());
        params.put(Types.TINYINT, acount.getType());
        params.put(Types.VARCHAR, acount.getAdType());
        params.put(Types.TIMESTAMP, acount.getCreateTime());
        params.put(Types.TIMESTAMP, acount.getLastLoginTime());
        result = getDBHelper().executeUpdate(sql, params) > -1;
        return result;
    }

    /**
     * 
     * @param ids
     * @return
     */
    public boolean deleteByKey(Object... ids) {
        boolean result = false;
        String sql = "delete from t_g_acount where `ID_`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.BIGINT, ids[0]);
        result = getDBHelper().executeUpdate(sql, params) > -1;
        return result;
    }

    /**
     * 
     * @param ids
     * @return
     */
    public AcountEntity getByKey(Object... ids) {
        String sql = "select * from t_g_acount where `ID_`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.BIGINT, ids[0]);
        AcountEntity acount = query(sql, params);
        return acount;
    }

    /**
     * 
     * @param userName
     * @param site
     * @return
     */
    public AcountEntity getByUserNameAndType(String userName, String site) {
        String sql = "select * from t_g_acount where `USER_NAME_`=? and `SITE_`=?";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.VARCHAR, userName);
        params.put(Types.VARCHAR, site);
        AcountEntity acount = query(sql, params);
        return acount;
    }

    /**
     * 
     * @return
     */
    public List<AcountEntity> listAll() {
        String sql = "select * from t_g_acount;";
        List<AcountEntity> acounts = queryList(sql);
        return acounts;
    }

    /**
     * 
     * @param acounts
     * @return
     */
    public int[] addOrUpdateBatch(List<AcountEntity> acounts) {
        String sql = "insert into t_g_acount(`ID_`,`USER_NAME_`,`SITE_`,`ON_LINE_`,"
                        + "`BAN_LOGIN_END_TIME_`,`BAN_CHAT_END_TIME_`,`TYPE_`,`AD_TYPE_`,`CREATE_TIME_`,"
                        + "`LAST_LOGIN_TIME_`) values(?,?,?,?,?,?,?,?,?,?) on DUPLICATE KEY "
                        + "update `USER_NAME_`=?,`SITE_`=?,`ON_LINE_`=?,`BAN_LOGIN_END_TIME_`=?,"
                        + "`BAN_CHAT_END_TIME_`=?,`TYPE_`=?,`AD_TYPE_`=?,`CREATE_TIME_`=?,`LAST_LOGIN_TIME_`=?;";
        int[] effectedRows = getDBHelper().sqlBatch(sql, acounts,
                        new DataExecutor<int[]>() {
                            @Override
                            public int[] execute(PreparedStatement statement,
                                            Object... objects) throws Exception {
                                List<AcountEntity> acounts = (List<AcountEntity>) objects[0];
                                for (AcountEntity acount : acounts) {
                                    DBParamWrapper params = new DBParamWrapper();
                                    params.put(Types.BIGINT,
                                                    acount.getPlayerID());
                                    params.put(Types.VARCHAR,
                                                    acount.getUserName());
                                    params.put(Types.VARCHAR, acount.getSite());
                                    params.put(Types.TINYINT,
                                                    acount.getOnLine());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getBanLoginEndTime());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getBanChatEndTime());
                                    params.put(Types.TINYINT, acount.getType());
                                    params.put(Types.VARCHAR,
                                                    acount.getAdType());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getCreateTime());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getLastLoginTime());
                                    params.put(Types.VARCHAR,
                                                    acount.getUserName());
                                    params.put(Types.VARCHAR, acount.getSite());
                                    params.put(Types.TINYINT,
                                                    acount.getOnLine());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getBanLoginEndTime());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getBanChatEndTime());
                                    params.put(Types.TINYINT, acount.getType());
                                    params.put(Types.VARCHAR,
                                                    acount.getAdType());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getCreateTime());
                                    params.put(Types.TIMESTAMP,
                                                    acount.getLastLoginTime());
                                    statement = getDBHelper().prepareStatement(
                                                    statement,
                                                    params.getParams());
                                    statement.addBatch();
                                }
                                return statement.executeBatch();
                            }
                        });
        return effectedRows;
    }

    /**
     * 
     * @param acounts
     * @return
     */
    public int[] deleteBatch(List<AcountEntity> acounts) {
        String sql = "delete from t_g_acount where `ID_`=?;";
        int[] effectedRows = getDBHelper().sqlBatch(sql, acounts,
                        new DataExecutor<int[]>() {
                            @Override
                            public int[] execute(PreparedStatement statement,
                                            Object... objects) throws Exception {
                                List<AcountEntity> acounts = (List<AcountEntity>) objects[0];
                                for (AcountEntity acount : acounts) {
                                    DBParamWrapper params = new DBParamWrapper();
                                    params.put(Types.BIGINT,
                                                    acount.getPlayerID());
                                    statement = getDBHelper().prepareStatement(
                                                    statement,
                                                    params.getParams());
                                    statement.addBatch();
                                }
                                return statement.executeBatch();
                            }
                        });
        return effectedRows;
    }

    @Override
    public AcountEntity rsToEntity(ResultSet rs) throws SQLException {
        AcountEntity acount = new AcountEntity();
        acount.setPlayerID(rs.getLong("ID_"));
        acount.setUserName(rs.getString("USER_NAME_"));
        acount.setSite(rs.getString("SITE_"));
        acount.setOnLine(rs.getInt("ON_LINE_"));
        acount.setBanLoginEndTime(rs.getTimestamp("BAN_LOGIN_END_TIME_"));
        acount.setBanChatEndTime(rs.getTimestamp("BAN_CHAT_END_TIME_"));
        acount.setType(rs.getInt("TYPE_"));
        acount.setAdType(rs.getString("AD_TYPE_"));
        acount.setCreateTime(rs.getTimestamp("CREATE_TIME_"));
        acount.setLastLoginTime(rs.getTimestamp("LAST_LOGIN_TIME_"));
        return acount;
    }

}