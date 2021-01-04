package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.Development;
import com.duing.springbootepidemic.util.HttpClientUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class DevelopmentHandler {


    private static Gson gson = new Gson();

    private static final String url = "https://api.inews.qq.com/newsqa/v1/automation/modules/list?modules=FAutoNewsArticleList";

    public static List<Development> getDevelopments(){
        //发送请求，获得数据
        String json = HttpClientUtil.doGet(url);

        //给定一个List存储最终结果
        List<Development> result = new ArrayList<>();

        //处理json 数据
        Map dataMap = (Map)gson.fromJson(json,Map.class);

        Map data = (Map)dataMap.get("data");

        List list = (List) data.get("FAutoNewsArticleList");
        //遍历List
        for(int i=0;i<list.size();i++){
             Map m = (Map)list.get(i);
             String title = (String)m.get("title");
             String publishTime = (String)m.get("publish_time");
             String desc = (String)m.get("desc");
             String url = (String)m.get("url");
             result.add(new Development(title,desc,publishTime,url));
        }
        return result;
    }


}
