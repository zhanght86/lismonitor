package com.sinosoft.job.lis.qy;

import com.sinosoft.job.lis.LisJobTemplate;
import com.sinosoft.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QYTest extends LisJobTemplate{


    private static final Logger LOGGER = LoggerFactory.getLogger(QYTest.class);

    public void doTest() {
        LOGGER.info("doTest 开始跑批： " + DateUtils.getCurrentDateTime());
        run("QY",1L);
    }
}
