package com.sinosoft.job.lis.xq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 停效满两年终止
 * */
public class SuspendedOver2YDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("XQ","5");
    }
}
