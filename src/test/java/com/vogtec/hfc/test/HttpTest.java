package com.vogtec.hfc.test;

import com.vogetec.translatetool.ui.MyJTable;

import javax.swing.*;

public class HttpTest  {


    public static void main(String[] args) {
        try {
            // 设置外观
//            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            // 更新组件外观
            SwingUtilities.invokeLater(() -> {
                MyJTable example = new MyJTable("F:\\java\\Workspaces\\VogetetFranslate\\res\\values\\strings.xml");
                // SwingUtilities.updateComponentTreeUI(example);
                example.setVisible(true);

//                valueBeans.clear();
//                for (int i = 0; i < 10; i++) {
//                    ValueBean valueBean = new ValueBean();
//                    valueBean.key ="key"+i;
//                    valueBean.unTransalteAble = false;
//                    valueBean.resFolder ="res/value";
//                    valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
//                    valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
//                    valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
//                    valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
//                    valueBeans.add(valueBean);
//                }
//                example.reLoad(valueBeans);
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
