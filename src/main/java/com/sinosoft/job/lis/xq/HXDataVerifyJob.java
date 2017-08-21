package com.sinosoft.job.lis.xq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 应核销未核销校验
 * */
public class HXDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("XQ","2");
    }
}
