package com.vogetec.translatetool.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vogetec.translatetool.utils.Logger;
import com.vogetec.translatetool.utils.MyPluginSettings;
import okhttp3.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Translator {
    public static  String Access_token=null;
    public static  String client_id="CGz1Fj7P5NIeZnhcSXQ1zI0H";
    public static  String client_secret="HnKO7dRfMIMM6mf0EW0f5eGgH9T3efzY";
    static {
        client_id = MyPluginSettings.getId();
        client_secret = MyPluginSettings.getSecret();
    }
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */

    public static String textTrans(String from, String to, String q, String termIds) {
        // 请求url   http://api.fanyi.baidu.com/api/trans/vip/translate

        String url = "https://aip.baidubce.com/rpc/2.0/mt/texttrans/v1";
        try {
            Map<String, Object> map = new HashMap<>();
            //q= URLEncoder.encode(q);
            map.put("from",from );
            map.put("to",to );
            map.put("q", q);

            String param = GsonUtils.toJson(map);
           // System.out.println("Access_token111:"+Access_token);
            if (Access_token==null){
                getToken();
                Logger.error("需要先获取Access_token");

            }
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken =Access_token;

            String result = HttpUtil.post(url, accessToken, "application/json", param);
           // System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    //Access_token
    public static String getToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?client_id="+client_id+"&client_secret="+client_secret+"&grant_type=client_credentials")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String str;
        str =response.body().string();
        if (str!=null){
            // 使用 JsonParser 解析 JSON 字符串
            System.out.println(str);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(str).getAsJsonObject();
//            System.out.println("access_token:"+jsonObject.get("access_token").getAsString());
            return Access_token=jsonObject.get("access_token").getAsString();
        }
        return null;
    }
}
