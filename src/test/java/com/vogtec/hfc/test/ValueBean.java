package com.vogtec.hfc.test;


import java.util.ArrayList;
import java.util.List;

public class ValueBean {
    String key;
    String resFolder;
    boolean unTransalteAble;
    List<LangValue> langValues = new ArrayList<>();
    static class LangValue{
        String langName;
        String langValue;

        public LangValue(String langName, String langValue) {
            this.langName = langName;
            this.langValue = langValue;
        }
    }
    public void addLangValue(LangValue langValue){
        langValues.add(langValue);

    }
    public String[] getOtherColum(){
        String[] otherColum=new String[langValues.size()];
        for (int i = 0; i < langValues.size(); i++) {
            otherColum[i]= langValues.get(i).langName;
        }
        return otherColum;
    }

}
