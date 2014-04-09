/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-26  下午8:18:33
 */
package com.road.arpg.module.misc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.road.arpg.core.manager.database.BaseDAO;
import com.road.arpg.core.manager.database.DataBaseManager;
import com.road.arpg.module.misc.entity.PlayerEntitiy;

/**
 * Player数据库管理类
 * 
 * @author Dream.xie
 */
public final class PlayerDAO extends BaseDAO<PlayerEntitiy> {
    /**
     * 
     */
    private static final PlayerDAO INSTANCE = new PlayerDAO();

    /**
     * @param helper
     */
    private PlayerDAO() {
        super(DataBaseManager.getInstance().obtainDBHelper(
                        DataBaseManager.DBHelperType.GAME));
    }

    /**
     * @return the instance
     */
    public static PlayerDAO getInstance() {
        return INSTANCE;
    }

    /**
     * 
     */
    @Override
    protected PlayerEntitiy rsToEntity(ResultSet rs) throws SQLException {
        return null;
    }

}
