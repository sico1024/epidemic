package com.duing.springbootepidemic.controller;


import com.duing.springbootepidemic.domain.Development;
import com.duing.springbootepidemic.handler.DevelopmentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DevelopmentController {


    @Autowired
    private DevelopmentHandler developmentHandler;

    @RequestMapping("/development")
    public String developmentLatest(Model model){
        List<Development> newsList = DevelopmentHandler.getDevelopments();
        model.addAttribute("newsList",newsList);
        return "development.html";
    }




}
