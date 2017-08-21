package com.sinosoft.job.lis.bq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 红利数据正确性
 * */
public class BonusDataVerifyJob extends LisJobTemplate {

    public void execute() {
        run("BQ","1");
    }
}
