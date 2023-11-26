package com.hws.utils;

import com.alibaba.fastjson2.JSON;

import java.util.Map;

public class JsonUtil {

    public static Map<String, Object> jsonToMap(String json) {
        return JSON.parseObject(json, Map.class);
    }

    // 可以根据需要添加其他的 JSON 解析方法
}
