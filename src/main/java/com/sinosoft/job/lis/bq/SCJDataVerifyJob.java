package com.sinosoft.job.lis.bq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 生存金数据正确性
 * */
public class SCJDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("BQ",3L);
    }
}
