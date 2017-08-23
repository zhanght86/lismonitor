package com.sinosoft.service;

import com.sinosoft.dao.SQLManagerMapper;
import com.sinosoft.util.ArithUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class QueryLisService {

    public static final Logger LOGGER = LoggerFactory.getLogger(QueryLisService.class);

    @Autowired
    private SQLManagerMapper sqlManagerMapper;

    public List<LinkedHashMap<String, Object>> queryLis(String sql) {
        //记录当前时间点
        String start = String.valueOf(System.currentTimeMillis());

        List<LinkedHashMap<String, Object>> linkedHashMapList = sqlManagerMapper.query(sql);

        //记录sql执行后的时间点
        String end = String.valueOf(System.currentTimeMillis());
        LOGGER.info("SQL 【"+ sql +"】 耗时：" + ArithUtils.div(Double.parseDouble(ArithUtils.sub(end, start)),1000,2) + "秒");

        return linkedHashMapList;
    }
}
