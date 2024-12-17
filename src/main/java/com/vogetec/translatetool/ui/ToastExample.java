package com.vogetec.translatetool.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToastExample {

    public static void main(String[] args) {
        // 创建并显示一个简单的 Swing 窗口
        JFrame frame = new JFrame("Toast Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        // 创建一个按钮，点击后弹出 Toast
        JButton button = new JButton("Show Toast");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showToast("This is a toast message.", 2000);
            }
        });

        // 将按钮添加到窗口中
        frame.getContentPane().add(button, BorderLayout.CENTER);

        // 显示窗口
        frame.setVisible(true);
    }

    public static void showToast(String message, int duration) {
        // 创建一个 JWindow 作为 Toast
        JWindow toast = new JWindow();
        JLabel label = new JLabel(message);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        toast.getContentPane().add(label, BorderLayout.CENTER);

        // 设置 Toast 位置和大小
        toast.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (screenSize.getWidth() - toast.getWidth() - 10);
        int y = (int) (screenSize.getHeight() - toast.getHeight() - 50);
        toast.setLocation(x, y);

        // 设置定时器，在指定时间后关闭 Toast
        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toast.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

        // 显示 Toast
        toast.setVisible(true);
    }
}
