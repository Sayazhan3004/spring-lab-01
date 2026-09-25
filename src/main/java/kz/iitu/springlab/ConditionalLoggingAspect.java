package kz.iitu.springlab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ConditionalLoggingAspect {

    @Value("${app.aspect.verbose:false}")
    private boolean verbose;

    @Around("execution(public * kz.iitu.springlab.service..*(..))")
    public Object conditionalLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();

        if (verbose) {
            System.out.println(
                    "[CONDITIONAL-LOG] VERBOSE -> method=" + methodName +
                            ", args=" + Arrays.toString(joinPoint.getArgs())
            );
        } else {
            System.out.println(
                    "[CONDITIONAL-LOG] BRIEF -> " + methodName
            );
        }

        Object result = joinPoint.proceed();

        if (verbose) {
            System.out.println(
                    "[CONDITIONAL-LOG] VERBOSE <- method=" + methodName +
                            ", result=" + result
            );
        }

        return result;
    }
}