package com.vogtec.hfc.test;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableCellGridHeightExample extends JFrame {
    public TableCellGridHeightExample() {
        setTitle("TableCell Grid Height Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);

//        try {
//            // 设置 FlatLaf 主题
//         //   UIManager.setLookAndFeel(new FlatIntelliJLaf());
//            // 更新组件外观
//            SwingUtilities.updateComponentTreeUI(this);
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }

        // 创建数据
        Object[][] data = {
                {"John", "Doe"},
                {"Jane", "Smith"},
                {"Tom", "Brown"}
        };

        // 创建列名
        String[] columns = {"First Name", "Last Name"};

        // 创建默认的表格模型
        DefaultTableModel model = new DefaultTableModel(data, columns);

        // 创建 JTable
        JTable table = new JTable(model);
        UIManager.put("Table.gridColor", Color.LIGHT_GRAY); // 可根据需要更改颜色
        // 修改 UIManager 中的 "Table.rowHeight" 属性，设置分割线高度
        UIManager.put("Table.rowHeight", 50); // 可根据需要更改高度

        // 创建 JScrollPane 并添加 JTable
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加 JScrollPane 到窗口
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TableCellGridHeightExample example = new TableCellGridHeightExample();
            example.setVisible(true);
        });
    }
}
