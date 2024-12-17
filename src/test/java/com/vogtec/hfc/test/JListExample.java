package com.vogtec.hfc.test;

import javax.swing.*;
import java.awt.*;

public class JListExample extends JFrame {

    public JListExample() {
        setTitle("JList Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);

        // 创建列表数据
        String[] data = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

        // 创建 JList，并设置列表数据
        JList<String> list = new JList<>(data);

        // 设置列表选择模式为单选模式
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 添加列表到滚动面板
        JScrollPane scrollPane = new JScrollPane(list);

        // 添加滚动面板到窗口
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JListExample example = new JListExample();
            example.setVisible(true);
        });
    }
}
