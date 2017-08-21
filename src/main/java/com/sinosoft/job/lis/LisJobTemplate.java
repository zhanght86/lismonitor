package com.sinosoft.job.lis;

import com.sinosoft.datasource.DataSourceTypeManager;
import com.sinosoft.datasource.DataSources;
import com.sinosoft.service.EmailService;
import com.sinosoft.service.QueryLisService;
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

    private QueryLisService queryLisService = SpringUtils.getBean(QueryLisService.class);

    private EmailService emailService = SpringUtils.getBean(EmailService.class);

    //获取所有对应SQL
    private List<LinkedHashMap<String,Object>> querySQL(String module, String job) {
        String sql = "SELECT A.modulename,A.job_name,A.sql_statement,A.description FROM jobconfig a " +
                " where a.system='LIS' AND A.module='"+module+"' AND a.job_id='"+job+"' ORDER BY A.sql_id";
        return queryLisService.queryLis(sql);
    }

    //执行所有对应SQL
    private List<String> getSQLResult(List<LinkedHashMap<String,Object>> linkedHashMapList) throws Exception {
        //错误信息集合
        List<String> errList = new ArrayList<String>();
        if (linkedHashMapList == null || linkedHashMapList.size() == 0) {
            throw new Exception("任务配置有误，请修复！");
        }

        Iterator<LinkedHashMap<String,Object>> queryResultIt = linkedHashMapList.iterator();
        String moduleName;
        String jobName;
        String sql_statement;
        String desc;
        List<LinkedHashMap<String, Object>> result;
        while (queryResultIt.hasNext()) {
            LinkedHashMap<String,Object> linkedHashMap = queryResultIt.next();
            moduleName = (String) linkedHashMap.get("modulename");
            jobName = (String) linkedHashMap.get("job_name");
            sql_statement = (String) linkedHashMap.get("sql_statement");
            desc = (String) linkedHashMap.get("description");
//            LOGGER.info(job_name + " : " + sql_statement + " : " + desc);
            result = executeSQL(sql_statement);
            if (!isPass(result)) {
                LOGGER.info("核心系统【" + moduleName + "】模块下定时任务【" + jobName + "】下的规则【" + desc + "】校验未通过。");
                errList.add("核心系统【" + moduleName + "】模块下定时任务【" + jobName + "】下的规则【" + desc + "】校验未通过。");
            }
        }

        return errList;
    }

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
        boolean ispass = linkedHashMap.containsValue(new Long(1))
                || linkedHashMap.containsValue(new BigDecimal(1));
        return ispass;
    }

    //执行**模块下的**定时任务
    protected void run(String module, String job) {

        try {
            List<String> errList = getSQLResult(querySQL(module, job));

            if(errList != null && errList.size() > 0) {
                String message = StringUtils.join(errList.toArray(),"\n");
                emailService.sendEmail(message);
            }
        } catch (Exception e) {
            emailService.sendEmail(e.getMessage());
        }
    }
}
