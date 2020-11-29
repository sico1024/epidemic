package com.duing.springbootepidemic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duing.springbootepidemic.domain.AreaEpidemic;
import com.duing.springbootepidemic.mapper.AreaEpidemicMapper;
import com.duing.springbootepidemic.service.AreaEpidemicService;
import org.springframework.stereotype.Service;


@Service("AreaEpidemicServiceImpl")
public class AreaEpidemicServiceImpl extends ServiceImpl<AreaEpidemicMapper,AreaEpidemic> implements AreaEpidemicService {

//    @Autowired
//    private DataHandler dataHandler;
//
//
//    //获取疫情地区数据树
//    @Override
//    public List<AreaEpidemic> getDataSources() {
//        return dataHandler.getDataSource();
//    }
}
