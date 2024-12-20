package com.vogetec.translatetool.utils;

import com.intellij.notification.*;

//主要输出信息类
public class Logger {
    private static String NAME;
    private static int LEVEL = 0;

    public static final int DEBUG = 3;
    public static final int INFO = 2;
    public static final int WARN = 1;
    public static final int ERROR = 0;

    //初始化，我放在Action那里：Logger.init("JoLiveBuild", Logger.DEBUG);
    public static void init(String name, int level) {
        NAME = name;
        LEVEL = level;
        NotificationsConfiguration.getNotificationsConfiguration().register(NAME, NotificationDisplayType.NONE);
    }

    public static void debug(String text) {
        if (LEVEL >= DEBUG) {
            Notifications.Bus.notify(
                    new Notification(NAME, NAME + " [DEBUG]", text, NotificationType.INFORMATION));
        }
    }

    public static void info(String text) {
        if (LEVEL > INFO) {
            Notifications.Bus.notify(
                    new Notification(NAME, NAME + " [INFO]", text, NotificationType.INFORMATION));
        }
    }

    public static void warn(String text) {
        if (LEVEL > WARN) {
            Notifications.Bus.notify(
                    new Notification(NAME, NAME + " [WARN]", text, NotificationType.WARNING));
        }
    }

    public static void error(String text) {
        if (LEVEL > ERROR) {
            Notifications.Bus.notify(
                    new Notification(NAME, NAME + " [ERROR]", text, NotificationType.ERROR));
        }
    }
}