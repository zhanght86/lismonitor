package com.sinosoft.job.lis.xq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 应催未催校验正确性
 * */
public class CSDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("XQ","1");
    }
}
