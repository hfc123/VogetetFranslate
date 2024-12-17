package com.vogetec.translatetool.presenter;

import com.google.gson.Gson;
import com.vogetec.translatetool.http.Translator;
import com.vogetec.translatetool.lang.LanguageMapBuilder;
import com.vogetec.translatetool.model.JTableModel;
import com.vogetec.translatetool.model.TranslationResult;
import com.vogetec.translatetool.model.ValueBean;
import com.vogetec.translatetool.tools.CustomThreadPool;
import com.vogetec.translatetool.tools.ExcelWriter;
import com.vogetec.translatetool.ui.MyJTable;
import com.vogetec.translatetool.ui.MyTableModel;
import com.vogetec.translatetool.utils.ResourceXmlTools;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JTablePresenter  extends  BasePresenter{
    private int screenWidth;
    private int screenHeight;
    private DefaultTableModel model;
    private JTableModel jTableModel;
    private MyJTable jTable;
    private String fromLang = "auto";;
    private String toLang;
    private String excelLang;
    private String absolutePath;
    public static  String TAG = "~\\(~";
    public JTablePresenter(MyJTable table,String path) {
        this.jTable = table;
        jTableModel = new JTableModel(path);
        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth  = screenSize.width;
        screenHeight = screenSize.height;
        model = new MyTableModel();
//        model.setDataVector(data,columnNames);
        model.setDataVector(jTableModel.getData(),jTableModel.getColumnNames());
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
              //  System.out.println("row"+row+"row"+column);
                if (row==-1||column==-1){
                    return;
                }
                try {
                    Object value =  table.table.getValueAt(row, column);
                    jTableModel.getData()[row][column] =value;
                    if (column == 0)
                        jTableModel.getValueBeans().get(row).key= (String) value;
                    else if (column == 1)
                        jTableModel.getValueBeans().get(row).resFolder = (String) value;
                    else if (column == 2)
                        jTableModel.getValueBeans().get(row).unTransalteAble = (boolean) value;
                    else if (column == 3)
                        jTableModel.getValueBeans().get(row).needtranslate = (boolean) value;
                    else {
                        jTableModel.getValueBeans().get(row).langValues.get(column-4).setLangValue((String) value);
                        ValueBean valueBean =   jTableModel.getValueBeans().get(row);
                        ValueBean.LangValue langValue = jTableModel.getValueBeans().get(row).langValues.get(column-4);

                        String rootPath = valueBean.resFolder;
                        File file = ResourceXmlTools.findFileByLang(rootPath,langValue.getLangName());
                        System.out.println("jtablevalue"+","+file.getPath().toString());
                        Document document = ResourceXmlTools.getDocumentbyFile(file);
                        Element element = document.getRootElement();
                        Element element2 = new Element("string");
                        element2.setAttribute("name",valueBean.key);
                        element2.addContent((String) value);
                        element.addContent(element2);

                        System.out.println("jtablevalue"+","+valueBean.toString());
                    }

                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        });
    }

    public int getScreenWidth() {
        return (int) (screenWidth * 0.618);
    }

    public int getScreenHeight() {
        return  (int) (screenHeight * 0.618);
    }

    public TableModel getModel() {
        return model;
    }

    public void upDateModel() {
        //getColumnNames(valueBeans.get(0).getOtherColum());
        String[] columnNames = (String[]) jTableModel.getColumnNames();
        //createTable(valueBeans,columnNames.length);
        Object[][] data = jTableModel.createTable();
        model.setRowCount(0);
        model.setDataVector(data,columnNames);
    }
    private void translateList(List<ValueBean> list){
        if (fromLang == null || toLang == null){
            throw  new IllegalArgumentException("fromLang  toLang must not be null");
        }
        if (fromLang .equals(toLang)){
            throw  new IllegalArgumentException("fromLang  toLang must not be same");
        }

        // System.out.println(list.toString());


        if (fromLang!=null&&toLang!=null) {
            CustomThreadPool.getInstance(5).executeTask(new Runnable() {
                @Override
                public void run() {
                    jTable.showLoading();
                    try {
                        translateListValue(list);
                    }catch (Exception e){
                        e.printStackTrace();
                        jTable.dissMissLoading();
                        return;
                    }
                    jTable.reLoad();
                    jTable.showLoadingComplete();
                }
            });

        }

        //translator.textTrans();
    }
    public void tranlateSelected() {
        List<ValueBean> list = jTableModel.getSelectedData();
        translateList(list);
    }
    public void tranlateAll() {
        List<ValueBean> list = jTableModel.getValueBeans();
        translateList(list);
    }


    // translate 20 per times
    @SuppressWarnings("Duplicates")
    private void  translateListValue(List<ValueBean> list){
        System.out.println("list.size"+list.size());
        if (list==null||list.size()==0){
            return;
        }
        if (list.size()>20){
            List<ValueBean> list1 = null;
            for (int i = 0; i < list.size(); i++) {
             //   System.out.println("counts"+i);
                if ((i)%20==0){
                   translateListValue(list1);
                   list1 = new ArrayList();
                }
                list1.add(list.get(i));
            }
            translateListValue(list1);
            return;
        }
        StringBuilder stringBuilder = null;
        Translator translator = null;
        try {
            translator = new Translator();

            stringBuilder = createQueryValue(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("list.size"+list.size());
        if (list.size() ==0){
            jTable.showLoadingFail("invalidate lang value");
            throw  new InvalidParameterException("invalidate lang value");
        }
        if (LanguageMapBuilder.getInstance().getLanguageMaps().get(toLang)==null){
            //子线程抛出的异常不会打印
            new InvalidParameterException("not found supported lang").printStackTrace();
            throw  new InvalidParameterException("not found supported lang");
        }

        System.out.println("fromLang"+fromLang);
        String fromLang2 =fromLang.equals("auto")?fromLang:LanguageMapBuilder.getInstance().getLanguageMaps().get(fromLang).getCode();
        String json = translator.textTrans(fromLang2,LanguageMapBuilder.getInstance().getLanguageMaps()
                .get(toLang).getCode() , stringBuilder.toString(), null);
        reloadData(json,list);

    }
    //single translate
    @SuppressWarnings("Duplicates")
    private void  translateListValueWithSpecialValue(@NotNull List<ValueBean> list){
        if (list.size()!=1){
            throw new InvalidParameterException("SpecialValue must be single list to handler");
        }
        Translator translator = new Translator();
        String stringBuilder = list.get(0).langValues.get(0).getLangValue();
        if (LanguageMapBuilder.getInstance().getLanguageMaps().get(toLang)==null){
            //子线程抛出的异常不会打印
            new InvalidParameterException("not found supported lang").printStackTrace();
            throw  new InvalidParameterException("not found supported lang");
        }
        System.out.println("fromLang"+fromLang);
        String fromLang2 =fromLang.equals("auto")?fromLang:LanguageMapBuilder.getInstance().getLanguageMaps().get(fromLang).getCode();
        String json = translator.textTrans(fromLang2,LanguageMapBuilder.getInstance().getLanguageMaps().get(toLang).getCode() , stringBuilder.toString(), null);
        reloadDataWithSpecialValue(json,list);
        //jTable.showLoadingComplete();
    }
    private StringBuilder createQueryValue( List<ValueBean> list) {
        StringBuilder stringBuilder = new StringBuilder();
        List<ValueBean> removelist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).langValues.size(); j++) {
                String  langeName =  list.get(i).langValues.get(j).getLangName().equals("default")?"auto":list.get(i).langValues.get(j).getLangName();
                String  langeValue =  list.get(i).langValues.get(j).getLangValue();

              //  System.out.println("----------"+fromLang+langeName+","+langeValue+toLang+"----------");
                if (fromLang.equals(langeName)){
                    if (langeValue==null||langeValue.trim().equals("")){
              //  System.out.println("langeValueNull"+langeValue);
                        removelist.add(list.get(i));
                        continue;
                    }
                    if (langeValue.contains(TAG)){
                       // System.out.println("langeValueTAG"+langeValue);
                        removelist.add(list.get(i));
                        List<ValueBean>list1 = new ArrayList<>();
                        list1.add(list.get(i));
                        translateListValueWithSpecialValue(list1);
                        continue;
                    }
                    stringBuilder.append(langeValue).append(TAG);
                  //  System.out.println(stringBuilder.toString());
                }
            }
        }
        for (int i = 0; i < removelist.size(); i++) {
            list.remove(removelist.get(i));
        }

        return stringBuilder;
    }
    @SuppressWarnings("Duplicates")
    public void reloadData(String json, List<ValueBean> list){
       // System.out.println(json);
        Gson gson = new Gson();
        TranslationResult translationResult = gson.fromJson(json, TranslationResult.class);
        String [] dsts = translationResult.getTranslateResultDst().split(TAG);
        String [] srcs = translationResult.getTranslateResultSrc().split(TAG);
      //  System.out.println("dsts[i]"+list.size()+"------"+dsts.length);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).langValues.size(); j++) {
                String  langeName =  list.get(i).langValues.get(j).getLangName()
                        .equals("default")?"auto":list.get(i).langValues.get(j).getLangName();
                if (toLang.equals(langeName)){
                //    System.out.println("dsts[i]"+list.get(i).langValues.get(0).getLangValue()+"-----"+dsts[i]);
                    list.get(i).langValues.get(j).setLangValue(dsts[i]);
                }
            }
        }
        List<ValueBean> valueBeans = jTableModel.getValueBeans();
        for (int i = 0; i < valueBeans.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (valueBeans.get(i).key.equals(list.get(j).key)){
                    ValueBean newObj  =list.get(j);
                    valueBeans.set(i,newObj);
                }
            }
        }

    }
    @SuppressWarnings("Duplicates")
    public void reloadDataWithSpecialValue(String json, List<ValueBean> list){
       // System.out.println(json);
        Gson gson = new Gson();
        TranslationResult translationResult = gson.fromJson(json, TranslationResult.class);
        String  dsts = translationResult.getTranslateResultDst();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).langValues.size(); j++) {
                String  langeName =  list.get(i).langValues.get(j).getLangName().equals("default")?"auto":list.get(i).langValues.get(j).getLangName();
                if (toLang.equals(langeName)){
                    list.get(i).langValues.get(j).setLangValue(dsts);
                }
            }
        }
        List<ValueBean> valueBeans = jTableModel.getValueBeans();
        for (int i = 0; i < valueBeans.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (valueBeans.get(i).key.equals(list.get(j).key)){
                    ValueBean newObj  =list.get(j);
                    valueBeans.set(i,newObj);
                }
            }
        }
    }

    public String[] getLanges() {
        // 创建一个预定义选项的数组
        String[] columnNames = (String[]) jTableModel.getColumnNames();
        String[] options = new String[columnNames.length-4];
        for (int i = 4; i < columnNames.length; i++) {
            options[i-4]= columnNames[i];
        }

        return options;
    }

    public void setFromLang(String selectedOption) {

        this.fromLang = selectedOption;
        if (selectedOption.equals("default")){
            fromLang = "auto";
        }
    }

    public void setToLang(String selectedOption) {
        if (selectedOption.equals("default")){
            throw new IllegalArgumentException("Invalid option selected: default");
        }
        this.toLang = selectedOption;
    }
    //选择翻译语言
    public void setSelectExcelLang(String selectedOption) {
        this.excelLang = selectedOption;
    }

    @SuppressWarnings("Duplicates")
    public void exportAllToExcel() {
        if (absolutePath==null){
            throw new InvalidParameterException("not selected export path");
        }
        Object[][] src = jTableModel.getData();
        Object[] columns = jTableModel.getColumnNames();
        if (src.length==0||src[0].length-3==0){
            throw new IllegalArgumentException("empty src data");
        }
        int len1 = src[0].length-3;
        int len2 = src.length;
        Object[][] dest = new Object[len2+1][len1];
        for (int i = 0; i < dest[0].length; i++) {
            dest[0][i]=columns[i==0?i:i+3];
        }
        for (int i = 1; i < len2; i++) {
            for (int j = 0; j < len1; j++) {
                dest[i][j]=src[i-1][j==0?j:j+3];
            }
        }
        CustomThreadPool.getInstance(5).executeTask(new Runnable() {
            @Override
            public void run() {
                try {
                    jTable.showLoading();
                    ExcelWriter.writeExcel(dest,absolutePath.concat("/").concat("languages.xlsx"));
                    jTable.showLoadingComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @SuppressWarnings("Duplicates")
    public void exportSelectToExcel() {
        if (absolutePath==null){
            throw new InvalidParameterException("not selected export path");
        }
        if (excelLang==null){
            throw new InvalidParameterException("not selected export Lang");
        }
        Object[][] src = jTableModel.getData();
        Object[] columns = jTableModel.getColumnNames();
        if (src.length==0||src[0].length-3==0){
            throw new IllegalArgumentException("empty src data");
        }
        int len2 = src.length;
        int len1;
        if (excelLang.equals("default")){
            len1 = 2;
        }else {
            len1 = 3;
        }

        int index = Arrays.binarySearch(columns, excelLang);
        Object[][] dest = new Object[len2+1][len1];
        for (int i = 0; i < dest[0].length; i++) {
            dest[0][i]=columns[i==0?i:i+3];
            if (i==2){
                dest[0][i]=columns[i==0?i:index];
            }
        }

        for (int i = 1; i < len2; i++) {
            for (int j = 0; j < len1; j++) {
                if (j==0)
                dest[i][j]=src[i-1][j];
                else {
                    if ("default".equals(excelLang)){
                        dest[i][j]=src[i-1][4];
                    }else if (j==1){
                        dest[i][j]=src[i-1][4];
                    }else {
                        dest[i][j]=src[i-1][index];
                    }
                }
            }
        }
        CustomThreadPool.getInstance(5).executeTask(new Runnable() {
            @Override
            public void run() {
                try {
                    jTable.showLoading();
                    ExcelWriter.writeExcel(dest,absolutePath.concat("/").concat("languages.xlsx"));
                    jTable.showLoadingComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setExportPath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String[] getTranslationTools() {

        //, "Google", "ChatGpt"
        return new String[]{"Baidu"};
    }
}
