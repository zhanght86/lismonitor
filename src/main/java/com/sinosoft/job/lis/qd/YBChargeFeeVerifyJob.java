package com.sinosoft.job.lis.qd;

import com.sinosoft.job.lis.LisJobTemplate;

public class YBChargeFeeVerifyJob extends LisJobTemplate {
    public void execute() {
        run("QD","2");
    }
}
