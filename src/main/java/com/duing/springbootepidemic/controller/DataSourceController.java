package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.domain.DataSource;
import com.duing.springbootepidemic.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;


@Controller
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping("/")
    public String test(Model model){

        List<DataSource> resultList = dataSourceService.getDataSources();
        model.addAttribute("areaTree",resultList);
        return "test.html";

    }




}
