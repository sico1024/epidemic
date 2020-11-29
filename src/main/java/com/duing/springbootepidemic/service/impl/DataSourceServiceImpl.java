package com.duing.springbootepidemic.service.impl;

import com.duing.springbootepidemic.domain.AreaEpidemic;
import com.duing.springbootepidemic.service.DataSourceService;
import com.duing.springbootepidemic.util.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataHandler dataHandler;

    //获取数据的地址
    private String jsonUrl = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";
    private String htmlUrl = "https://ncov.dxy.cn/ncovh5/view/pneumonia";

    //获取疫情地区数据树
    @Override
    public List<AreaEpidemic> getDataSources() {
        return dataHandler.getDataSource("html",htmlUrl);
    }
}
