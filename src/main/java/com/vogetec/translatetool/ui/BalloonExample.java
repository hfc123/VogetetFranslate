package com.vogetec.translatetool.ui;

import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.Balloon.Position;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;

import javax.swing.*;
import java.awt.*;

public class BalloonExample {
    public static void main(String[] args) {
        // 获取主窗口
        Frame parentFrame = WindowManager.getInstance().getFrame(null);

        // 创建一个空的标签，并设置消息文本和背景颜色
        JLabel label = new JLabel("Hello, world!");
        label.setBackground(JBColor.YELLOW);
        label.setOpaque(true);

        // 创建 BalloonBuilder 并设置气球的内容和属性
        BalloonBuilder balloonBuilder = JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("This is a balloon", null, JBColor.YELLOW, null)
                .setFadeoutTime(5000) // 5 秒后消失
                .setBorderColor(JBColor.BLACK)
                .setShowCallout(true)
                .setHideOnClickOutside(true)
                .setHideOnAction(true);

        // 创建 Balloon 并显示在屏幕上
        Balloon balloon = balloonBuilder.createBalloon();
        balloon.show(new RelativePoint(parentFrame, new Point(100, 100)), Position.above);
    }
}
