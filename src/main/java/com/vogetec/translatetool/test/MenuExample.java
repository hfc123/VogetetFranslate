package com.vogetec.translatetool.test;

//import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.event.*;

public class MenuExample {
    public static void main(String[] args) {
     //
        // 创建 JFrame
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();

        // 创建 File 菜单
        JMenu fileMenu = new JMenu("File");
        
        // 创建 File 菜单项
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        
        // 添加菜单项到 File 菜单
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator(); // 添加分隔线
        fileMenu.add(exitMenuItem);

        // 创建 Help 菜单
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        // 将菜单添加到菜单栏
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // 设置菜单栏到 JFrame
        frame.setJMenuBar(menuBar);

        // 添加事件监听器
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 设置 JFrame 大小并显示
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
