package com.codebase.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    // Pointcut targeting all public methods in classes under the controller.*.impl package
    @Pointcut("execution(public * com.codebase.controller.*.impl..*(..))")
    public void allPublicMethodsInControllerImpl() {
        // This method is empty because it serves as a pointcut definition.
    }

    @Before("allPublicMethodsInControllerImpl()")
    public void logBeforeController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        log.info(">> {}.{}() - Args: {}", className, methodName, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "allPublicMethodsInControllerImpl()", returning = "result")
    public void logAfterReturningController(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("<< {}.{}() - Result: {}", className, methodName, result);
    }

    @AfterThrowing(pointcut = "allPublicMethodsInControllerImpl()", throwing = "exception")
    public void logAfterThrowingController(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.error("!! {}.{}() - Exception: {}", className, methodName, exception.getMessage(), exception);
    }
}


