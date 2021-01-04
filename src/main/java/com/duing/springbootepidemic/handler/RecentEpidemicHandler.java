package com.duing.springbootepidemic.handler;

import com.duing.springbootepidemic.domain.RecentEpidemic;
import com.duing.springbootepidemic.util.HttpClientUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecentEpidemicHandler {

    private static Gson gson = new Gson();

    private static final String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=statisGradeCityDetail";


    public static List<RecentEpidemic> getRecentData(){
        String json = HttpClientUtil.doGet(url);

        List<RecentEpidemic> result = new ArrayList<>();

        Map map = gson.fromJson(json, Map.class);

        Map data = (Map) map.get("data");

        List list = (List) data.get("statisGradeCityDetail");

        for(int i=0;i<list.size();i++){

            Map m = (Map) list.get(i);
            String city = (String) m.get("city");
            String province = (String) m.get("province");
            double confirmAdd = (double) m.get("confirmAdd");
            double nowConfirm = (double) m.get("nowConfirm");
            String grade = (String) m.get("grade");

            RecentEpidemic recentEpidemic = new RecentEpidemic(city,province,(int)confirmAdd,(int)nowConfirm,grade);
            result.add(recentEpidemic);
        }


        return result;
    }
}
