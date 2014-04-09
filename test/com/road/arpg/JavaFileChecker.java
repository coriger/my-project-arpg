/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-19  下午4:54:55
 */
package com.road.arpg;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.road.arpg.core.util.FileUtil;

/**
 * @author Dream.xie
 */
public final class JavaFileChecker {
    /**
     * 核心包位置
     */
    private static final String CORE_DIR = "E:/eclipseworkspace/arpg/src/com/road/arpg/core";
    /**
     * 所有java文件目录
     */
    private static final String SRC_DIR = "E:/eclipseworkspace/arpg/src";
    /**
     * synchronized同步检测
     */
    private static final Pattern CHECK_SYNCHRONIZED_PATTERN = Pattern
                    .compile(".*synchronized.*");
    /**
     * core包检测
     */
    private static final Pattern CHECK_CORE_PACKAGE_PATTERN = Pattern
                    .compile(".*import\\s+com\\.road\\.arpg\\.(?!core).*");
    /**
     * 忽略要检查同步的文件
     */
    private static Set<String> ingoreSynFiles = new HashSet<>();
    /**
     * 
     */
    static {
        ingoreSynFiles.add("MinaStrictMessageEncoder.java");
        ingoreSynFiles.add("NettyStrictMessageEncoder.java");
        ingoreSynFiles.add("XsocketStrictMessageEncoder.java");
    }

    /**
     * 
     */
    private JavaFileChecker() {
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        check();
    }

    /**
     * 检查
     * 
     * @throws Exception
     */
    private static void check() throws Exception {
        List<File> allFiles = getFiles(new File(SRC_DIR), ingoreSynFiles);
        List<File> coreFiles = getFiles(new File(CORE_DIR), null);
        boolean isSuccessFull = true;
        //检查是否用同步字段
        for (File file : allFiles) {
            if (checkIsError(FileUtil.readFileToString(file),
                            CHECK_SYNCHRONIZED_PATTERN)) {
                System.out.println(file.getName() + "包含synchronized字段!");
                isSuccessFull = false;
            }
        }
        if (!isSuccessFull) {
            System.exit(-1);
        }
        //检测core包
        for (File file : coreFiles) {
            if (checkIsError(FileUtil.readFileToString(file),
                            CHECK_CORE_PACKAGE_PATTERN)) {
                System.out.println(file.getName() + "core包含对其他代码的引用!");
                isSuccessFull = false;
            }
        }
        if (!isSuccessFull) {
            System.exit(-1);
        }

        System.out.println("===经过检查，没有发现JAVA源代码需要注意的问题===");
    }

    /**
     * 检查是否错误,true表示有错误.
     * 
     * @param content
     * @param pattern
     * @return
     */
    private static boolean checkIsError(String content, Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }

    /**
     * 获取指定目录的代码（递归获取）
     * 
     * @param file
     * @param ingoreFiles
     *            要忽略的文件
     * @return
     */
    private static List<File> getFiles(File file, final Set<String> ingoreFiles) {
        List<File> result = new ArrayList<File>();
        if (file.isDirectory()) {
            File[] javaFiles = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (ingoreFiles != null
                                    && ingoreFiles.contains(pathname.getName())) {
                        return false;
                    }
                    return (pathname.getName().endsWith(".java"));
                }
            });
            result.addAll(Arrays.asList(javaFiles));

            File[] dirFils = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });

            for (File dir : dirFils) {
                result.addAll(getFiles(dir, ingoreFiles));
            }
        } else {
            if (ingoreFiles != null && ingoreFiles.contains(file.getName())) {
                return result;
            }
            if (file.getName().endsWith(".java")) {
                result.add(file);
            }
        }
        return result;
    }
}
