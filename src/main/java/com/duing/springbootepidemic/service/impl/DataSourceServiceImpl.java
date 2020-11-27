package com.duing.springbootepidemic.service.impl;

import com.duing.springbootepidemic.domain.DataSource;
import com.duing.springbootepidemic.service.DataSourceService;
import com.duing.springbootepidemic.util.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataHandler dataHandler;

    //获取疫情地区数据树
    @Override
    public List<DataSource> getDataSources() {
        return dataHandler.getDataSource();
    }
}
