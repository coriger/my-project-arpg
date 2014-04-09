/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-21  下午3:47:16
 */
package com.road.arpg.module.misc;

import com.road.arpg.core.manager.module.NormalModule;
import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.module.misc.dao.AcountDao;
import com.road.arpg.module.misc.entity.AcountEntity;
import com.road.arpg.module.misc.protobuff.CommonMessageProto;
import com.road.arpg.module.misc.protobuff.LoginSuccessProto.LoginSuccessProtoMsg;

/**
 * 杂项模块，包括其他模块不适合的所有信息，比如登录
 * 
 * @author Dream.xie
 */
public class CommonModule extends NormalModule {
    /**
     * 帐号DAO
     */
    private AcountDao acountDao = AcountDao.getInstance();

    /**
     * 根据userName 和 site 判断用户是否存在
     * 
     * @param userName
     * @param site
     * @return
     */
    public AcountEntity getByUserNameAndType(String userName, String site) {
        return acountDao.getByUserNameAndType(userName, site);
    }

    /**
     * 创建用户是否成功
     * 
     * @param acount
     * @return
     */
    public boolean createAcount(AcountEntity acount) {
        return acountDao.add(acount);
    }

    /**
     * 登陆或者创建用户
     * 
     * @param userName
     * @param site
     * @param conn
     *            TODO
     * @return TODO
     */
    public boolean loginOrCreateAccount(String userName, String site,
                    Connection conn) {

//        AcountDaoImpl dao = new AcountDaoImpl(DBHelper.createDBHelper(DBHelperType.GAME));
//        Acount acount = dao.getByUserNameAndType(userName, site);
//        if (acount != null) {
//            conn.setId(acount.getPlayerID());
//            return true;
//        }
//        /**
//         * 
//         * 对应数据库数据
//        String sql = "select * from t_g_acount where `USER_NAME_`=? and `SITE_`=?";
//        DBParamWrapper params = new DBParamWrapper();
//        params.put(Types.VARCHAR, userName);
//        params.put(Types.VARCHAR, site);
//        Acount acount = query(sql, params);
//        return acount;
//         */
//        acount = new Acount();
//        acount.setOnLine(1);
//        acount.setLastLoginTime(new Date());
//        acount.setCreateTime(new Date());
//        acount.setLastLoginTime(new Date());
//        acount.setSite(site);
//        acount.setType(1);
//        acount.setUserName(userName);
//        acount.setAdType("");
//        if (!dao.add(acount)) {
//            LogUtil.error("创建账户失败");
//            this.sendCommonMessage(0, this.getResource("createAcountError"), conn);
//            //创建角色不成功的话，关闭连接
//            conn.closeConnection();
//            return false;
//        }
//        acount = dao.getByUserNameAndType(acount.getUserName(), acount.getSite());
//        if (acount == null) {
//            this.sendCommonMessage(0, this.getResource("getAcountError"), conn);
//            //创建角色不成功的话，关闭连接
//            conn.closeConnection();
//            LogUtil.error("创建账户成功，之后获取用户失败");
//            return false;
//        }
//        //是否有已经在线了
//        Connection oldPlayer = GameServer.getInstance().getSocketManager().getConnection(conn);
//        if (oldPlayer != null) {
//            this.sendCommonMessage(1, this.getResource("kickYouDown"), oldPlayer);
//            oldPlayer.closeConnection();
//        }
//        acount.setLastLoginTime(new Date());
//        conn.setState(State.CONNECTED);
//        GameServer.getInstance().getSocketManager().addNewConnection(conn);
//        /**if (!dao.addAndGet(acount))  {
//            
//               boolean result = false;
//        String sql = "insert into t_g_acount(`ID_`,`USER_NAME_`,`SITE_`,`ON_LINE_`,"
//                        + "`BAN_LOGIN_END_TIME_`,`BAN_CHAT_END_TIME_`,`TYPE_`," 
//                        + "`AD_TYPE_`,`CREATE_TIME_`,`LAST_LOGIN_TIME_`) values(?,?,?,?,?,?,?,?,?,?);";
//        DBParamWrapper params = new DBParamWrapper();
//        params.put(Types.BIGINT, acount.getPlayerID());
//        params.put(Types.VARCHAR, acount.getUserName());
//        params.put(Types.VARCHAR, acount.getSite());
//        params.put(Types.TINYINT, acount.getOnLine());
//        params.put(Types.TIMESTAMP, acount.getBanLoginEndTime());
//        params.put(Types.TIMESTAMP, acount.getBanChatEndTime());
//        params.put(Types.TINYINT, acount.getType());
//        params.put(Types.VARCHAR, acount.getAdType());
//        params.put(Types.TIMESTAMP, acount.getCreateTime());
//        params.put(Types.TIMESTAMP, acount.getLastLoginTime());
//        result = getDBHelper().execNoneQuery(sql, params) > -1 ;
//        if (!result) {
//            return false;
//        }
//        String querySql = "select ID_ from t_g_acount where USER_NAME_=? and SITE_=?";
//        DBParamWrapper queryParams = new DBParamWrapper();
//        queryParams.put(Types.VARCHAR, acount.getUserName());
//        queryParams.put(Types.VARCHAR, acount.getSite());
//        Long id = getDBHelper().executeQuery(querySql, queryParams, new DataReader<Long>() {
//            @Override
//            public Long readData(ResultSet rs, Object... objects) throws Exception {
//                return rs.getLong("ID_");
//            }
//        });
//        acount.setPlayerID(id);
//        return true;
//             
//            return null;
//        }*/
//        this.sendLoginSuccess(conn.getId(), conn);
//        return true;
        return true;
    }

    /**
     * 发送公共消息
     * 
     * @param msgType
     * @param content
     * @param conn
     */
    public void sendCommonMessage(int msgType, String content, Connection conn) {
        Message message = new Message(SocketTypeConstant.OUT_COMMON_MESSAGE);
        CommonMessageProto.CommonMessageProtoMsg.Builder msg = CommonMessageProto.CommonMessageProtoMsg
                        .newBuilder();
        msg.setContent(content);
        msg.setType(msgType);
        message.setBody(msg.build().toByteArray());
        conn.writeMessage(message);
    }

    /**
     * 将刷新数据获取数据的包放到下一个包中去 这样更能区分一个账户多个角色
     */
    public void sendLoginSuccess(long playerID, Connection conn) {
        Message message = new Message(SocketTypeConstant.OUT_LOGIN_SUCCESS);
        LoginSuccessProtoMsg.Builder builder = LoginSuccessProtoMsg
                        .newBuilder();
        builder.setPlayerID(playerID);
        message.setBody(builder.build().toByteArray());
        conn.writeMessage(message);
    }

}
