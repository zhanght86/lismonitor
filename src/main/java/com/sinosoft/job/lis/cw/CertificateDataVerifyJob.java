package com.sinosoft.job.lis.cw;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 凭证应提未提正确性
 * */
public class CertificateDataVerifyJob extends LisJobTemplate {
    public void execute() {
        run("CW",new Long(3));
    }
}
