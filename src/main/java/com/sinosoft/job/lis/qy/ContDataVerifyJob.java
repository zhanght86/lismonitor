package com.sinosoft.job.lis.qy;

import com.sinosoft.job.lis.LisJobTemplate;
import com.sinosoft.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 保单数据正确性
 * */
public class ContDataVerifyJob extends LisJobTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContDataVerifyJob.class);

    public void execute() {

        LOGGER.info("ContDataVerify 开始跑批： " + DateUtils.getCurrentDateTime());

        run("QY",1L);
    }
}
