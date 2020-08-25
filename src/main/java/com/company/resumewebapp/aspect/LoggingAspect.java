package com.company.resumewebapp.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect  // yeni qiragdan nese etmek isteyiremse
@Component    /// bunuda veririk ki Spring boot bunu ozu handle edib idare ede bilsin
public class LoggingAspect {

    @Before("execution (* com.company.resumewebapp.service.UserServiceInter.getAll())")
    public void logBefore(JoinPoint joinPoint) {    // joinPoint le eslinde her seyi goturmek olar

        System.out.println("logBefore() is running! ");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println(" ******* ");
    }

    @After("execution(* com.company.resumewebapp.service.UserServiceInter.getAll())")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("logAfter() is running! ");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println(" ******* ");
    }

    @AfterReturning(   // bu metod logArounddan sonra ise dusurve geriye deyer donderen bir metoddur
            pointcut = "execution(* com.company.resumewebapp.service.UserServiceInter.getAll())",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("logAfterReturning() is running! ");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println(" ******* ");
    }

    /*@AfterThrowing(
            pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCuxtomerThrowException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running! ");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println(" ******* ");
    }*/

    @Around("execution (* com.company.resumewebapp.service.UserServiceInter.getAll())")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("logAround() is running! ");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println(" Around before is running! ");
        Object res = joinPoint.proceed();
        System.out.println(" Around after is running! ");
        System.out.println(" ******* ");

        return res;
    }

}
