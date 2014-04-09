/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-19  下午4:58:01
 */
package com.road.arpg.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author Dream.xie
 */
public final class FileUtil {
    /**
     * 
     */
    private FileUtil() {

    }

    /**
     * 把文件转换成String
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static String readFileToString(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}
