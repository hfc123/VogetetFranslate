package com.vogetec.translatetool.ui;

import com.intellij.ui.components.JBLabel;
import com.vogetec.translatetool.lang.Lang;
import com.vogetec.translatetool.lang.Languages;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SupportLanguagesDialog2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public SupportLanguagesDialog2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        SupportLanguagesDialog2 dialog = new SupportLanguagesDialog2();
        dialog.setContentPane();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setContentPane(){
        contentPane.add(createCenterPanel(null));
    }

    protected @Nullable
    JComponent createCenterPanel(List<Lang> supportedLangList) {
        List<Lang> supportedLanguages = supportedLangList==null?Languages.getLanguages():supportedLangList;
        supportedLanguages.sort(new SupportLanguagesDialog.EnglishNameComparator());
        JPanel contentPanel = new JPanel(new GridLayout(supportedLanguages.size() / 4, 4, 10, 20));
        for (Lang supportedLanguage : supportedLanguages) {
            contentPanel.add(new JBLabel(supportedLanguage.getEnglishName()));
        }
        return contentPanel;
    }
}
