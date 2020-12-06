package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.service.ChinaTotalGraphService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChinaTotalGraphController {

    @Autowired
    private ChinaTotalGraphService chinaTotalGraphService;

    @RequestMapping("/graph")
    @ResponseBody
    public String graph(){
        Map<String, List> result = new HashMap<>();
        chinaTotalGraphService.getDataAndInitModel(result);
        return new Gson().toJson(result);
    }


}
