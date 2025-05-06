package org.example.tliasweb.aop;

/* 
    @author nanchao 
    @date 2025/4/24
*/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class RecordTimeAspect {

    private static Logger log= LoggerFactory.getLogger(RecordTimeAspect.class);

    @Around("execution(* org.example.tliasweb.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.记录方法运行的开始时间
        long begin = System.currentTimeMillis();

        //2.执行原始的方法
        Object result = pjp.proceed();

        //3.记录方法运行的结束时间，记录耗时
        long end = System.currentTimeMillis();
        log.info("方法{}耗时{}毫秒", pjp.getSignature(), (end - begin));

        return result;
    }
}
