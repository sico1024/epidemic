package com.duing.springbootepidemic.util;

import com.duing.springbootepidemic.domain.DataSource;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@Component("dataHandler")
public class DataHandler {



    public ArrayList<DataSource> getDataSource(){

        //读取文件中的json数据
        StringBuilder str = new StringBuilder();
        FileReader fr =null;
        try{
            fr = new FileReader("temp.json");
            char[] c = new char[1024];
            int length = 0;
            while((length=fr.read(c))>0){
                str.append(new String(c,0,length));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(fr!=null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //解析json
        Gson gson = new Gson();
        Map map = gson.fromJson(str.toString(),Map.class);


        ArrayList chinaList = (ArrayList) map.get("areaTree");

        Map chinaTree =(Map) chinaList.get(0);

        ArrayList areaTree = (ArrayList) chinaTree.get("children");


        ArrayList<DataSource> resultList = new ArrayList<>();

        for(int i=0;i<areaTree.size();i++){

            Map area = (Map)areaTree.get(i);
            //执行到这 找到每一个地区了
            String areaName = (String)area.get("name");
            Map totalMap = (Map)area.get("total");
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            double confirm = (Double) totalMap.get("confirm");
            double heal = (Double) totalMap.get("heal");
            double dead = (Double) totalMap.get("dead");
            resultList.add(new DataSource(areaName,(int)nowConfirm,(int)confirm,(int)heal,(int)dead)) ;
        }

        return resultList;
    }


}
