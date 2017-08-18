package com.sinosoft.service;

import com.sinosoft.dao.SQLManagerMapper;
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

    public List<LinkedHashMap<String, Object>> query(String sql) {
        return sqlManagerMapper.query(sql);
    }
}
