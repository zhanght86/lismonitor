package com.sinosoft.aop;


import com.sinosoft.datasource.DataSourceTypeManager;
import com.sinosoft.datasource.DataSources;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0) // execute before @Transactional
public class DataSourceInterceptor {

    @Pointcut("execution(public * com.sinosoft.service.QueryLisService.*(..))")
    public void dataSourceLIS(){}


    @Before("dataSourceLIS()")
    public void before(JoinPoint jp) {
        DataSourceTypeManager.set(DataSources.LIS);
    }
}
