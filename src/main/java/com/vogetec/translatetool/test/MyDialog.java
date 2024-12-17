package com.vogetec.translatetool.test;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class MyDialog extends DialogWrapper {
    private JPanel mainPanel;

    protected MyDialog() {
        super(false); // true 表示对话框是模态的
        mainPanel =new JPanel();
        init();
        setTitle("My Custom Dialog");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        MyDialog dialog = new MyDialog();
        dialog.show();

    }
}
