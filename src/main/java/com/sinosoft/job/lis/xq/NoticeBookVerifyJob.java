package com.sinosoft.job.lis.xq;

import com.sinosoft.job.lis.LisJobTemplate;

/**
 * 三合一通知书应生成未生成校验
 * */
public class NoticeBookVerifyJob extends LisJobTemplate {
    public void execute() {
        run("XQ","3");
    }
}
