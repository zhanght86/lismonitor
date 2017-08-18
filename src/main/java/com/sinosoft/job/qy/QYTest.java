package com.sinosoft.job.qy;

import com.sinosoft.dao.SQLManagerMapper;
import com.sinosoft.service.QueryLisService;
import com.sinosoft.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class QYTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(QYTest.class);

    private QueryLisService queryLisService = SpringUtils.getBean(QueryLisService.class);

    public void doTest() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        System.out.println("doTest 开始跑批： " + time);

        List<LinkedHashMap<String,Object>> lis = queryLisService.queryLis("select name from user");
        LOGGER.info(lis.toString());
    }
}
