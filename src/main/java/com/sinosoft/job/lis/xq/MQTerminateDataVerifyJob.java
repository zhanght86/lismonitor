package com.sinosoft.job.lis.xq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 满期终止
 * */
public class MQTerminateDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("XQ", 6L);
    }
}
