package com.dabby.tax.config.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName WebApiAspectConfig
 * @Description TODO
 * @Author Aaronchen
 * @Date 2019/3/27 19:27
 **/
@Aspect
@Order(1)
@Component
@Slf4j
public class WebApiAspectConfig {
    @Pointcut("execution(public * com.dabby.tax.BillServiceApplication.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        WebApiInfo.set(joinPoint.getSignature().getName());
        log.info("request data = {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("response data = {}", ret);
        WebApiInfo.remove();
    }
}
