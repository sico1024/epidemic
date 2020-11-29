package com.duing.springbootepidemic.util;

import com.duing.springbootepidemic.domain.AreaEpidemic;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component("dataHandler")
public class DataHandler {

    //处理json字符串对象
    private Gson gson = new Gson();


    public ArrayList<AreaEpidemic> getDataSource(String method , String url){

        ArrayList<AreaEpidemic> result = null;
        if("json".equals(method)) {
            //模拟http请求 拿到数据
            String jsonData = HttpDataSourceConnect.HttpConnectDataSource(url);
            //处理json数据
            result = handlerJsonDataSource(jsonData);
        }else if("html".equals(method)){
            result = handlerHtmlDataSource(url);
        }

        return result;
    }

    private ArrayList<AreaEpidemic> handlerHtmlDataSource(String url){
        //获取到丁香医生给到的html数据
        //自己封装的方法拿到
//        String html = HttpDataSourceConnect.HttpConnectDataSource(url);
        //用jSoup 将获取到的数据解析成html格式的数据
//        Document document = Jsoup.parse(html);

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //找到数据对应的script标签
        Element scriptEle = document.getElementById("getAreaStat");
        //获取script标签中间的文本内容
        String scriptText = scriptEle.data();
        //将拿取中括号中间的数据
        String  data = scriptText.substring(scriptText.indexOf("["),scriptText.lastIndexOf("]")+1);

        List list = gson.fromJson(data, ArrayList.class);

        ArrayList<AreaEpidemic> result = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            Map map = (Map) list.get(i);
            String name = (String) map.get("provinceName");
            double nowConfirm = (Double) map.get("currentConfirmedCount");
            double confirm = (Double) map.get("confirmedCount");
            double heal = (Double) map.get("curedCount");
            double dead = (Double) map.get("deadCount");

            result.add(new AreaEpidemic(name,(int)nowConfirm,(int)confirm,(int)heal,(int)dead));
        }

        return result;
    }



    private ArrayList<AreaEpidemic> handlerJsonDataSource(String str){
        //解析json
        Map data = gson.fromJson(str,Map.class);

        String jsonData =(String) data.get("data");

        Map map = gson.fromJson(jsonData,Map.class);

        ArrayList chinaList = (ArrayList) map.get("areaTree");

        Map chinaTree =(Map) chinaList.get(0);

        ArrayList areaTree = (ArrayList) chinaTree.get("children");

        //以上是套娃找到最需要的数据层
        ArrayList<AreaEpidemic> resultList = new ArrayList<>();

        for(int i=0;i<areaTree.size();i++){

            Map area = (Map)areaTree.get(i);
            //执行到这 找到每一个地区了
            String areaName = (String)area.get("name");
            Map totalMap = (Map)area.get("total");
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            double confirm = (Double) totalMap.get("confirm");
            double heal = (Double) totalMap.get("heal");
            double dead = (Double) totalMap.get("dead");
            resultList.add(new AreaEpidemic(areaName,(int)nowConfirm,(int)confirm,(int)heal,(int)dead)) ;
        }
        return resultList;
    }


    //从文件中读取json
//    public ArrayList<DataSource> getDataSource(){
//
//        //读取文件中的json数据
//        StringBuilder str = new StringBuilder();
//        FileReader fr =null;
//        try{
//            fr = new FileReader("temp.json");
//            char[] c = new char[1024];
//            int length = 0;
//            while((length=fr.read(c))>0){
//                str.append(new String(c,0,length));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                if(fr!=null)
//                fr.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }

}
