package kz.iitu.springlab.aop;

import kz.iitu.springlab.annotation.Audited;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class AuditAspect {

    @Around("@annotation(audited)")
    public Object audit(ProceedingJoinPoint joinPoint, Audited audited) throws Throwable {

        System.out.println(
                "[AUDIT] start " + audited.action() +
                        " time=" + LocalDateTime.now()
        );

        if (audited.logArguments()) {
            System.out.println(
                    "[AUDIT] args=" + Arrays.toString(joinPoint.getArgs())
            );
        }

        try {
            Object result = joinPoint.proceed();

            System.out.println(
                    "[AUDIT] " + audited.action() + " success"
            );

            return result;

        } catch (Throwable ex) {

            System.out.println(
                    "[AUDIT] " + audited.action() +
                            " failure: " + ex.getMessage()
            );

            throw ex;
        }
    }
}