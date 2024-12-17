package com.vogetec.translatetool.model;


import java.util.ArrayList;
import java.util.List;

public class ValueBean {
    public String key;
    public String resFolder;
    public boolean unTransalteAble;
    public boolean needtranslate;
    public List<LangValue> langValues = new ArrayList<>();
    public static class LangValue{
        String langName;
        String langValue;

        public LangValue(String langName, String langValue) {
            this.langName = langName;
            this.langValue = langValue;
        }

        public String getLangName() {
            return langName;
        }

        public void setLangName(String langName) {
            this.langName = langName;
        }

        public String getLangValue() {
            return langValue;
        }

        public void setLangValue(String langValue) {
            this.langValue = langValue;
        }

        @Override
        public String toString() {
            return "LangValue{" +
                    "langName='" + langName + '\'' +
                    ", langValue='" + langValue + '\'' +
                    '}';
        }
    }
    public void addLangValueN(LangValue langValue){
        langValues.add(langValue);
    }
    public void addLangValue(LangValue langValue){
        for (int i = 0; i < langValues.size(); i++) {
            if (langValues.get(i).getLangName().equals(langValue.getLangName())){
                langValues.get(i).setLangValue(langValue.getLangValue());
            }
        }
    }
    public void initLanges(List<String> langs){
        if (langValues.size()==0){

        }
    }
    public String[] getOtherColum(){
        String[] otherColum=new String[langValues.size()];
        for (int i = 0; i < langValues.size(); i++) {
            otherColum[i]= langValues.get(i).langName;
        }
        return otherColum;
    }

    @Override
    public String toString() {
        return "ValueBean{" +
                "key='" + key + '\'' +
                ", resFolder='" + resFolder + '\'' +
                ", unTransalteAble=" + unTransalteAble +
                ", needtranslate=" + needtranslate +
                ", langValues=" + langValues +
                '}';
    }
}
