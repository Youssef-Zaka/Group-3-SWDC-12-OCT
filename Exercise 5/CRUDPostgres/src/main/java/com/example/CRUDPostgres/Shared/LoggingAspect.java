package com.example.CRUDPostgres.Shared;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect is responsible for logging method entry, exit, exceptions,
 * and the duration of method execution using AOP with colored output.
 *
 * This aspect targets all methods in the services package of the application.
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // ANSI color codes
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Logs the execution time of methods in the services package.
     *
     * @param joinPoint Represents the target method being intercepted.
     * @return The result of the target method execution.
     * @throws Throwable If any error occurs during the method execution.
     */
    @Around("execution(* com.example.CRUDPostgres.*.services.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Record the start time before the method executes
        long startTime = System.currentTimeMillis();

        // Proceed with the actual method execution
        Object result = joinPoint.proceed();

        // Calculate the method's execution duration
        long duration = System.currentTimeMillis() - startTime;

        // Log the method name and its execution time in blue
        String methodName = joinPoint.getSignature().toShortString();
        log.info(ANSI_BLUE + "Method {} executed in {} ms" + ANSI_RESET, methodName, duration);

        return result;
    }

    /**
     * Logs before the execution of methods in the services package.
     *
     * @param joinPoint Represents the target method being intercepted.
     */
    @Before("execution(* com.example.CRUDPostgres.*.services.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info(ANSI_GREEN + "Entering method: {}" + ANSI_RESET, joinPoint.getSignature().getName());
    }

    /**
     * Logs after the successful execution of methods in the services package.
     *
     * @param joinPoint Represents the target method being intercepted.
     * @param result The return value of the target method.
     */
    @AfterReturning(value = "execution(* com.example.CRUDPostgres.*.services.*.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        log.info(ANSI_YELLOW + "Method {} completed with result: {}" + ANSI_RESET, joinPoint.getSignature().getName(), result);
    }

    /**
     * Logs when a method in the services package throws an exception.
     *
     * @param joinPoint Represents the target method being intercepted.
     * @param exception The exception thrown by the target method.
     */
    @AfterThrowing(value = "execution(* com.example.CRUDPostgres.*.services.*.*(..))", throwing = "exception")
    public void logAfterException(JoinPoint joinPoint, Throwable exception) {
        log.error(ANSI_RED + "Method {} threw an exception: {}" + ANSI_RESET, joinPoint.getSignature().getName(), exception.getMessage());
    }
}
