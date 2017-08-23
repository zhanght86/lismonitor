package com.sinosoft.job.lis.qd;

import com.sinosoft.job.lis.LisJobTemplate;

public class ExtraDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("QD",new Long(1));
    }
}
