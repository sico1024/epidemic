package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.domain.GraphBar;
import com.duing.springbootepidemic.handler.GraphBarHandler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class GraphBarController {

    @Autowired
    private GraphBarHandler graphBarHandler;

    @RequestMapping("/graphBar")
    @ResponseBody
    public String getOutSideInputGraphBar(){
        ArrayList<GraphBar> list = graphBarHandler.getOutSideInputData();

        //将集合进行排序
        Collections.sort(list);

        //用于存放条形图的横轴和纵轴
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Integer> confirmList = new ArrayList<>();

        //循环存入数据
        for(int i = 0;i<10;i++){
            GraphBar bar = list.get(i);
            nameList.add(bar.getName());
            confirmList.add(bar.getConfirm());
        }
        Map<String,List> resultMap = new HashMap();
        resultMap.put("nameList",nameList);
        resultMap.put("confirmList",confirmList);

        return new Gson().toJson(resultMap);
    }

}
