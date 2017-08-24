package com.sinosoft.job.lis.cw;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 重复收付费数据正确性
 * */
public class RepeatPayAndReceiveDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("CW", 1L);
    }
}
