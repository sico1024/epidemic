package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.domain.GraphBar;
import com.duing.springbootepidemic.handler.GraphBarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class GraphBarController {

    @Autowired
    private GraphBarHandler graphBarHandler;

    @RequestMapping("/graphBar")
    public String getOutSideInputGraphBar(Model model){
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

        model.addAttribute("nameList",nameList);
        model.addAttribute("confirmList",confirmList);

        System.out.println(nameList);
        System.out.println(confirmList);
        return "graphBar.html";
    }

}
