package com.vogetec.translatetool.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputDialogExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 创建 JFrame
            JFrame frame = new JFrame("Input Dialog Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 创建按钮并添加事件监听器
            JButton button = new JButton("显示输入框");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 创建一个新的 JDialog
                    JDialog dialog = new JDialog(frame, "输入框", true);
                    dialog.setLayout(new GridLayout(3, 2));

                    // 创建标签和文本框
                    JLabel idLabel = new JLabel("ID:");
                    JTextField idField = new JTextField();
                    JLabel secretLabel = new JLabel("Secret:");
                    JTextField secretField = new JTextField();

                    // 创建确定和取消按钮
                    JButton okButton = new JButton("确定");
                    JButton cancelButton = new JButton("取消");

                    // 添加确定按钮的事件监听器
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String id = idField.getText();
                            String secret = secretField.getText();
                            System.out.println("ID: " + id);
                            System.out.println("Secret: " + secret);
                            // 这里可以添加其他逻辑，比如保存输入值，关闭窗口等
                            dialog.dispose(); // 关闭对话框
                        }
                    });

                    // 添加取消按钮的事件监听器
                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // 关闭对话框
                            dialog.dispose();
                        }
                    });

                    // 将组件添加到对话框中
                    dialog.add(idLabel);
                    dialog.add(idField);
                    dialog.add(secretLabel);
                    dialog.add(secretField);
                    dialog.add(okButton);
                    dialog.add(cancelButton);

                    // 设置对话框大小并显示
                    dialog.setSize(300, 150);
                    dialog.setVisible(true);
                }
            });

            // 将按钮添加到 JFrame 中
            frame.add(button, BorderLayout.CENTER);

            // 设置 JFrame 大小并显示
            frame.setSize(300, 150);
            frame.setVisible(true);
        });
    }
}
