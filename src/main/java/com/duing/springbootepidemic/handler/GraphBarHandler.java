package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.AreaEpidemic;
import com.duing.springbootepidemic.domain.GraphBar;
import com.duing.springbootepidemic.util.HttpClientUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GraphBarHandler {

    private String url = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    private Gson gson = new Gson();

    public ArrayList<GraphBar> getOutSideInputData(){

        String jsonStr = HttpClientUtil.doGet(url);
        ArrayList<GraphBar> result = parseJsonStr(jsonStr);
        return result;
    }


    private ArrayList<GraphBar> parseJsonStr(String str){

        //解析json
        Map data = gson.fromJson(str,Map.class);

        String jsonData =(String) data.get("data");

        Map map = gson.fromJson(jsonData,Map.class);

        ArrayList chinaList = (ArrayList) map.get("areaTree");

        Map chinaTree =(Map) chinaList.get(0);

        ArrayList areaTree = (ArrayList) chinaTree.get("children");

        ArrayList<GraphBar> resultList = new ArrayList<>();
        for(int i=0;i<areaTree.size();i++){

            Map area = (Map)areaTree.get(i);
            //执行到这 找到每一个地区了
            String areaName = (String)area.get("name");
            List children = (List) area.get("children");
            //循环children list 找到境外输入的数值
            for(int j=0;j<children.size();j++){
                Map child = (Map)children.get(j);
                if("境外输入".equals(child.get("name"))){
                    Map total = (Map)child.get("total");
                    double confirm = (Double) total.get("confirm");
                    resultList.add(new GraphBar(areaName,(int)confirm));
                    break;
                }

            }

        }



        return resultList;


    }
}
