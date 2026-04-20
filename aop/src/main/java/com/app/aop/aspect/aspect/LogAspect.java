package com.app.aop.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LogAspect {

//    @After("@annotation(com.app.aop.aspect.annotation.LogStatus)")
//    public void beforeStart(JoinPoint joinPoint) {
//        log.info("join point: " + joinPoint);       // 조인포인트 객체
//        log.info("join point: " + Arrays.stream(joinPoint.getArgs()).map(String::valueOf).collect(Collectors.joining(". ")));     // 파라미터
//        log.info("join point: " + joinPoint.getSignature().getName());  // 메서드 이름
//        log.info("join point: " + joinPoint.getTarget());   // 타겟
//    }

    //리턴 이후에 실행되는 시점
//    @AfterReturning(value = "@annotation(com.app.aop.aspect.annotation.LogStatus)", returning = "returnValue")
//    public void afterReturning(JoinPoint joinPoint, Integer returnValue) {
//        log.info("join point: " + joinPoint);       // 조인포인트 객체
//        log.info("join point: " + Arrays.stream(joinPoint.getArgs()).map(String::valueOf).collect(Collectors.joining(". ")));     // 파라미터
//        log.info("join point: " + joinPoint.getSignature().getName());  // 메서드 이름
//        log.info("join point: " + joinPoint.getTarget());   // 타겟
//        log.info("return value" + returnValue);
//    }


    // 예외를 잡아서 처리하는 것이 아님.
    // 예외 발생 시 어떻게 처리할 것인가
//
//    @AfterThrowing(value = "@annotation(com.app.aop.aspect.annotation.LogStatus)", throwing = "e")
//    public void afterReturning(JoinPoint joinPoint, Exception e) {
//        log.info(e.getMessage());
//    }

    @Around(value = "@annotation(com.app.aop.aspect.annotation.LogStatus)")
    public Integer around(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("point cut" , proceedingJoinPoint);
        Integer result = 0;

        try {
            result = (Integer) proceedingJoinPoint.proceed();
        } catch (NumberFormatException e) {
            log.info("number format exception");
        } catch (Throwable e){
            throw new RuntimeException();
        }
        return result;
    }
    }

