package com.sinosoft.job.lis.lp;

import com.sinosoft.job.lis.LisJobTemplate;

public class ClaimDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("LP",new Long(1));
    }
}
