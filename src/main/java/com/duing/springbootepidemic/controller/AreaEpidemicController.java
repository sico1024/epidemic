package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.domain.*;
import com.duing.springbootepidemic.handler.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AreaEpidemicController {

    @Autowired
    private AreaEpidemicHandler areaEpidemicHandler;



    private Gson gson = new Gson();

    @RequestMapping("/")
    public String chinaAreaEpidemic(Model model){

        List<AreaEpidemic> resultList = areaEpidemicHandler.getDataSource();
        List<Map<String,Object>> confirmMap = new ArrayList<>();
        List<Map<String,Object>> nowConfirmMap = new ArrayList<>();
        List<Development> newsList = DevelopmentHandler.getDevelopments();

        List<Foreign> foreigns = ForeignHandler.getForeignData();

        List<AreaHospital> hospitals = HospitalHandler.getHospitalData();

        List<RecentEpidemic> recentEpidemics = RecentEpidemicHandler.getRecentData();

        for(int i=0;i<resultList.size();i++){
            AreaEpidemic areaEpidemic = resultList.get(i);
            Map<String,Object> m1 = new HashMap<>();
            Map<String,Object> m2 = new HashMap<>();

            m1.put("name",areaEpidemic.getName());
            m1.put("value",areaEpidemic.getConfirm());
            confirmMap.add(m1);

            m2.put("name",areaEpidemic.getName());
            m2.put("value",areaEpidemic.getNowConfirm());
            nowConfirmMap.add(m2);
        }

        EpidemicToday et = EpidemicTodayHandler.getDataSource();

        model.addAttribute("confirmMap",gson.toJson(confirmMap));
        model.addAttribute("nowConfirmMap",gson.toJson(nowConfirmMap));
        model.addAttribute("areaTree",resultList);
        model.addAttribute("newsList",newsList);
        model.addAttribute("et",et);
        model.addAttribute("hospitals",hospitals);
        model.addAttribute("foreign",foreigns);
        model.addAttribute("recentEpidemics",recentEpidemics);

        return "epidemic.html";

    }

//    @RequestMapping("/chinaMap")
//    public String chinaAreaEpidemicMap(Model model){
//        List<AreaEpidemic> resultList = areaEpidemicServiceImpl.list();
//
//        List<Map<String,Object>> confirmMap = new ArrayList<>();
//        List<Map<String,Object>> nowConfirmMap = new ArrayList<>();
//
//        for(int i=0;i<resultList.size();i++){
//            AreaEpidemic areaEpidemic = resultList.get(i);
//            Map<String,Object> m1 = new HashMap<>();
//            Map<String,Object> m2 = new HashMap<>();
//
//            m1.put("name",areaEpidemic.getName());
//            m1.put("value",areaEpidemic.getConfirm());
//            confirmMap.add(m1);
//
//            m2.put("name",areaEpidemic.getName());
//            m2.put("value",areaEpidemic.getNowConfirm());
//            nowConfirmMap.add(m2);
//        }
//        model.addAttribute("confirmMap",gson.toJson(confirmMap));
//        model.addAttribute("nowConfirmMap",gson.toJson(nowConfirmMap));
//
//        return "chinaMap.html";
//
//    }




}
