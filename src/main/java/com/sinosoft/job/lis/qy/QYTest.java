package com.sinosoft.job.lis.qy;

import com.sinosoft.job.lis.LisJobTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QYTest extends LisJobTemplate{


    private static final Logger LOGGER = LoggerFactory.getLogger(QYTest.class);

    public void doTest() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        LOGGER.info("doTest 开始跑批： " + time);

        run("QY","1");
    }
}
