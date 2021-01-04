package com.duing.springbootepidemic.handler;


import com.duing.springbootepidemic.domain.Foreign;
import com.duing.springbootepidemic.util.HttpClientUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForeignHandler {

    private static Gson gson = new Gson();

    private static final String url = "https://api.inews.qq.com/newsqa/v1/automation/foreign/country/ranklist";

    public static List<Foreign> getForeignData(){
        //模拟http请求 获取数据
        String json = HttpClientUtil.doGet(url);

        return parseJson(json);
    }

    //解析json
    private static List<Foreign> parseJson(String json){

        Map map = gson.fromJson(json, Map.class);
        List data = (List) map.get("data");

        //存储结果
        List<Foreign> result = new ArrayList<>();

        for(int i=0;i<data.size();i++){
            Map country = (Map) data.get(i);

            String name = (String)country.get("name");
            double confirmAdd = (double) country.get("confirmAdd");
            double confirm = (double) country.get("confirm");
            double heal = (double) country.get("heal");
            double dead = (double) country.get("dead");

            Foreign foreign = new Foreign(name,(int)confirmAdd,(int)confirm,(int)heal,(int)dead);

            result.add(foreign);

        }

        return result;
    }


}
