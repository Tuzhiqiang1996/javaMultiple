package com.example.config;

import com.example.common.DataSCH.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Tu
 * @Package com.example.config
 * @date 2021/4/10-9:54
 * @message 该类起到路由数据源的作用
 */

public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

}

