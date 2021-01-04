package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.EpidemicToday;
import com.duing.springbootepidemic.util.HttpDataSourceConnect;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class EpidemicTodayHandler {

    private static Gson gson = new Gson();
    private static String jsonUrl = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";


    //获取数据
//    @SuppressWarnings("all")
    public static EpidemicToday getDataSource(){



        String str = HttpDataSourceConnect.HttpConnectDataSource(jsonUrl);
        Map data = gson.fromJson(str, Map.class);

        String jsonData =(String) data.get("data");

        Map map = gson.fromJson(jsonData,Map.class);

        String lastUpdateTime = (String) map.get("lastUpdateTime");


        Map chinaTotal = (Map)map.get("chinaTotal");
        double nowConfirm = (double)chinaTotal.get("nowConfirm");
        double noInfect = (double)chinaTotal.get("noInfect");
        double importedCase = (double)chinaTotal.get("importedCase");
        double confirm = (double)chinaTotal.get("confirm");
        double heal = (double)chinaTotal.get("heal");
        double dead = (double)chinaTotal.get("dead");

        Map chinaAdd = (Map)map.get("chinaAdd");
        double compareNowConfirm = (double)chinaAdd.get("nowConfirm");
        double compareNoInfect = (double)chinaAdd.get("noInfect");
        double compareImportedCase = (double)chinaAdd.get("importedCase");
        double compareConfirm = (double)chinaAdd.get("confirm");
        double compareHeal = (double)chinaAdd.get("heal");
        double compareDead = (double)chinaAdd.get("dead");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        EpidemicToday result = new EpidemicToday();

        result.setLastUpdateTime(lastUpdateTime);
        result.setNowConfirm((int)nowConfirm);
        result.setNoInfect((int)noInfect);
        result.setImportedCase((int)importedCase);
        result.setConfirm((int)confirm);
        result.setHeal((int)heal);
        result.setDead((int)dead);

        result.setCompareNowConfirm(parseCount(compareNowConfirm));
        result.setCompareNoInfect(parseCount(compareNoInfect));
        result.setCompareImportedCase(parseCount(compareImportedCase));
        result.setCompareConfirm(parseCount(compareConfirm));
        result.setCompareHeal(parseCount(compareHeal));
        result.setCompareDead(parseCount(compareDead));

        return result;
    }

    private static String parseCount(double count){
        int num = (int) count;
        String result = null;
        if(num>0){
            result = "+"+num;
        }else {
            result = ""+num;
        }
        return result;

    }





}
