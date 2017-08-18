package com.sinosoft.dao;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface SQLManagerMapper {
    List<LinkedHashMap<String, Object>> query(String sql);
}
