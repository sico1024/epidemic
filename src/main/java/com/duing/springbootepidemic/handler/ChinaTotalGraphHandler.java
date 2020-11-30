package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.ChinaTotalGraph;
import com.duing.springbootepidemic.util.HttpDataSourceConnect;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Map;

@Component
public class ChinaTotalGraphHandler {

    //处理json字符串
    private Gson gson = new Gson();

    private String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,cityStatis,nowConfirmStatis,provinceCompare";

    public  ArrayList<ChinaTotalGraph> getData(){
        //发送请求获取相应的json数据
        String data = HttpDataSourceConnect.HttpConnectDataSource(url);
        //gson解析json数据
        Map map = gson.fromJson(data,Map.class);
        //找到需要的数据
        Map dataMap = (Map) map.get("data");
        ArrayList list = (ArrayList) dataMap.get("chinaDayList");

        //给定一个集合存储所有结果数据
        ArrayList<ChinaTotalGraph> result = new ArrayList<>();
        //循环遍历list集合 找到相对应的
        for(int i=0;i<list.size();i++){
            Map dayMap = (Map) list.get(i);
            String date = (String)dayMap.get("date");
            double confirm = (Double) dayMap.get("confirm");
            double heal = (Double) dayMap.get("heal");
            double dead = (Double) dayMap.get("dead");
            ChinaTotalGraph graphNode = new ChinaTotalGraph(date,(int)confirm,(int)dead,(int)heal);
            result.add(graphNode);
        }
        return result;
    }



}
