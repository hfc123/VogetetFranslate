package com.vogetec.translatetool.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingDialog extends JDialog {
    private JLabel loadingLabel;

    public LoadingDialog(Frame parent) {
        super(parent, "Loading", true);
        setSize(400,300);
        setModal(false);
        // 创建加载中的消息标签
        loadingLabel = new JLabel("Loading...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(loadingLabel, BorderLayout.CENTER);

    }

    // 开始加载
    public void startLoading() {
        setSize(200, 100); //// 根据内容自适应大小
        setLocationRelativeTo(getParent()); // 居中显示
        setVisible(true); // 显示对话框
    }
}
