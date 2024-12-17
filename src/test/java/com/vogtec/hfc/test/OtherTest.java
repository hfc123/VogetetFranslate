package com.vogtec.hfc.test;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OtherTest {


    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("xx","11");
        jsonObject.addProperty("xx1","11");
        System.out.println(jsonObject.toString());
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("xx");
        jsonArray.add("xx1");
        System.out.println(jsonArray.toString());
    }
}
