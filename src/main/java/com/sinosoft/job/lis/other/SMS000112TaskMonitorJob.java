package com.sinosoft.job.lis.other;

import com.sinosoft.job.lis.LisJobTemplate;

public class SMS000112TaskMonitorJob extends LisJobTemplate {
    public void execute() {
        run("SMS",new Long(1));
    }
}
