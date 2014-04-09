/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:39:44
 */
package com.road.arpg.core.manager.socket;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Socket消息.
 * 
 * @author Dream.xie
 */
public class Message {

    /**
     * 协议体的头部长度，包括协议号，和协议长度
     */
    public static final short HEAD_SIZE = 6;

    /**
     * 包的标识
     */
    public static final short HEADER = 0x7ffe;

    /**
     * 协议号
     */
    private short code;

    /** 包体数据 */
    private byte[] bodyData = null;

    /**
     * 私有构造方法，只能build中使用
     */
    private Message() {
    }

    /**
     * 构造方法
     * 
     * @param code
     *            协议号
     */
    public Message(short code) {
        this.code = code;
    }

    /**
     * 由字节构建包体
     * 
     * @param dataBytes
     *            这个数组中，第一个short是整个包的长度，第二个short是协议编号
     * @return
     */
    public static Message build(byte[] dataBytes) {
        //如果长度不够，返回
        if (dataBytes.length < HEAD_SIZE) {
            return null;
        }
        Message message = new Message();

        ByteBuffer byteBuffer = ByteBuffer.wrap(dataBytes);
        //跳过包体标识，跳过包长度
        byteBuffer.position(4);
        message.code = byteBuffer.getShort();
        int bodyLen = dataBytes.length - HEAD_SIZE;
        //有些协议只需要发送消息头就好
        if (dataBytes.length > HEAD_SIZE) {
            message.bodyData = new byte[bodyLen];
            byteBuffer.get(message.bodyData, 0, bodyLen);
        }
        return message;
    }

    /**
     * 数据包转换为ByteBuffer，包括包头和包体。
     * 
     * @return
     */
    public ByteBuffer toByteBuffer() {
        short len = HEAD_SIZE;
        if (this.bodyData != null) {
            len += (short) this.bodyData.length;
        }
        ByteBuffer buff = ByteBuffer.allocate(len);
        // 插入包成都
        buff.putShort(HEADER);
        // 插入长度
        buff.putShort(len);
        // 先跳过校验和
        buff.putShort(code);
        if (bodyData != null) {
            buff.put(bodyData);
        }
        buff.flip();
        return buff;
    }

    /**
     * 获取包体，包体允许为null。
     * 
     * @return
     */
    public byte[] getBody() {
        return bodyData;
    }

    /**
     * 设置包体，包体允许为null。
     * 
     * @param bytes
     */
    public void setBody(byte[] bytes) {
        this.bodyData = bytes;
    }

    /**
     * @return the code
     */
    public short getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(short code) {
        this.code = code;
    }

    /**
     * 包头的字符串表示形式。
     * 
     * @return
     */
    public String headerToStr() {
        StringBuilder sb = new StringBuilder();
        sb.append(", code: 0x").append(Integer.toHexString(code));
        // sb.append(", playerId: ").append(playerId);
        // sb.append(", empty1: ").append(empty1);
        // sb.append(", empty2: ").append(empty2);
        return sb.toString();
    }

    /**
     * 数据包的字符串表示形式。
     * 
     * @return
     */
    public String detailToStr() {
        String str = null;
        if (bodyData != null) {
            try {
                str = new String(bodyData, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                str = "(UnsupportedEncodingException)";
            }
        }
        return String.format("%s. content:%s.", headerToStr(), str);
    }
}
