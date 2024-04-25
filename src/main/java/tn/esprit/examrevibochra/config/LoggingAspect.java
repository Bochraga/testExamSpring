package tn.esprit.examrevibochra.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* tn.esprit.examrevibochra.Services.*.get*(..))")
    public void methodCall(){
    }
    @Before("methodCall()")
    public void LogMethodEntry(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("In method" + name + ":");
        log.info(LocalDate.now().toString());
    }

    @After("methodCall()")
    public void LogMethodExit(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("In method" + name + ":");
    }

    @AfterReturning("methodCall()")
    public void LogMethodExitReturn(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("AfterReturning of method"+name+":");
    }
//
//    @AfterThrowing(pointcut = "methodCall()", throwing = "nameEx")
//    public void LogMethodExitThrowing(JoinPoint joinPoint){
//        String name = joinPoint.getSignature().getName();
//        log.info("AfterThrowing of method"+name+":");
//        log.error(nameEx.g);
//    }
    @Around("execution(* tn.esprit.examrevibochra.Services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start= System.currentTimeMillis();Object obj= pjp.proceed();
        long elapsedTime= System.currentTimeMillis() -start;log.info("Method execution time: "+ elapsedTime+"milliseconds.");
        return obj;
    }

}
