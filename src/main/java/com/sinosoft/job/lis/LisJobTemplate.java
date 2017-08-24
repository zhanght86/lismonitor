package com.sinosoft.job.lis;

import com.sinosoft.dao.LisJobMapper;
import com.sinosoft.dao.LisModuleMapper;
import com.sinosoft.datasource.DataSourceTypeManager;
import com.sinosoft.datasource.DataSources;
import com.sinosoft.domain.*;
import com.sinosoft.service.EmailService;
import com.sinosoft.service.QueryLisService;
import com.sinosoft.service.RuleService;
import com.sinosoft.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class LisJobTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LisJobTemplate.class);

    /** 查询sql服务 */
    private QueryLisService queryLisService = SpringUtils.getBean(QueryLisService.class);

    /** 邮件服务 */
    private EmailService emailService = SpringUtils.getBean(EmailService.class);

    /** 规则服务 */
    private RuleService ruleService = SpringUtils.getBean(RuleService.class);

    private LisJobKey lisJobKey = SpringUtils.getBean(LisJobKey.class);

    private LisModuleMapper lisModuleMapper = SpringUtils.getBean(LisModuleMapper.class);

    private LisJobMapper lisJobMapper = SpringUtils.getBean(LisJobMapper.class);

    //切换数据库，并执行sql
    private List<LinkedHashMap<String, Object>> executeSQL(String sql) {
        if ("".equals(sql)) {
            return null;
        }
        List<LinkedHashMap<String, Object>> tLinkedHashMapList;
        //切换到核心数据库
        DataSourceTypeManager.set(DataSources.LIS);
        tLinkedHashMapList = queryLisService.queryLis(sql);
        DataSourceTypeManager.reset();
        return tLinkedHashMapList;
    }

    //查询出的结果为1，则说明校验通过
    private boolean isPass(List<LinkedHashMap<String, Object>> linkedHashMapList) {
        if (linkedHashMapList == null || linkedHashMapList.size() == 0) {
            return false;
        }
        LinkedHashMap<String, Object> linkedHashMap = linkedHashMapList.get(0);

        //MySQL返回Long类型，Oracle返回BigDecimal类型
        return linkedHashMap.containsValue(1L)
                || linkedHashMap.containsValue(new BigDecimal(1));
    }

    /** 执行**模块下的**定时任务 */
    protected void run(String module, Long job) {
        try {
            // 查询出模块信息
            LisModule lisModule = lisModuleMapper.selectByPrimaryKey(module);
            String moduleName = lisModule.getModule();

            // 查询出本次的计划信息
            lisJobKey.setId(job);
            lisJobKey.setModuleid(module);
            LisJob lisJob = lisJobMapper.selectByPrimaryKey(lisJobKey);
            String jobName = lisJob.getJob();

            // 查询出本次需执行的规则
            List<LisRule> lisRules = ruleService.getValidRulesByModuleAndJob(module, job);
            if (lisRules == null || lisRules.size() == 0) {
                LOGGER.error("任务配置有误，请修复！");
                throw new Exception("任务配置有误，请修复！");
            }

            Iterator<LisRule> lisRuleIt = lisRules.iterator();
            List<String> errList = new ArrayList<>();
            List<LinkedHashMap<String, Object>> result;
            while (lisRuleIt.hasNext()) {
                LisRule lisRule = lisRuleIt.next();
                String sql = lisRule.getRulesql();
                String ruleDesc = lisRule.getRule();
                result = executeSQL(sql);
                if (!isPass(result)) {
                    LOGGER.info("核心系统【" + moduleName + "】模块下定时任务【" + jobName + "】下的规则【" + ruleDesc + "】校验未通过。");
                    errList.add("核心系统【" + moduleName + "】模块下定时任务【" + jobName + "】下的规则【" + ruleDesc + "】校验未通过。");
                }
            }
            if(errList.size() > 0) {
                String message = StringUtils.join(errList.toArray(),"\n");
                emailService.sendEmail(message);
            }
        } catch (Exception e) {
            LOGGER.error("批处理运行异常", e);
            emailService.sendEmail(e.getMessage());
        }

    }
}
