/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package com.road.arpg.core.manager.socket.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import com.road.arpg.core.manager.socket.Message;
import com.road.arpg.core.util.LogUtil;

/**
 * MINA解码器-明文.
 * 
 * @author dream.xie
 * 
 */
final class MinaPlaintextStrictMessageDecoder extends CumulativeProtocolDecoder {

    /**
     * 解码
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
                    ProtocolDecoderOutput out) throws Exception {
        if (in.remaining() < 4) {
            // 剩余不足4字节，不足以解析数据包头，暂不处理
            return false;
        }
        int header = in.getShort();
        int packetLength = in.getShort();
        // 预解密长度信息成功，回溯位置
        in.position(in.position() - 4);
        //如果不是标识头，发送给客户端说，断开连接
        if (header != Message.HEADER || packetLength < Message.HEAD_SIZE) {
            // 数据包长度错误，断开连接
            LogUtil.error(String
                            .format("error packet length: packetlength=%d Packet.HDR_SIZE=%d",
                                            packetLength, Message.HEAD_SIZE));
            LogUtil.error(String.format("Disconnect the client:%s",
                            session.getRemoteAddress()));
            session.close(true);
            return false;
        }

        if (in.remaining() < packetLength) {
            // 数据长度不足，等待下次接收
            return false;
        }

        // 读取数据并解密数据
        byte[] data = new byte[packetLength];
        in.get(data, 0, packetLength);
        Message packet = Message.build(data);
        if (packet != null) {
            out.write(packet);
        }
        return true;
    }
}
