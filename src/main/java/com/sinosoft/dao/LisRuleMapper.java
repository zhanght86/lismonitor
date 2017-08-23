package com.sinosoft.dao;

import com.sinosoft.domain.LisRule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LisRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LisRule record);

    int insertSelective(LisRule record);

    LisRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LisRule record);

    int updateByPrimaryKey(LisRule record);

    List<LisRule> getAll();
}