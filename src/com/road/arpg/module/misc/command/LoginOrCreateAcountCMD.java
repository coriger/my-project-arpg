/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-27  下午4:01:14
 */
package com.road.arpg.module.misc.command;

import com.road.arpg.core.manager.module.ModuleManager;
import com.road.arpg.core.manager.module.ModuleManager.ModuleType;
import com.road.arpg.core.manager.socket.Connection;
import com.road.arpg.core.manager.socket.ICommand;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.module.misc.CommonModule;
import com.road.arpg.module.misc.protobuff.LoginProto;
import com.road.arpg.module.misc.protobuff.LoginProto.LoginProtoMsg;

/**
 * 玩家登陆指令，如果账户不存在，则创建一个账户
 * 
 * @author shandong.su
 */
public class LoginOrCreateAcountCMD implements ICommand {

    @Override
    public void excute(Connection conn, Message msg) throws Exception {
        LoginProto.LoginProtoMsg protoMsg = LoginProtoMsg.parseFrom(msg
                        .getBody());
        String userName = protoMsg.getUsername();
        String site = protoMsg.getSite();
        CommonModule module = ModuleManager.getInstance().getModule(
                        ModuleType.MISC);
        if (!module.loginOrCreateAccount(userName, site, conn)) {
            conn.closeConnection();
            conn = null;
        }
//        Acount acount = module.getByUserNameAndType(userName, site);
//        if (acount == null) {
//            acount = new Acount();
//            acount.setOnLine(1);
//            acount.setLastLoginTime(new Date());
//            acount.setCreateTime(new Date());
//            acount.setLastLoginTime(new Date());
//            acount.setSite(site);
//            acount.setType(1);
//            acount.setUserName(userName);
//            acount.setAdType("");
//            if (!module.createAcount(acount)) {
//                module.sendCommonMessage(1, GameServer.getInstance().getMsgCodeManager(), conn);
//            }
//        } else {
//            
//        }
    }

}
