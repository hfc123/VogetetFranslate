package com.vogtec.hfc.test;


import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JTableExample extends JFrame {
    private  JTable table;
    static List<ValueBean> valueBeans = new ArrayList<>();
    private DefaultTableModel model;

    public JTableExample() {
        setTitle("JTable Example");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model = new MyTableModel();

        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // 设置窗口大小为屏幕的一半
        setSize((int) (screenWidth * 0.618), (int) (screenHeight * 0.618));

        // 创建表格数据

        setLocationRelativeTo(null);

        for (int i = 0; i < 100; i++) {
            ValueBean valueBean = new ValueBean();
            valueBean.key ="key"+i;
            valueBean.unTransalteAble = false;
            valueBean.resFolder ="res/value";
            valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
            valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
            valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
            valueBean.addLangValue(new ValueBean.LangValue("key"+i,"value"+i));
            valueBeans.add(valueBean);
        }
        // 创建表头        String[] columnNames = getColumnNames(valueBeans.get(0).getOtherColum());
        String[] columnNames = getColumnNames(valueBeans.get(0).getOtherColum());
        System.out.println(Arrays.toString(columnNames));
        Object[][] data = createTable(valueBeans,columnNames.length);
       /**{
        {"1", "Item 1", "Description 1"},
        {"2", "Item 2", "Description 2"},
        {"3", "Item 3", "Description 3"},
        {"4", "Item 4", "Description 4"},
        {"5", "Item 5", "Description 5"}
        };*/

        model.setDataVector(data,columnNames);
        initTable();
        // 创建 JTable，并设置表格数据和表头

        // 将表格放置到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);

        // 将滚动面板添加到窗口中
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void initTable() {
        table = new JTable(model);
        // 更新组件外观
        SwingUtilities.updateComponentTreeUI(table);
        UIManager.put("Table.gridColor", new ColorUIResource(Color.RED));
        UIManager.put("Table.intercellSpacing", new Dimension(1, 1));
        // 设置单元格边框颜色
        table.setGridColor(Color.RED);
        // 设置单元格间距
        table.setIntercellSpacing(new Dimension(20, 1)); // 设置为 (1, 1) 或其他你希望的值

        // 创建单元格渲染器并设置边框颜色
        // 将第二列设置为复选框列
        table.getColumnModel().getColumn(2).setCellRenderer(new CheckboxRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new CheckboxEditor());
        // 设置单元格选择模式为单个选中
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 设置单元格选择模式为单个选中
        table.setCellSelectionEnabled(true);
    }

    public void reLoad(List<ValueBean> valueBeans2){

        String[] columnNames = getColumnNames(valueBeans.get(0).getOtherColum());
        Object[][] data = createTable(valueBeans,columnNames.length);
        model.setRowCount(0);
        model.setDataVector(data,columnNames);
        // 将第二列设置为复选框列
        table.getColumnModel().getColumn(2).setCellRenderer(new CheckboxRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new CheckboxEditor());
        // 刷新表格显示
        table.repaint();

    }
    private Object[][] createTable(List<ValueBean> valueBeans, int length) {
        Object[][] objects =  new Object[valueBeans.size()][length];
        for (int i = 0; i < valueBeans.size(); i++) {

                    objects[i][0] = valueBeans.get(i).key;

                    objects[i][1] = valueBeans.get(i).resFolder;

                    objects[i][2] = valueBeans.get(i).unTransalteAble;

                    for (int j = 0; j < valueBeans.get(i).langValues.size(); j++) {
                        objects[i][3+j]=valueBeans.get(i).langValues.get(j).langValue;
                    }

        }
        return objects;
    }

    public String[] getColumnNames(String[] columnNames){
        String[] c = {"Key", "Resource Folder", "Default Value"};
        String[] c2 = new String[c.length+columnNames.length];

        for (int i = 0; i < c.length; i++) {
            c2[i]=c[i];
        }
        for (int i = 0; i < columnNames.length; i++) {
            c2[c.length+i]=columnNames[i];
        }
        return c2;

    }
    public static void main(String[] args) {
        try {

            // 设置外观
         //   UIManager.setLookAndFeel(new FlatIntelliJLaf());
            // 更新组件外观

            SwingUtilities.invokeLater(() -> {
                JTableExample example = new JTableExample();
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
    // TableCellRenderer 用于在表格中渲染复选框
    private class CheckboxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckboxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER); // 居中显示复选框
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value); // 设置复选框状态
            System.out.println("getcheckbox");
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

}
