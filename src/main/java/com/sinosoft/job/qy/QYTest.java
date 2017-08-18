package com.sinosoft.job.qy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QYTest {

    public void doTest() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        System.out.println("doTest 开始跑批： " + time);
    }
}
