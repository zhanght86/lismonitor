package com.sinosoft.service;

import com.sinosoft.dao.LisJobMapper;
import com.sinosoft.domain.LisJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobService {

    public static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    private LisJobMapper lisJobMapper;

    @Autowired
    public void setLisJobMapper(LisJobMapper lisJobMapper) {
        this.lisJobMapper = lisJobMapper;
    }

    /**
     * 查询moduleID下的所有任务
     * */
    public List<LisJob> getJobByModule(String moduleID) {
        List<LisJob> lisJobs = lisJobMapper.getAll();
        Iterator<LisJob> lisJobsIt = lisJobs.iterator();
        List<LisJob> returnLisJobs = new ArrayList<>();
        while (lisJobsIt.hasNext()) {
            LisJob lisJob = lisJobsIt.next();
            //如果传入的模块与循环的一致
            if (moduleID.equals(lisJob.getModuleid())) {
                returnLisJobs.add(lisJob);
            }
        }
        return returnLisJobs;
    }

}
