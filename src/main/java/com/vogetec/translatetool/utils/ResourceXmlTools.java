package com.vogetec.translatetool.utils;

import com.vogetec.translatetool.lang.LanguageMapBuilder;
import com.vogetec.translatetool.model.ValueBean;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SuppressWarnings("Duplicates")
public class ResourceXmlTools {
    File file;
    Document  document;
    protected Element rootElement;
    public void init (File rawFile) throws JDOMException, IOException {
        file = rawFile;
        try {
            document = getDocumentbyFile(file);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        rootElement = document.getRootElement();
    }

    public static Document getDocumentbyFile(File file) throws IOException, SAXException, JDOMException {
        //1、通过DOM解析器获取一个工厂实例
        SAXBuilder builder = new SAXBuilder();
//        String str =getTextByFile(file);
        String xmlContent = new String(Files.readAllBytes(Paths.get(file.getPath())));
        //2、通过工厂对象获取解析器对象;
        InputSource is = new InputSource(new StringReader(xmlContent));
        return builder.build(is);
    }

    @SuppressWarnings("Duplicates")
    public List<ValueBean> loadValueBeans(){
        String path = file.getPath();
        String foldPath = path.concat("/../..");
        File foldDir = new File(foldPath);
        Map map =new HashMap<String,ValueBean>();
        List<String> langs = new ArrayList<>();
        if (foldDir.isDirectory()){
            //递归
            File[] files = foldDir.listFiles();
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()&&files[i].getName().contains("values")){
                    File[] files2 = files[i].listFiles();
                    for (int j = 0; j < files2.length; j++) {
                        File file2 = files2[j];
                        if (file2.getName().contains("strings")){
                            loadValueBeansNames(langs,file2);
                        }
                    }
                }
            }
        }
        // System.out.println(langs.toString());

        if (foldDir.isDirectory()){
            //递归
            File[] files = foldDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()&&files[i].getName().contains("values")){
                    File[] files2 = files[i].listFiles();
                    for (int j = 0; j < files2.length; j++) {
                        File file2 = files2[j];
                        if (file2.getName().contains("strings")){
                            loadValueBeans(map,langs,file2);
                        }
                    }
                }
            }
        }



        // 将 Map 的值集合转换为 List
        List<ValueBean> list = (List<ValueBean>) map.values().stream().collect(Collectors.toList());

        // 输出转换后的 List
        // System.out.println(list);
        return list;
    }
    private void loadValueBeans(Map<String, ValueBean> stringValueBeanHashMap, List<String> langs, File file) {
        try {
            Document  document = getDocumentbyFile(file);
            Element  rootElement = document.getRootElement();
            List<Element> elements = rootElement.getChildren();
            String[] strings =file.getParentFile().getName().split("-");
            String langName ;
            if (strings.length>=2){
                if (strings[1]!=null&&!strings[1].equals(""))
                    langName = strings[1];
                else
                    langName = "default";
            }else {
                langName = "default";
            }
            for (int i = 0; i < elements.size(); i++) {
                String key = elements.get(i).getAttribute("name").getValue();
                ValueBean valueBean2 = stringValueBeanHashMap.get(key);
                ValueBean valueBean = valueBean2==null? new ValueBean():valueBean2;

                if (valueBean2 == null){
                    //new value not have key
                    valueBean.key = key;
                    Attribute unTransalteAble= elements.get(i).getAttribute("translatable");
                    valueBean.unTransalteAble = Boolean.valueOf(unTransalteAble==null?"false":unTransalteAble.getValue());
                    String path =file.getAbsolutePath();
                    int index = path.indexOf("res");
                    for (int j = 0; j < langs.size(); j++) {
                        valueBean.addLangValueN(new ValueBean.LangValue(langs.get(j),""));
                    }
                    valueBean.addLangValue(new ValueBean.LangValue(langName,elements.get(i).getValue()));
                    valueBean.resFolder =path.substring(index);
                    stringValueBeanHashMap.put(key,valueBean);
                }else {
                    //old value has key
                    if (langName.equals("default")){
                        valueBean.unTransalteAble = Boolean.valueOf(elements.get(i).getAttribute("translatable").getValue());
                    }
                    valueBean.addLangValue(new ValueBean.LangValue(langName,elements.get(i).getValue()));
                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadValueBeansNames(List<String> langs, File file2) {
        String[] strings =file2.getParentFile().getName().split("-");
        String langName ;
        if (strings.length>=2){
            if (strings[1]!=null&&!strings[1].equals(""))
                langName = strings[1];
            else
                langName = "default";
        }else {
            langName = "default";
        }
        if (!langs.contains(langName)&&
                (LanguageMapBuilder.getInstance().getLanguageMaps().containsKey(langName)||langName.equals("default")))
            langs.add(langName);
    }

    /**
     *
     * LangValue{langName='es', langValue=''}
     * @param path res\values\strings.xml\
     * @param lang  langName='es'
     * @return res\values-es\strings.xml
     */
    public static  File findFileByLang(String path,String lang){
        String foldPath = path.concat("/../..");
        String path2 = foldPath.concat("/values-"+lang);
        File foldDir = new File(foldPath);
        List<File> fileList = new ArrayList<>();
        if (foldDir.isDirectory()){
            //递归
            File[] files = foldDir.listFiles();
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()&&files[i].getName().contains("values")&&files[i].getName().contains("lang")){
                    File[] files2 = files[i].listFiles();
                    for (int j = 0; j < files2.length; j++) {
                        File file2 = files2[j];
                        if (file2.getName().contains("strings")){
                            fileList.add(file2);
                        }
                    }

                }
            }
        }
        if (fileList.size()>1){
            //多个string 文件返回第一个
            return fileList.get(0);
        }

        if (fileList.size()==1){
            return fileList.get(0);
        }
        //path2
        if (fileList.size()==0){
            File file = new File(path2);
            File file2 = new File(path2.concat("/strings.xml"));
            if (!file.exists()){
                file.mkdir();
                try {
                    if (!file2.exists())
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return file2;
            }else {
                try {
                    if (!file2.exists())
                        file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return  file2;
            }
        }
        return null;
    }
}
