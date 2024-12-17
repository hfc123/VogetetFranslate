package com.vogetec.translatetool.utils;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.vogetec.translatetool.lang.LanguageMapBuilder;
import com.vogetec.translatetool.model.ValueBean;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceXmlControler extends XmlSaveAbstract {
    Map<String,String> rawmap = new HashMap<>();
    String prefix="string";

    public void init(PsiFile psiFile, int type, Project mProject, String prefix) throws JDOMException, IOException {
        super.init(psiFile, type, mProject);
        List<Element> elements = rootElement.getChildren();
        for (int i = 0; i < elements.size(); i++) {
            rawmap.put(elements.get(i).getAttribute("name").getValue(),elements.get(i).getValue());
        }
        this.psiFile=psiFile;
        this.prefix =prefix;
    }

    @Override
    Map<String, String> getDimensMap() {
        return null;
    }

    @Override
    Map<String, String> getStringMap() {
        return null;
    }

    @Override
    Map<String, String> getColorMap() {
        return null;
    }
    //获取color文件，string 文件，dimen文件的值
    @Override
    public Map<String , String> getMap() {
        return rawmap;
    }


    @Override
    public void saveMaps(Map<String, String> map) {
        for (String key : map.keySet()) {
            Element element1 =  new Element(prefix);
            element1.setAttribute("name",key);
            element1.setText( map.get(key));
            rootElement.addContent(element1);
        }
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
    @SuppressWarnings("Duplicates")
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
    @SuppressWarnings("Duplicates")
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


}
