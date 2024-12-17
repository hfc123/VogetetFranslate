package com.vogetec.translatetool.action.test;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class OtherTest {


    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        jsonObject.add("xx",gson.toJsonTree("11"));
        jsonObject.addProperty("xx1","11");
        System.out.println(jsonObject.toString());
        JsonArray jsonArray = new JsonArray();

        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject);

        String json = gson.toJson(jsonArray);
        String str = "[{\"xx\":\"11\",\"xx1\":\"11\"},{\"xx\":\"11\",\"xx1\":\"11\"}]";
        System.out.println(str);
        Map<String,Object> map = new HashMap<>();
        map.put("data",jsonArray);
         json = gson.toJson(map);
        System.out.println("map"+json);
    }
}
