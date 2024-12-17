package com.vogetec.translatetool.lang;

import java.util.HashMap;
import java.util.Map;

public class LanguageMapBuilder {
    
    public static void main(String[] args) {
        Map<String, Language> languageMap = buildLanguageMap();

        // 输出语种信息
        for (Map.Entry<String, Language> entry : languageMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    private  static LanguageMapBuilder languageMapBuilder ;
    private static Map<String, Language> languageMaps ;
    public static LanguageMapBuilder getInstance(){
        if (languageMapBuilder==null){
            languageMapBuilder = new LanguageMapBuilder();
            languageMaps = languageMapBuilder.buildLanguageMap();
        }
        return languageMapBuilder;
    }

    public Map<String, Language> getLanguageMaps() {
        if (languageMaps==null){
            return buildLanguageMap();
        }
        return languageMaps;
    }

    public void setLanguageMaps(Map<String, Language> languageMaps) {
        this.languageMaps = languageMaps;
    }

    @SuppressWarnings("Duplicates")
    public static Map<String, Language> buildLanguageMap() {
        Map<String, Language> languageMap = new HashMap<>();
        
        // 添加语种信息到 Map
        addLanguageToMap(languageMap, "alb", "sq", true, "阿尔巴尼亚语");
        addLanguageToMap(languageMap, "ara", "ar", true, "阿拉伯语");
        addLanguageToMap(languageMap, "gle", "ga", true, "爱尔兰语");
        addLanguageToMap(languageMap, "oci", "oc", true, "奥克语");
        addLanguageToMap(languageMap, "arq", "arq", false, "阿尔及利亚阿拉伯语");
        addLanguageToMap(languageMap, "aka", "ak", false, "阿肯语");
        addLanguageToMap(languageMap, "arg", "an", false, "阿拉贡语");
        addLanguageToMap(languageMap, "amh", "am", true, "阿姆哈拉语");
        addLanguageToMap(languageMap, "asm", "as", true, "阿萨姆语");
        addLanguageToMap(languageMap, "aym", "ay", false, "艾马拉语");
        addLanguageToMap(languageMap, "aze", "az", true, "阿塞拜疆语");
        addLanguageToMap(languageMap, "ast", "ast", true, "阿斯图里亚斯语");
        addLanguageToMap(languageMap, "oss", "os", false, "奥塞梯语");
        addLanguageToMap(languageMap, "est", "et", true, "爱沙尼亚语");
        addLanguageToMap(languageMap, "oji", "oj", false, "奥杰布瓦语");
        addLanguageToMap(languageMap, "ori", "or", true, "奥里亚语");
        addLanguageToMap(languageMap, "orm", "om", false, "奥罗莫语");
        addLanguageToMap(languageMap, "aym", "ay", false, "艾马拉语");
        addLanguageToMap(languageMap, "aze", "az", true, "阿塞拜疆语");
        addLanguageToMap(languageMap, "ast", "ast", true, "阿斯图里亚斯语");
        addLanguageToMap(languageMap, "oss", "os", false, "奥塞梯语");
        addLanguageToMap(languageMap, "est", "et", true, "爱沙尼亚语");
        addLanguageToMap(languageMap, "oji", "oj", false, "奥杰布瓦语");
        addLanguageToMap(languageMap, "ori", "or", true, "奥里亚语");
        addLanguageToMap(languageMap, "orm", "om", false, "奥罗莫语");
        // 添加更多语种...
        addLanguageToMap(languageMap, "bre", "br", true, "布列塔尼语");
        addLanguageToMap(languageMap, "bak", "ba", false, "巴什基尔语");
        addLanguageToMap(languageMap, "baq", "eu", true, "巴斯克语");
        addLanguageToMap(languageMap, "pot", "pt_BR", false, "巴西葡萄牙语");
        addLanguageToMap(languageMap, "bel", "be", true, "白俄罗斯语");
        addLanguageToMap(languageMap, "ber", "kab", true, "柏柏尔语");
        addLanguageToMap(languageMap, "pam", "pam", false, "邦板牙语");
        addLanguageToMap(languageMap, "bul", "bg", true, "保加利亚语");
        addLanguageToMap(languageMap, "sme", "smj", false, "北方萨米语");
        addLanguageToMap(languageMap, "ped", "stq", false, "北索托语");
        addLanguageToMap(languageMap, "bem", "bem", false, "本巴语");
        addLanguageToMap(languageMap, "bli", "bli", false, "比林语");
        addLanguageToMap(languageMap, "bis", "bis", false, "比斯拉马语");
        addLanguageToMap(languageMap, "bal", "bal", false, "俾路支语");
        addLanguageToMap(languageMap, "ice", "is", true, "冰岛语");
        addLanguageToMap(languageMap, "bos", "bs", true, "波斯尼亚语");
        addLanguageToMap(languageMap, "bho", "bho", false, "博杰普尔语");
        addLanguageToMap(languageMap, "frn", "fr_CA", false, "加拿大法语");
        addLanguageToMap(languageMap, "cat", "ca", true, "加泰罗尼亚语");
        addLanguageToMap(languageMap, "cs", "cs", true, "捷克语");
        addLanguageToMap(languageMap, "kab", "kab", true, "卡拜尔语");
        addLanguageToMap(languageMap, "kan", "kn", true, "卡纳达语");
        addLanguageToMap(languageMap, "kau", "kr", false, "卡努里语");
        addLanguageToMap(languageMap, "kah", "kj", false, "卡舒比语");
        addLanguageToMap(languageMap, "cor", "kw", false, "康瓦尔语");
        addLanguageToMap(languageMap, "xho", "xh", true, "科萨语");
        addLanguageToMap(languageMap, "cos", "co", false, "科西嘉语");
        addLanguageToMap(languageMap, "cre", "cr", false, "克里克语");
        addLanguageToMap(languageMap, "cri", "crh", false, "克里米亚鞑靼语");
        addLanguageToMap(languageMap, "kli", "tlh", false, "克林贡语");
        addLanguageToMap(languageMap, "hrv", "hr", true, "克罗地亚语");
        addLanguageToMap(languageMap, "que", "qu", false, "克丘亚语");
        addLanguageToMap(languageMap, "kas", "ks", false, "克什米尔语");
        addLanguageToMap(languageMap, "kok", "kok", false, "孔卡尼语");
        addLanguageToMap(languageMap, "kur", "ku", true, "库尔德语");
        addLanguageToMap(languageMap, "lat", "la", true, "拉丁语");
        addLanguageToMap(languageMap, "lao", "lo", false, "老挝语");
        addLanguageToMap(languageMap, "rom", "ro", true, "罗马尼亚语");
        addLanguageToMap(languageMap, "lag", "lag", false, "拉特加莱语");
        addLanguageToMap(languageMap, "lav", "lv", true, "拉脱维亚语");
        addLanguageToMap(languageMap, "lim", "li", false, "林堡语");
        addLanguageToMap(languageMap, "lin", "ln", false, "林加拉语");
        addLanguageToMap(languageMap, "lug", "lg", false, "卢干达语");
        addLanguageToMap(languageMap, "ltz", "lb", false, "卢森堡语");
        addLanguageToMap(languageMap, "ruy", "lu", false, "卢森尼亚语");
        addLanguageToMap(languageMap, "kin", "rw", true, "卢旺达语");
        addLanguageToMap(languageMap, "lit", "lt", true, "立陶宛语");
        addLanguageToMap(languageMap, "roh", "rm", false, "罗曼什语");
        addLanguageToMap(languageMap, "ro", "ro", false, "罗姆语");
        addLanguageToMap(languageMap, "loj", "lo", false, "逻辑语");
        addLanguageToMap(languageMap, "may", "ms", true, "马来语");
        addLanguageToMap(languageMap, "bur", "my", true, "缅甸语");
        addLanguageToMap(languageMap, "mar", "mr", false, "马拉地语");
        addLanguageToMap(languageMap, "mg", "mg", true, "马拉加斯语");
        addLanguageToMap(languageMap, "mal", "ml", true, "马拉雅拉姆语");
        addLanguageToMap(languageMap, "mac", "mk", true, "马其顿语");
        addLanguageToMap(languageMap, "mah", "mh", false, "马绍尔语");
        addLanguageToMap(languageMap, "mai", "mai", true, "迈蒂利语");
        addLanguageToMap(languageMap, "glv", "gv", false, "曼克斯语");
        addLanguageToMap(languageMap, "mau", "mfe", false, "毛里求斯克里奥尔语");
        addLanguageToMap(languageMap, "mao", "mi", false, "毛利语");
        addLanguageToMap(languageMap, "ben", "bn", true, "孟加拉语");
        addLanguageToMap(languageMap, "mlt", "mt", true, "马耳他语");
        addLanguageToMap(languageMap, "hmn", "mww", false, "苗语");
        addLanguageToMap(languageMap, "nor", "no", true, "挪威语");
        addLanguageToMap(languageMap, "nea", "nia", false, "那不勒斯语");
        addLanguageToMap(languageMap, "nbl", "nr", false, "南恩德贝莱语");
        addLanguageToMap(languageMap, "afr", "af", true, "南非荷兰语");
        addLanguageToMap(languageMap, "sot", "st", false, "南索托语");
        addLanguageToMap(languageMap, "nep", "ne", true, "尼泊尔语");
        addLanguageToMap(languageMap, "pt", "pt", true, "葡萄牙语");
        addLanguageToMap(languageMap, "pan", "pa", true, "旁遮普语");
        addLanguageToMap(languageMap, "pap", "pap", false, "帕皮阿门托语");
        addLanguageToMap(languageMap, "pus", "ps", false, "普什图语");
        addLanguageToMap(languageMap, "th", "th", true, "泰语");
        addLanguageToMap(languageMap, "tr", "tr", true, "土耳其语");
        addLanguageToMap(languageMap, "per", "fa", true, "波斯语");
        addLanguageToMap(languageMap, "jp", "ja", true, "日语");
        addLanguageToMap(languageMap, "swe", "sv", true, "瑞典语");
        addLanguageToMap(languageMap, "nya", "sn", false, "齐切瓦语");
        addLanguageToMap(languageMap, "sec", "sh", false, "塞尔维亚-克罗地亚语");
        addLanguageToMap(languageMap, "srp", "sr", true, "塞尔维亚语");
        addLanguageToMap(languageMap, "sol", "son", false, "桑海语");
        addLanguageToMap(languageMap, "sin", "si", true, "僧伽罗语");
        addLanguageToMap(languageMap, "epo", "eo", true, "世界语");
        addLanguageToMap(languageMap, "nob", "nb", true, "书面挪威语");
        addLanguageToMap(languageMap, "sk", "sk", true, "斯洛伐克语");
        addLanguageToMap(languageMap, "slo", "sl", true, "斯洛文尼亚语");
        addLanguageToMap(languageMap, "swa", "sw", true, "斯瓦希里语");
        addLanguageToMap(languageMap, "som", "so", true, "索马里语");
        addLanguageToMap(languageMap, "gla", "gd", false, "苏格兰盖尔语");
        addLanguageToMap(languageMap, "tgk", "tg", true, "塔吉克语");
        addLanguageToMap(languageMap, "tam", "ta", true, "泰米尔语");
        addLanguageToMap(languageMap, "tel", "te", true, "泰卢固语");
        addLanguageToMap(languageMap, "tir", "ti", false, "提格利尼亚语");
        addLanguageToMap(languageMap, "ton", "to", false, "汤加语");
        addLanguageToMap(languageMap, "tuk", "tk", false, "特鲁库语");
        addLanguageToMap(languageMap, "tua", "tne", false, "突尼斯阿拉伯语");
        addLanguageToMap(languageMap, "tuk", "tk", false, "土库曼语");
        addLanguageToMap(languageMap, "ukr", "uk", true, "乌克兰语");
        addLanguageToMap(languageMap, "urd", "ur", true, "乌尔都语");
        addLanguageToMap(languageMap, "zh", "zh", true, "中文（简体）");
        addLanguageToMap(languageMap, "cht", "zh_TW", true, "中文（繁体）");
        addLanguageToMap(languageMap, "wyw", "wyw", true, "中文（文言文）");
        addLanguageToMap(languageMap, "yue", "zh_HK", true, "中文（粤语）");
        addLanguageToMap(languageMap, "zaz", "zzj", false, "扎扎其语");
        addLanguageToMap(languageMap, "frm", "fro", false, "中古法语");
        addLanguageToMap(languageMap, "zul", "zu", false, "祖鲁语");
        addLanguageToMap(languageMap, "it", "it", true, "意大利语");
        addLanguageToMap(languageMap, "pl", "pl", true, "波兰语");
        addLanguageToMap(languageMap, "nl", "nl", true, "荷兰语");
        addLanguageToMap(languageMap, "ru", "ru", true, "俄语");
        addLanguageToMap(languageMap, "fra", "fr", true, "俄语");
        addLanguageToMap(languageMap, "fin", "fi", true, "芬兰语");
        addLanguageToMap(languageMap, "el", "el", true, "希腊语");
        addLanguageToMap(languageMap, "de", "de", true, "德语");
        addLanguageToMap(languageMap, "spa", "es", true, "西班牙语");
        addLanguageToMap(languageMap, "en", "en", true, "英语");

        return languageMap;
    }
    
    public static void addLanguageToMap(Map<String, Language> map, String code, String androidCode, boolean supported, String name) {
        Language language = new Language(code, androidCode, supported, name);
        map.put(androidCode, language);
    }
}

