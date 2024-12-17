package com.vogetec.translatetool.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 创建一个包含字符串的 List
        List<String> idList = new ArrayList<>();
        idList.add("1");
        idList.add("2");
        idList.add("3");

        // 创建 IdListWrapper 对象并设置 idList
        IdListWrapper wrapper = new IdListWrapper(idList);

        // 使用 Gson 将对象转换为 JSON 字符串
        Gson gson = new Gson();
        String json = gson.toJson(wrapper);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("params",json);
        // 输出 JSON 字符串
        System.out.println(jsonObject.toString());
    }
}
