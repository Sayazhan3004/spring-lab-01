package kz.iitu.springlab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimingAspect {

    @Around("execution(* kz.iitu.springlab.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            long end = System.nanoTime();
            long duration = end - start;

            System.out.println(
                    "[AOP TIMING] Method: " +
                            joinPoint.getSignature().getName() +
                            ", execution time: " +
                            duration + " ns"
            );
        }
    }
}