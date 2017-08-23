package com.sinosoft.job.lis.xq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 应停效未停效校验
 * */
public class SuspendedDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("XQ",new Long(4));
    }
}
