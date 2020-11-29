package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.AreaEpidemic;
import com.duing.springbootepidemic.service.AreaEpidemicService;
import com.duing.springbootepidemic.util.HttpDataSourceConnect;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.geom.Area;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Component("areaEpidemicHandler")
public class AreaEpidemicHandler {

    //处理日期格式
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //处理json字符串对象
    private Gson gson = new Gson();

    //获取数据的地址
    private String jsonUrl = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
    private String htmlUrl = "https://ncov.dxy.cn/ncovh5/view/pneumonia";

    //service
    @Autowired
    private AreaEpidemicService service;

    //初始化数据库方法
    @PostConstruct
    public void initDataBase(){
        System.out.println(new Date()+" --- [初始化数据]");
        List<AreaEpidemic> data = getDataSource();

        service.remove(null);

        service.saveBatch(data);

    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void updateDataBase(){
        System.out.println(new Date()+" --- [更新数据]");

        List<AreaEpidemic> data = getDataSource();
        service.remove(null);
        service.saveBatch(data);
    }

    //更新数据方法

    public ArrayList<AreaEpidemic> getDataSource(){
        ArrayList<AreaEpidemic> result = null;
        //模拟http请求 拿到数据
        String data = HttpDataSourceConnect.HttpConnectDataSource(jsonUrl);

        if(data==null || "".equals(data)) {//如果再Tencent没有拿到 就拿丁香医生的数据
            result = handlerHtmlDataSource(htmlUrl);
        }else {
            //处理json数据
            result = handlerJsonDataSource(data);
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

            result.add(new AreaEpidemic(null,name,(int)nowConfirm,(int)confirm,(int)heal,(int)dead));
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
            resultList.add(new AreaEpidemic(null,areaName,(int)nowConfirm,(int)confirm,(int)heal,(int)dead)) ;
        }
        return resultList;
    }

    //传参数选择数据源
//    public ArrayList<AreaEpidemic> getDataSource(String method ){
//        ArrayList<AreaEpidemic> result = null;
//        if("json".equals(method)) {
//            //模拟http请求 拿到数据
//            String jsonData = HttpDataSourceConnect.HttpConnectDataSource(jsonUrl);
//            //处理json数据
//            result = handlerJsonDataSource(jsonData);
//        }else if("html".equals(method)){
//            result = handlerHtmlDataSource(htmlUrl);
//        }
//
//        return result;
//    }

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
