package com.sinosoft.job.lis.other;

import com.sinosoft.job.lis.LisJobTemplate;

public class SMS000301TaskMonitorJob extends LisJobTemplate {
    public void execute() {
        run("SMS",2L);
        }
        }
