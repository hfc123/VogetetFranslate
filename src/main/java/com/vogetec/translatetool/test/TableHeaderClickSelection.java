package com.vogetec.translatetool.test;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class TableHeaderClickSelection extends JFrame {
    private JTable table;

    public TableHeaderClickSelection() {
        setTitle("Table Header Click Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // 创建表格模型和数据
        String[] columnNames = {"Name", "Age", "Gender"};
        Object[][] data = {
            {"John", 30, "Male"},
            {"Alice", 25, "Female"},
            {"Bob", 35, "Male"}
        };
        table = new JTable(data, columnNames);

        // 添加表头点击监听器
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                if (column != -1) {
                    // 切换选中状态
                    TableColumnModel columnModel = table.getColumnModel();
                    TableColumn tableColumn = columnModel.getColumn(column);
                    tableColumn.setCellRenderer(table.getTableHeader().getDefaultRenderer());
                    tableColumn.setHeaderRenderer(new SelectedHeaderRenderer(table.getTableHeader().getDefaultRenderer()));
                    tableColumn.setHeaderValue(tableColumn.getHeaderValue());
                    table.getTableHeader().repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        setVisible(true);
    }

    // 自定义表头渲染器，用于设置选中状态
    class SelectedHeaderRenderer implements TableCellRenderer {
        private TableCellRenderer defaultRenderer;

        public SelectedHeaderRenderer(TableCellRenderer defaultRenderer) {
            this.defaultRenderer = defaultRenderer;
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component component = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                component.setForeground(table.getSelectionForeground());
                component.setBackground(table.getSelectionBackground());
            } else {
                component.setForeground(table.getForeground());
                component.setBackground(table.getBackground());
            }
            return component;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TableHeaderClickSelection());
    }
}
