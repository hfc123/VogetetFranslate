package com.vogetec.translatetool.utils;

import com.intellij.ide.util.PropertiesComponent;

public class MyPluginSettings {
    private static final String PROPERTY_KEY_ID = "baidu.id";
    private static final String PROPERTY_KEY_SECRET = "baidu.secret";

    public static void saveSettings(String id, String secret) {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue(PROPERTY_KEY_ID, id);
        propertiesComponent.setValue(PROPERTY_KEY_SECRET, secret);
    }

    public static String getId() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        return propertiesComponent.getValue(PROPERTY_KEY_ID);
    }

    public static String getSecret() {
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        return propertiesComponent.getValue(PROPERTY_KEY_SECRET);
    }
}
