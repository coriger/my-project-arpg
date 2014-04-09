/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.util.LogUtil;

/**
 * Mina编码器-明文.
 * 
 * @author dream.xie
 * 
 */
final class MinaPlaintextStrictMessageEncoder extends ProtocolEncoderAdapter {

    /**
     * 编码
     */
    @Override
    public void encode(IoSession session, Object message,
                    ProtocolEncoderOutput out) throws Exception {
        try {
            // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
            Message msg = (Message) message;
            byte[] plainText = msg.toByteBuffer().array();

            int length = plainText.length;
            IoBuffer cipherBuffer = IoBuffer.allocate(length);
            cipherBuffer.put(plainText);
            //写入
            out.write(cipherBuffer.flip());
            out.flush();
        } catch (Exception ex) {
            LogUtil.error("catch error for encoding packet:", ex);
            throw ex;
        }
    }

}
