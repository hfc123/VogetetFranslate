package com.vogtec.hfc.test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FlagViewer extends JFrame {

    private Map<String, ImageIcon> flagMap;

    public FlagViewer() {
        // 初始化窗口
        setTitle("Flag Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);

        // 初始化国旗映射
        initializeFlagMap();

        // 创建标签并显示国旗
        String countryCode = "US"; // 示例国家代码
        ImageIcon flagIcon = flagMap.get(countryCode);
        JLabel flagLabel = new JLabel(flagIcon);
        add(flagLabel, BorderLayout.CENTER);
    }

    private void initializeFlagMap() {
        flagMap = new HashMap<>();
        // 在这里添加国家代码与对应国旗图片的映射关系
        flagMap.put("US", new ImageIcon("path/to/usa_flag.png"));
        flagMap.put("CN", new ImageIcon("path/to/china_flag.png"));
        // 添加更多国家代码和对应的国旗图片
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlagViewer viewer = new FlagViewer();
            viewer.setVisible(true);
        });
    }
}