package com.vogetec.translatetool.test;

import com.intellij.openapi.ui.DialogWrapper;
import javax.swing.*;

public class MyDialogWrapper extends DialogWrapper {
    protected MyDialogWrapper() {
        super(true); // true 表示对话框是模态的
        init();
        setTitle("My Dialog");
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Hello, Dialog!"));
        return panel;
    }
}
