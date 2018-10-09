package com.framework.dynamic.boot;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HystrixAop {


    @Before("execution(* com.netflix.hystrix.contrib.javanica.command.MethodExecutionAction.executeWithArgs(..))")
    public void beforeExec(JoinPoint point) {
        System.out.println(point.getArgs());
    }

}
