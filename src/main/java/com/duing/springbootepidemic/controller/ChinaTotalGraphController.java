package com.duing.springbootepidemic.controller;

import com.duing.springbootepidemic.service.ChinaTotalGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChinaTotalGraphController {

    @Autowired
    private ChinaTotalGraphService chinaTotalGraphService;

    @RequestMapping("/graph")
    public String graph(Model model){

        chinaTotalGraphService.getDataAndInitModel(model);
        return "graph.html";
    }


}
