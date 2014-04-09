/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-20  下午2:30:02
 */
package com.road.arpg.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.road.arpg.core.util.ProjectPathUtil;

/**
 * 自定义类加载器，负责加载lib和bin以及module里面的lib和classes下面加载类
 * 
 * @author Dream.xie
 */
class ArpgClassLoader extends URLClassLoader {

    /**
     * ARPG的包
     */
    private static final String ARPG_PACKAGE = "com.road.arpg";

    /**
     * 构造函数.
     * 
     * @param parent
     *            这个参数可以为null
     * @param urls
     *            目录
     * @throws java.net.MalformedURLException
     * 
     */
    ArpgClassLoader(ClassLoader parent, URL[] urls)
                    throws MalformedURLException {
        super(urls, parent);
        for (URL url : urls) {
            File[] jars = new File(url.getFile())
                            .listFiles(new FilenameFilter() {
                                public boolean accept(File dir, String name) {
                                    boolean accept = false;
                                    String smallName = name.toLowerCase();
                                    if (smallName.endsWith(".jar")) {
                                        accept = true;
                                    }
                                    return accept;
                                }
                            });

            // 如果没有找到jar包，就返回。
            if (jars == null) {
                return;
            }

            for (int i = 0; i < jars.length; i++) {
                if (jars[i].isFile()) {
                    addURL(jars[i].toURI().toURL());
                }
            }
        }
    }

    /**
     * 加载ARPG的类
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith(ARPG_PACKAGE)) {
            return loadArpgClass(name);
        } else {
            return super.loadClass(name);
        }
    }

    /* (non-Javadoc)
     * @see java.net.URLClassLoader#findClass(java.lang.String)
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.startsWith(ARPG_PACKAGE)) {
            return loadArpgClass(name);
        } else {
            return super.findClass(name);
        }
    }

    /**
     * 加载ARPG的类
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    private Class<?> loadArpgClass(String name) throws ClassNotFoundException {
        //查看是否已经加载
        Class<?> clazz = findLoadedClass(name);
        if (clazz == null) {
            File file = new File(ProjectPathUtil.getBinDirPath()
                            + File.separator + "main" + File.separator
                            + name.replaceAll("\\.", "/") + ".class");
            FileInputStream fis = null;
            ByteArrayOutputStream baos = null;
            byte[] bytes = new byte[1024 * 10];
            int len = -1;
            try {
                fis = new FileInputStream(file);
                baos = new ByteArrayOutputStream();

                while ((len = fis.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                bytes = baos.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (baos != null) {
                        baos.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            clazz = defineClass(name, bytes, 0, bytes.length);
            //定义包
            String packageName = name.substring(0, name.lastIndexOf('.'));
            if (getPackage(packageName) == null) {
                definePackage(packageName, null, null, null, null, null, null,
                                null);
            }
            return clazz;
        } else {
            return clazz;
        }
    }
}
