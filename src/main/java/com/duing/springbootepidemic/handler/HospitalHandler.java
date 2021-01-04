package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.AreaHospital;
import com.duing.springbootepidemic.domain.Hospital;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HospitalHandler {

    private static Gson gson = new Gson();

    //设计一个方法 读取hospital.json数据
    private static String readFile(){
        StringBuilder result = new StringBuilder();
        FileReader fr = null;
        try {
            fr = new FileReader("hospital.json");
            char[] c = new char[1024];
            int value = 0;
            value = fr.read(c);

            while(value>0){

                result.append(new String(c,0,value));
                value = fr.read(c);
            }
        }catch (Exception e){
            try {
                if(fr!=null){
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result.toString();
    }


    public static List<AreaHospital> getHospitalData(){
        String json = readFile();

        //存储返回结果
        List<AreaHospital> result = new ArrayList<>();

        //解析json文件
        List list = gson.fromJson(json,List.class);
        for(int i = 0;i<list.size();i++){
            Map map =(Map) list.get(i);

            AreaHospital areaHospital = new AreaHospital();

            String city = (String) map.get("city");
            //存储城市名称
            areaHospital.setCity(city);
            //存储医院对象
            List<Hospital> hospitals = new ArrayList<>();

            areaHospital.setTown(hospitals);

            List town = (List) map.get("town");

            for(int j = 0; j<town.size();j++){
                Hospital h = new Hospital();

                Map m = (Map) town.get(j);

                String name = (String) m.get("name");
                double count = (double) m.get("count");

                h.setName(name);
                h.setCount((int)count);

                hospitals.add(h);

            }
            result.add(areaHospital);
        }


        return result;
    }


}
