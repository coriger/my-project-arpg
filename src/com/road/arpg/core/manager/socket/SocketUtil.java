/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-10  上午10:37:43
 */
package com.road.arpg.core.manager.socket;

import java.util.Arrays;

/**
 * Socket工具类
 * 
 * @author Dream.xie
 */
public final class SocketUtil {
    /**
     * 默认密钥
     */
    private static final int[] DEFAULT_KEY = new int[] { 0xae, 0xbf, 0x56,
                    0x78, 0xab, 0xcd, 0xef, 0xf1 };

    /**
     * 
     */
    private SocketUtil() {

    }

    /**
     * 加密的过程
     * 
     * @param encryptKey
     * @param data
     * @return
     */
    public static byte[] encoder(byte[] plainText, int[] encryptKey) {
        int lastCipherByte = 0;
        // 加密首字节
        lastCipherByte = (byte) ((plainText[0] ^ encryptKey[0]) & 0xff);
        plainText[0] = (byte) lastCipherByte;
        //加密第二个字节
        encryptKey[1] = (((encryptKey[1] ^ lastCipherByte)) & 0xff);
        lastCipherByte = (((plainText[1] ^ encryptKey[1]) & 0xff) + lastCipherByte) & 0xff;
        plainText[1] = (byte) lastCipherByte;

        // 循环加密
        int keyIndex = 0;
        int step = plainText.length % 7 + (lastCipherByte & 0x7);
        //包长度不加密
        for (int i = 4; i < plainText.length;) {
            keyIndex = i & 0x7;
            encryptKey[keyIndex] = (((encryptKey[keyIndex] + lastCipherByte) ^ i) & 0xff);
            lastCipherByte = (((plainText[i] ^ encryptKey[keyIndex]) & 0xff) + lastCipherByte) & 0xff;
            plainText[i] = (byte) lastCipherByte;
            i += step;
        }
        return plainText;
    }

    /**
     * 解密整段数据
     * 
     * @param data
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, int[] decryptKey) {
        if (data.length == 0) {
            return data;
        }
        int length = data.length , keyIndex;
        int lastCipherByte;
        int plainText;
        int key;

        // 解密首字节
        lastCipherByte = data[0] & 0xff;
        data[0] ^= decryptKey[0];

        //解密第二个字节
        key = ((decryptKey[1] ^ lastCipherByte));
        plainText = (((data[1] & 0xff) - lastCipherByte) ^ key) & 0xff;
        // 更新变量值
        lastCipherByte = data[1] & 0xff;
        data[1] = (byte) plainText;
        decryptKey[1] = (key & 0xff);
        int step = data.length % 7 + (lastCipherByte & 0x7);
        for (int i = 4; i < length;) {
            keyIndex = i & 0x7;
            // 解密当前字节
            key = ((decryptKey[keyIndex] + lastCipherByte) ^ i);
            plainText = (((data[i] & 0xff) - lastCipherByte) ^ key) & 0xff;
            // 更新变量值
            lastCipherByte = data[i] & 0xff;
            data[i] = (byte) plainText;
            decryptKey[keyIndex] = (key & 0xff);
            //i加上密文的data[i]
            i += (step);
        }
        return data;
    }

    /**
     * 拷贝默认key
     * 
     * @return
     */
    public static int[] copyDefaultKey() {
        return Arrays.copyOf(SocketUtil.DEFAULT_KEY,
                        SocketUtil.DEFAULT_KEY.length);
    }

}
