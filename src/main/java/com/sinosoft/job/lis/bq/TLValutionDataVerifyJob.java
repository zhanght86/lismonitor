package com.sinosoft.job.lis.bq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 投连数据正确性
 * */
public class TLValutionDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("BQ",new Long(4));
    }
}
