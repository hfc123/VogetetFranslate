package com.vogtec.hfc.test;

import junit.framework.TestCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableExample extends TestCase {

    public  void test1(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("表格示例");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTable table = new JTable(new CustomTableModel());
            table.getColumnModel().getColumn(1).setCellRenderer(new CustomCheckBoxRenderer());
            table.getColumnModel().getColumn(0).setCellRenderer(new CustomTextFieldRenderer());
            table.getColumnModel().getColumn(2).setCellEditor(new CustomTextFieldEditor());
            // table.getColumnModel().getColumn(0).set
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            //  2
            Object[] rowData = {"标签内容", false, "输入框内容"};
            model.addRow(rowData);
            model.addRow(rowData);
            model.addRow(rowData);
            model.addRow(rowData);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static class CustomTableModel extends DefaultTableModel {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 1 ? Boolean.class : Object.class;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int column) {
             switch (column) {
                 case 0 :
                     return "标签";
                 case 1 :
                     return "选项框";
                 case 2 :
                     return "输入框";
                 default :
                     return "";
            }
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column==2;
        }
    }

    static class CustomCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CustomCheckBoxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value);
            return this;
        }
    }

    static class CustomTextFieldRenderer extends JTextField implements TableCellRenderer {
        public CustomTextFieldRenderer() {
            setBorder(null);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            return this;
        }
    }

    static class CustomTextFieldEditor extends AbstractCellEditor implements TableCellEditor {
        private JTextField textField;

        public CustomTextFieldEditor() {
            textField = new JTextField();
            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopCellEditing();
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return textField.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            textField.setText(value != null ? value.toString() : "");
            return textField;
        }
    }
}
