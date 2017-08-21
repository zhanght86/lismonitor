package com.sinosoft.job.lis.bq;


import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 万能结息数据正确性
 * */
public class WNJXDataVerify extends LisJobTemplate {

    public void execute() {
        run("BQ","2");
    }
}
