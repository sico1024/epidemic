package com.duing.springbootepidemic.service;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface ChinaTotalGraphService {


    public void getDataAndInitModel(Map<String, List> map);
}
