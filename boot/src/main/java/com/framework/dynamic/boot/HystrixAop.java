package com.framework.dynamic.boot;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order
public class HystrixAop {


    @Before("execution(* com.netflix.hystrix..*.*(..) throws *)")
    //@Before("execution(* com.framework.dynamic.boot.ServiceHiApplication.iService(..))")
    public void beforeExec(JoinPoint point) {
        System.err.println(point.getArgs());
    }

}
