package com.duing.springbootepidemic.service.impl;

import com.duing.springbootepidemic.domain.ChinaTotalGraph;
import com.duing.springbootepidemic.handler.ChinaTotalGraphHandler;
import com.duing.springbootepidemic.service.ChinaTotalGraphService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChinaTotalGraphServiceImpl implements ChinaTotalGraphService {

    @Autowired
    private ChinaTotalGraphHandler handler;

    private Gson gson = new Gson();


    public void getDataAndInitModel(Model model){

        ArrayList<ChinaTotalGraph> list = handler.getData();

//        循环遍历集合，将所需的数据存放到四个不同的集合之中
        List<String> date = new ArrayList<>();
        List<Integer> confirm = new ArrayList<>();
        List<Integer> heal = new ArrayList<>();
        List<Integer> dead = new ArrayList<>();

        //将值拆分放入四个集合之中
        for(ChinaTotalGraph graph:list){
            date.add(graph.getDate());
            confirm.add(graph.getConfirm());
            heal.add(graph.getHeal());
            dead.add(graph.getDead());
        }
        //存入Model之中
        model.addAttribute("date",gson.toJson(date));
        model.addAttribute("confirm",gson.toJson(confirm));
        model.addAttribute("heal",gson.toJson(heal));
        model.addAttribute("dead",gson.toJson(dead));
    }


}
