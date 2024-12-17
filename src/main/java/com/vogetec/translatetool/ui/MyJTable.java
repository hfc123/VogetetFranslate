package com.vogetec.translatetool.ui;

import com.vogetec.translatetool.presenter.JTablePresenter;
import com.vogetec.translatetool.test.LoadingDialog;
import com.vogetec.translatetool.test.TableHeaderClickSelection;
import com.vogetec.translatetool.utils.MyPluginSettings;
import gherkin.lexer.Tr;
import org.codehaus.groovy.runtime.StringGroovyMethods;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyJTable extends JFrame {
    public JTable table;
    private JTablePresenter jTablePresenter;
    private JPanel northPanel;

    public MyJTable(String path) {
        setTitle("JTable Example");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table=new JTable();
        jTablePresenter = new JTablePresenter(this,path);
        setSize(jTablePresenter.getScreenWidth(),jTablePresenter.getScreenHeight());
        setLocationRelativeTo(null);
        // 创建 JTable，并设置表格数据和表头
        initTable();
        initMenu();
        // 将表格放置到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // 将滚动面板添加到窗口中
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        // 创建北部面板
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel,BoxLayout.Y_AXIS));
        getContentPane().add(northPanel, BorderLayout.NORTH);
        addButtonPanel();
        addExcelButtonPanel();

    }

    private void addExcelButtonPanel() {
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton exportButton = new JButton("export selected");
        jPanel1.add(exportButton);
        JButton exportALLButton = new JButton("export All");
        JLabel jLabel = new JLabel("Export Lang");
        String[] options = jTablePresenter.getLanges();
        JComboBox<String> jComboBox = new JComboBox<>(options);
        jComboBox.addActionListener(e ->   {
            String selectedOption = (String) jComboBox.getSelectedItem();
            System.out.println("fromLang Selected option: " + selectedOption);
            jTablePresenter.setSelectExcelLang(selectedOption);}
        );
        exportALLButton.addActionListener(e -> {

            try {
                jTablePresenter.exportAllToExcel();
            } catch (Exception e1) {
                e1.printStackTrace();
                showErrorMessage(e1);
            }
        });
        exportButton.addActionListener(e -> {
            try {
                jTablePresenter.exportSelectToExcel();
            } catch (Exception e1) {
                e1.printStackTrace();
                showErrorMessage(e1);
            }
        });
        jPanel1.add(exportALLButton);
        jPanel1.add(jLabel);
        jPanel1.add(jComboBox);
        JLabel selectedPath = new JLabel("Exported Path");
        // 创建按钮
        JButton chooseButton = new JButton("Choose Path");

        // 添加按钮点击事件处理程序
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个文件选择器
                JFileChooser fileChooser = new JFileChooser();

                // 设置文件选择器为选择目录模式
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // 显示文件选择器并等待用户选择
                int result = fileChooser.showOpenDialog(MyJTable.this);

                // 处理用户选择结果
                if (result == JFileChooser.APPROVE_OPTION) {
                    // 获取用户选择的文件或目录
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedPath.setText(selectedFile.getAbsolutePath());
                    jTablePresenter.setExportPath(selectedFile.getAbsolutePath());
                    // 打印用户选择的路径
                    System.out.println("Selected path: " + selectedFile.getAbsolutePath());
                }
            }
        });
        jPanel1.add(chooseButton);

        jPanel1.add(selectedPath);
        northPanel.add(jPanel1);
    }

    private void addButtonPanel() {
        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // 创建按钮并添加到面板
        JButton bt1 = new JButton("Tranlate Selected");
        JButton bt2 = new JButton("Tranlate All");
        bt1.addActionListener(actionEvent-> jTablePresenter.tranlateSelected());
        bt2.addActionListener(actionEvent-> jTablePresenter.tranlateAll());
        bt2.addActionListener(null);
        buttonPanel.add(bt1);
        buttonPanel.add(bt2);
        JLabel jLabel1 = new JLabel("From Lang");
        JLabel jLabel2 = new JLabel("To Lang");
        // 创建一个预定义选项的数组
        String[] options = jTablePresenter.getLanges();
        // 创建一个下拉列表，并将预定义选项数组传递给它
        JComboBox<String> fromLang = new JComboBox<>(options);
        JComboBox<String> toLang = new JComboBox<>(options);

          // 添加选中事件监听器
        fromLang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户选择的选项
                String selectedOption = (String) fromLang.getSelectedItem();
                System.out.println("fromLang Selected option: " + selectedOption);
                jTablePresenter.setFromLang(selectedOption);
            }
        });
        toLang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户选择的选项
                String selectedOption = (String) toLang.getSelectedItem();
                System.out.println("toLang Selected option: " + selectedOption);
                try {
                    jTablePresenter.setToLang(selectedOption);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    showErrorMessage(e1);
                }
            }
        });
        buttonPanel.add(jLabel1);
        buttonPanel.add(fromLang);
        buttonPanel.add(jLabel2);
        buttonPanel.add(toLang);
        // 将按钮面板添加到窗口中的 NORTH
        northPanel.add(buttonPanel);
    }

    private void initTable() {
        table.setModel(jTablePresenter.getModel());
        // 更新组件外观
        SwingUtilities.updateComponentTreeUI(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        risizeTable();
        UIManager.put("Table.gridColor", new ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("Table.intercellSpacing", new Dimension(1, 1));
        // 设置单元格边框颜色
        table.setGridColor(Color.RED);
        // 设置单元格间距
        table.setIntercellSpacing(new Dimension(20, 1)); // 设置为 (1, 1) 或其他你希望的值

        // 创建单元格渲染器并设置边框颜色
        // 将第二列设置为复选框列
        setCheckBox();
        // 设置单元格选择模式为单个选中
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 设置单元格选择模式为单个选中
        table.setCellSelectionEnabled(true);

        // 添加表头点击监听器
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                if (column == 3) { // If the clicked column is the checkbox column
                    selectAllNeedTranslate();
                }
                if (column == 2) { // If the clicked column is the checkbox column
                    selectAllunTranslateAble();
                }
            }
        });
    }

    private void risizeTable() {
        // 设置每列的宽度
        TableColumn column1 = table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(150); // 设置第一列的宽度为 100 像素
        TableColumn column2 = table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(150); // 设置第二列的宽度为 150 像素
        TableColumn column3 = table.getColumnModel().getColumn(2);
        column3.setPreferredWidth(130); // 设置第三列的宽度为 130 像素
        TableColumn column4 = table.getColumnModel().getColumn(3);
        column4.setPreferredWidth(120); // 设置第四列的宽度为 120 像素
        for (int i = 4; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column5 = table.getColumnModel().getColumn(i);
            column5.setPreferredWidth(120);
        }
    }

    boolean selectAllNeedTranslate = true;
    boolean selectAllunTranslateAble = true ;
    public void selectAllNeedTranslate(){
        int column = 3;
        if (selectAllNeedTranslate){
            selectAllNeedTranslate = false;
            selectColumAt(column);
        }else {
            selectAllNeedTranslate = true;
            unSelectColumAt(column);
        }

    }
    public void selectAllunTranslateAble(){
        int column = 2;
        if (selectAllunTranslateAble){
            selectAllunTranslateAble = false;
            selectColumAt(column);
        }else {
            selectAllunTranslateAble = true;
            unSelectColumAt(column);
        }
    }
    public void selectColumAt(int index){
        int rowCount = table.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            table.setValueAt(true, i, index); // Toggle checkbox state
        }
    }
    public void unSelectColumAt(int index){
        int rowCount = table.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            table.setValueAt(false, i, index); // Toggle checkbox state
        }
    }
    public void reLoad(){
        jTablePresenter.upDateModel();
        // 将第二列设置为复选框列
        setCheckBox();
        // 刷新表格显示
        table.repaint();
    }
    private LoadingDialog loadingDialog;
    public void showLoading(){
        loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoading();
    }
    public void dissMissLoading(){
        if (loadingDialog!=null)
            loadingDialog.dispose();
    }
    public void showLoadingFail(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
    public void showLoadingComplete(){
        if (loadingDialog!=null)
        loadingDialog.dispose();
        JOptionPane.showMessageDialog(this, " Do Task successful!");
    }
    private void setCheckBox(){
        table.getColumnModel().getColumn(2).setCellRenderer(new CheckboxRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new CheckboxEditor());
        table.getColumnModel().getColumn(3).setCellRenderer(new CheckboxRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new CheckboxEditor());
    }

    public static void main(String[] args) {
            // 设置外观
//            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            // 更新组件外观

                MyJTable example = new MyJTable("F:\\java\\Workspaces\\VogetetFranslate\\res\\values\\strings.xml");
               // SwingUtilities.updateComponentTreeUI(example);
                example.setVisible(true);
    }
    // TableCellRenderer 用于在表格中渲染复选框
    private class CheckboxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckboxRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER); // 居中显示复选框
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value); // 设置复选框状态
           // if (value != null && (Boolean) value)
           // System.out.println("getcheckbox"+row+","+column+","+value);
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
                  //  stopCellEditing(); // 编辑完成后停止编辑
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
    private void addTranslationTools(){
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(jPanel1);
        String[] options = jTablePresenter.getTranslationTools();
        JComboBox<String> jBoxs = new JComboBox<>(options);
        jBoxs.addActionListener(
                e -> {
                    String selectItem  = ((String) jBoxs.getSelectedItem());
                }
        );
        JButton jButton = new JButton("Settings");
        jButton.addActionListener(e -> {
            showLangSetting();
        });
    }
    private void initMenu(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("setting");
        JMenuItem item = new JMenuItem("baidu setting");
        item.addActionListener(e -> {
            showLangSetting();
        });
        jMenu.add(item);
        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);
    }
    @SuppressWarnings("Duplicates")
    private void showLangSetting() {
        {
            // 创建一个新的 JDialog
            JDialog dialog = new JDialog(this, "输入框", true);
            dialog.setLayout(new GridLayout(3, 2));

            // 创建标签和文本框
            JLabel idLabel = new JLabel("ID:", SwingConstants.CENTER);

            JTextField idField = new JTextField();
            JLabel secretLabel = new JLabel("Secret:", SwingConstants.CENTER);

            JTextField secretField = new JTextField();
            secretField.setText(MyPluginSettings.getSecret()==null?"":MyPluginSettings.getSecret());
            idField.setText(MyPluginSettings.getId()==null?"":MyPluginSettings.getId());
            // 创建确定和取消按钮
            JButton okButton = new JButton("确定");
            JButton cancelButton = new JButton("取消");

            // 添加确定按钮的事件监听器
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id = idField.getText();
                    String secret = secretField.getText();
                    System.out.println("ID: " + id);
                    System.out.println("Secret: " + secret);
                    // 这里可以添加其他逻辑，比如保存输入值，关闭窗口等
                    MyPluginSettings.saveSettings(id,secret);
                    dialog.dispose(); // 关闭对话框
                }
            });

            // 添加取消按钮的事件监听器
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 关闭对话框
                    dialog.dispose();
                }
            });

            // 将组件添加到对话框中
            dialog.add(idLabel);
            dialog.add(idField);
            dialog.add(secretLabel);
            dialog.add(secretField);
            dialog.add(okButton);
            dialog.add(cancelButton);

            // 设置对话框大小并显示
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        }
    }

    public void showErrorMessage(Exception e){
        JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
