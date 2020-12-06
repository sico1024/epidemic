package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.domain.AreaEpidemic;
import com.duing.springbootepidemic.service.AreaEpidemicService;
import com.duing.springbootepidemic.service.impl.AreaEpidemicServiceImpl;
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
    private AreaEpidemicServiceImpl areaEpidemicServiceImpl;

    private Gson gson = new Gson();

    @RequestMapping("/")
    public String chinaAreaEpidemic(Model model){

        List<AreaEpidemic> resultList = areaEpidemicServiceImpl.list();
        List<Map<String,Object>> confirmMap = new ArrayList<>();
        List<Map<String,Object>> nowConfirmMap = new ArrayList<>();

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
        model.addAttribute("confirmMap",gson.toJson(confirmMap));
        model.addAttribute("nowConfirmMap",gson.toJson(nowConfirmMap));
        model.addAttribute("areaTree",resultList);
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
