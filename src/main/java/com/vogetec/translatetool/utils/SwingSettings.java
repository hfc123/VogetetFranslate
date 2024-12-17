package com.vogetec.translatetool.utils;

import java.io.*;
import java.util.Properties;

public class SwingSettings {
    private static final String PROPERTIES_FILE = "settings.properties";

    public static void saveSettings(String key, String value) {
        try {
            Properties properties = new Properties();

            // 加载已有的 properties 文件（如果存在）
            File file = new File(PROPERTIES_FILE);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                }
            }

            // 设置新的属性值
            properties.setProperty(key, value);

            // 保存 properties 到文件
            try (FileOutputStream fos = new FileOutputStream(PROPERTIES_FILE)) {
                properties.store(fos, null);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getSetting(String key) {
        try {
            Properties properties = new Properties();

            // 加载 properties 文件
            try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
                properties.load(fis);
            }

            // 获取属性值
            return properties.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
