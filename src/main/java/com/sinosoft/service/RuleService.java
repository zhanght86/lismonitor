package com.sinosoft.service;

import com.sinosoft.dao.LisRuleMapper;
import com.sinosoft.domain.LisRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

    private LisRuleMapper lisRuleMapper;

    @Autowired
    public void setLisRuleMapper(LisRuleMapper lisRuleMapper) {
        this.lisRuleMapper = lisRuleMapper;
    }

    /**
     * 根据模块和任务获取所有规则
     * */
    public List<LisRule> getValidRulesByModuleAndJob(String moduleid, Long jobid) {
        List<LisRule> lisRules = lisRuleMapper.getAll();
        List<LisRule> resultLisRules = new ArrayList<>();

        for (LisRule lisRule : lisRules) {
            if (lisRule.getJobid().longValue() == jobid.longValue()
                    && lisRule.getModuleid().equals(moduleid)
                    && 0 == lisRule.getState()) {
                resultLisRules.add(lisRule);
            }
        }
        return resultLisRules;
    }
}
