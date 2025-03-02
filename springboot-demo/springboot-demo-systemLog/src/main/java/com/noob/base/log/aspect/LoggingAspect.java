package com.noob.base.log.aspect;

import com.noob.base.log.dao.LogEntryRepository;
import com.noob.base.log.model.LogEntry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private LogEntryRepository logEntryRepository;

    @Pointcut("@annotation(com.noob.base.log.anno.Loggable)")
    public void loggableMethods() {}

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String parameters = Arrays.toString(joinPoint.getArgs());

        LogEntry logEntry = new LogEntry();
        logEntry.setMethodName(methodName);
        logEntry.setParameters(parameters);
        logEntry.setResult(result != null ? result.toString() : "null");
        logEntry.setTimestamp(new Date());

        logEntryRepository.save(logEntry);
    }
}