package com.sinosoft.job.lis.cw;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 坏账数据正确性
 * */
public class BadDebtsDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("CW",new Long(2));
    }
}
