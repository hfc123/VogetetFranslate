package com.vogetec.translatetool.test;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.vogetec.translatetool.http.Translator;
import com.vogetec.translatetool.ui.MyJTable;
import com.vogetec.translatetool.ui.SupportLanguagesDialog;
import com.vogetec.translatetool.ui.SupportLanguagesDialog2;
import com.vogetec.translatetool.utils.ResourceXmlControler;
import junit.framework.TestCase;
import org.jdom.JDOMException;
import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class XmlTest   extends LightCodeInsightFixtureTestCase {

    @Test
    public void testSave(){
        try {
            // 设置外观
//            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            // 更新组件外观
            SwingUtilities.invokeLater(() -> {
                MyJTable example = new MyJTable("F:\\java\\Workspaces\\VogetetFranslate\\res\\values\\strings.xml");
                // SwingUtilities.updateComponentTreeUI(example);
                example.setVisible(true);
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    //{"q":"hello","from":"en","to":"zh","termIds":"xx1,xx2"}
    @Test
    public void  testTranslate(){
// 获取系统编码格式
        try {
            Translator.getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Translator.textTrans("auto","zh","hello",null);

    }

    public void testDialog(){

        SwingUtilities.invokeLater(() -> {
            try {
                // 示例：显示对话框
                SupportLanguagesDialog dialog = new SupportLanguagesDialog();
                // 设置对话框的大小
                dialog.setSize(400, 300);

                dialog .showAndGet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public  void testDialog2(){
        MyDialog dialog = new MyDialog();
        dialog.show();
    /*    SwingUtilities.invokeLater(() -> {
            try {
                // 示例：显示对话框
                // 示例：显示对话框
                // 示例：显示对话框
                // 示例：显示对话框
                MyDialog dialog = new MyDialog();
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
    }
    public  void testDialog3(){

        SwingUtilities.invokeLater(() -> {
            // 在事件分发线程上执行的代码
            new SampleDialogWrapper().show();
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 在事件分发线程上执行的代码
            if (new SampleDialogWrapper().showAndGet()) {
                // user pressed OK
            }
        });
    }

    public  void testDialog4() {
        SupportLanguagesDialog2 dialog = new SupportLanguagesDialog2();
        dialog.setContentPane();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    public void testxxx(){
        String []s = "值0^值1^".split("\\^");
        System.out.println(s[0]);
    }
}
