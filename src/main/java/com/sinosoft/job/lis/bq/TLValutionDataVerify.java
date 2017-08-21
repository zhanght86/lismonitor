package com.sinosoft.job.lis.bq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 投连数据正确性
 * */
public class TLValutionDataVerify extends LisJobTemplate {
    public void execute() {
        run("BQ","4");
    }
}
