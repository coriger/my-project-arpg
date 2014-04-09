/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-26  下午5:41:37
 */
package com.road.arpg.core.manager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.jolbox.bonecp.BoneCP;
import com.road.arpg.core.util.LogUtil;

/**
 * 
 * @author Dream.xie
 */
public final class DBHelper {

    /**
     * 池
     */
    private BoneCP pool = null;

    /**
     * @param pool
     *            the pool to set
     */
    void setPool(BoneCP pool) {
        this.pool = pool;
    }

    /**
     * @Action INSERT, UPDATE or DELETE
     * @param sql
     *            执行的脚本
     * @return
     */
    public int execNoneQuery(String sql) {
        return executeUpdate(sql, null);
    }

    /**
     * @Action INSERT, UPDATE or DELETE
     * @param sql
     *            执行的脚本
     * @param params
     *            脚本参数
     * @return
     */
    public int executeUpdate(String sql, DBParamWrapper params) {
        int result = 0;
        Connection conn = getConnection();
        if (conn == null) {
            return result;
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            prepareStatement(pstmt, getParams(params));
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            LogUtil.error("执行脚本出错", e);
        } finally {
            closeConn(conn, pstmt);
        }
        return result;
    }

    /**
     * 执行无参查询并返回单一记录
     * 
     * @param sql
     *            执行的脚本
     * @param reader
     *            记录读取接口，实现单一记录读取过程
     * @return
     */
    public <T> T executeQuery(String sql, DataReader<T> reader) {
        return executeQuery(sql, null, reader);
    }

    /**
     * 执行查询并返回单一记录
     * 
     * @param sql
     *            执行的脚本
     * @param params
     *            脚本参数
     * @param reader
     *            记录读取接口，实现单一记录读取过程
     * @param objects
     *            额外传递的参数
     * @return
     */
    public <T> T executeQuery(String sql, DBParamWrapper params,
                    DataReader<T> reader, Object... objects) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T resultData = null;
        Connection conn = getConnection();
        if (conn != null) {
            try {
                pstmt = conn.prepareStatement(sql);
                prepareStatement(pstmt, getParams(params));
                rs = pstmt.executeQuery();
                resultData = reader.readData(rs, objects);
            } catch (Exception e) {
                LogUtil.error("执行脚本出错", e);
            } finally {
                closeConn(conn, pstmt, rs);
            }
        }

        return resultData;
    }

    /**
     * 执行查询并返回单一记录
     * 
     * @param sql
     *            执行的脚本
     * @param executor
     *            statement执行接口，实现单一记录读取过程
     * @return
     */
    public <T> T executeQuery(String sql, DataExecutor<T> executor) {
        return executeQuery(sql, null, executor);
    }

    /**
     * 执行查询并返回单一记录
     * 
     * @param sql
     *            执行的脚本
     * @param params
     *            脚本参数
     * @param executor
     *            statement执行接口，实现单一记录读取过程
     * @param objects
     *            额外传递的参数
     * @return
     */
    public <T> T executeQuery(String sql, DBParamWrapper params,
                    DataExecutor<T> executor, Object... objects) {
        PreparedStatement pstmt = null;
        T resultData = null;
        Connection conn = getConnection();

        if (conn != null) {
            try {
                pstmt = conn.prepareStatement(sql);
                prepareStatement(pstmt, getParams(params));
                resultData = executor.execute(pstmt, objects);
            } catch (Exception e) {
                LogUtil.error("执行脚本出错", e);

            } finally {
                closeConn(conn, pstmt);
            }
        }

        return resultData;
    }

    /**
     * 
     * @param sql
     *            执行批量处理的脚本
     * @param entities
     *            实体集合
     * @param executor
     *            回调
     * @return
     */
    public <T, V> T sqlBatch(String sql, List<V> entities,
                    DataExecutor<T> executor) {
        Connection conn = getConnection();
        if (conn == null) {
            return null;
        }
        PreparedStatement pstmt = null;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            T result = executor.execute(pstmt, entities);
            conn.commit();
            return result;
        } catch (Exception e) {
            LogUtil.error("执行批量脚本出错", e);
        } finally {
            closeConn(conn, pstmt);
        }
        return null;
    }

    /**
     * @Action INSERT_Batch, UPDATE_Batch or DELETE_Batch
     * @param sqlComm
     *            执行的脚本
     * @return 返回影响行数
     */
    public int sqlBatch(List<String> sqlComm) {
        int[] results = null;
        int result = 0;
        Connection conn = getConnection();

        if (conn == null) {
            return result;
        }
        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (int i = 0; i < sqlComm.size(); i++) {
                stmt.addBatch(sqlComm.get(i));
            }
            results = stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            LogUtil.error("执行批量脚本出错", e);
        } finally {
            closeConn(conn, stmt);
        }
        if (results != null) {
            for (int i = 0; i < results.length; i++) {
                result += results[i];
            }
        }
        return result;
    }

    /**
     * 给Statement赋值
     * 
     * @param pstmt
     * @param parms
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(PreparedStatement pstmt,
                    Map<Integer, DBParameter> parms) throws SQLException {
        if (pstmt == null || parms == null) {
            return null;
        }
        for (Map.Entry<Integer, DBParameter> entry : parms.entrySet()) {
            pstmt.setObject(entry.getKey(), entry.getValue().getResult());
        }

        return pstmt;
    }

    /**
     * 获取连接池的连接
     * 
     * @return
     */
    private Connection getConnection() {
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            LogUtil.fatal(e);
        }
        return null;
    }

    /**
     * 获得参数
     * 
     * @param paramWrapper
     * @return
     */
    private Map<Integer, DBParameter> getParams(DBParamWrapper paramWrapper) {
        Map<Integer, DBParameter> params = null;
        if (paramWrapper != null) {
            params = paramWrapper.getParams();
        }
        return params;
    }

    /**
     * 关闭Connection、Statem和ResultSet
     * 
     * @param conn
     * @param stmt
     * @param rs
     */
    private void closeConn(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs == null || rs.isClosed()) {
                return;
            }
            rs.close();
            rs = null;
        } catch (SQLException e) {
            LogUtil.error("关闭Resultset出错", e);
        } finally {
            closeConn(conn, stmt);
        }

    }

    /**
     * 关闭Conne和Statement
     * 
     * @param conn
     * @param stmt
     */
    private void closeConn(Connection conn, Statement stmt) {
        try {
            if (stmt == null || stmt.isClosed()) {
                return;
            }
            if (stmt instanceof PreparedStatement) {
                ((PreparedStatement) stmt).clearParameters();
            }
            stmt.close();
            stmt = null;
        } catch (SQLException e) {
            LogUtil.error("关闭statement出错", e);
        } finally {
            closeConn(conn);
        }
    }

    /**
     * 关闭Connection
     * 
     * @param conn
     */
    private void closeConn(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return;
            }
            conn.setAutoCommit(true);
            conn.close();
            conn = null;
        } catch (SQLException e) {
            LogUtil.error("关闭数据库连接出错", e);
        }
    }
}
