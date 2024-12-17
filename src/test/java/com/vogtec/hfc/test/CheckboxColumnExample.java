package com.vogtec.hfc.test;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckboxColumnExample extends JFrame {
    public CheckboxColumnExample() {
        setTitle("Checkbox Column Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);

        // 创建数据
        Object[][] data = {
                {"John", true},
                {"Jane", false},
                {"Tom", true}
        };

        // 创建列名
        String[] columns = {"Name", "Active"};

        // 创建默认的表格模型
        DefaultTableModel model = new DefaultTableModel(data, columns);

        // 创建 JTable
        JTable table = new JTable(model);

        // 将第二列设置为复选框列
        table.getColumnModel().getColumn(1).setCellRenderer(new CheckboxRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new CheckboxEditor());

        // 添加 JTable 到窗口
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // TableCellRenderer 用于在表格中渲染复选框
    private class CheckboxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckboxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER); // 居中显示复选框
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value); // 设置复选框状态
            return this;
        }
    }

    // TableCellEditor 用于编辑单元格中的复选框
    private class CheckboxEditor extends DefaultCellEditor {
        private JCheckBox checkBox;

        public CheckboxEditor() {
            super(new JCheckBox());
            checkBox = (JCheckBox) getComponent();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER); // 居中显示复选框
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopCellEditing(); // 编辑完成后停止编辑
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            checkBox.setSelected(value != null && (Boolean) value); // 设置复选框状态
            return checkBox;
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected(); // 返回复选框状态
        }
    }

    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(new FlatIntelliJLaf());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        SwingUtilities.invokeLater(() -> {
            CheckboxColumnExample example = new CheckboxColumnExample();
            example.setVisible(true);
        });
    }
}
