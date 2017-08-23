package com.sinosoft.dao;

import com.sinosoft.domain.LisModule;
import org.springframework.stereotype.Repository;

@Repository
public interface LisModuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(LisModule record);

    int insertSelective(LisModule record);

    LisModule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LisModule record);

    int updateByPrimaryKey(LisModule record);
}