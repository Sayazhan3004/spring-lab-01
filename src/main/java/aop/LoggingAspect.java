package kz.iitu.springlab.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* kz.iitu.springlab.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(
                "[AOP BEFORE] Method: " +
                        joinPoint.getSignature().getName() +
                        ", args: " +
                        Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(
            pointcut = "execution(* kz.iitu.springlab.service.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println(
                "[AOP AFTER] Method: " +
                        joinPoint.getSignature().getName() +
                        ", result: " +
                        result
        );
    }
}