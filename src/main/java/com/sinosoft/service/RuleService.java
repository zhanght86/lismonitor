package com.sinosoft.service;

import com.sinosoft.dao.LisRuleMapper;
import com.sinosoft.domain.LisRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RuleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

    @Autowired
    private LisRuleMapper lisRuleMapper;

    /**
     * 根据模块和任务获取所有规则
     * */
    public List<LisRule> getValidRulesByModuleAndJob(String moduleid, Long jobid) {
        List<LisRule> lisRules = lisRuleMapper.getAll();
        List<LisRule> resultLisRules = new ArrayList<>();

        Iterator<LisRule> lisRulesIt = lisRules.iterator();

        while (lisRulesIt.hasNext()) {
            LisRule lisRule = lisRulesIt.next();
            if (lisRule.getJobid().longValue() == jobid.longValue()
                    && lisRule.getModuleid().equals(moduleid)
                    && 0 == lisRule.getState().longValue()) {
                resultLisRules.add(lisRule);
            }
        }
        return resultLisRules;
    }
}
