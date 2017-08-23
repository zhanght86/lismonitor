package com.sinosoft.dao;

import com.sinosoft.domain.LisJob;
import com.sinosoft.domain.LisJobKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LisJobMapper {
    int deleteByPrimaryKey(LisJobKey key);

    int insert(LisJob record);

    int insertSelective(LisJob record);

    LisJob selectByPrimaryKey(LisJobKey key);

    int updateByPrimaryKeySelective(LisJob record);

    int updateByPrimaryKey(LisJob record);

    List<LisJob> getAll();
}