package org.example.tliasweb.aop;

/* 
    @author nanchao 
    @date 2025/4/26
*/


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@Aspect
@Component
public class MyAspect {
    @Around("execution(* org.example.tliasweb.service.DeptService.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName(); // 获取目标类名
        Signature signature = pjp.getSignature(); // 获取目标方法签名
        String methodName = pjp.getSignature().getName(); // 获取目标方法名
        Object[] args = pjp.getArgs(); // 获取目标方法运行参数
        Object res = pjp.proceed(); // 执行原始方法，获取返回值（环绕通知）
        return res;
    }
}
