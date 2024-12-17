package com.vogtec.hfc.test;

import javax.swing.*;
import java.awt.*;

public class EmojiFlagViewer extends JFrame {

    public EmojiFlagViewer() {
        // 初始化窗口
        setTitle("Emoji Flag Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);

        // 创建标签并显示 Emoji 图标
        String countryCode = "US"; // 示例国家代码
        String emoji = localeToEmoji(countryCode);
        JLabel emojiLabel = new JLabel("\uD83D\uDE01\uD83D\uDE02\uD83D\uDE03\uD83D\uDE04\uD83D\uDE05");
        emojiLabel.setFont(new Font("Noto Color Emoji", Font.PLAIN, 36)); // 设置字体和大小
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText("<html><body>Here is an emoji: &#\uD83C\uDDE8\uD83C\uDDED;</body></html>");
        System.out.println("\uD83D\uDE4F\uD83D\uDE42");
        add(emojiLabel, BorderLayout.CENTER);
        add(textPane, BorderLayout.WEST);
    }

    private String localeToEmoji(String countryCode) {
        int firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmojiFlagViewer viewer = new EmojiFlagViewer();
            viewer.setVisible(true);
        });
    }

}
