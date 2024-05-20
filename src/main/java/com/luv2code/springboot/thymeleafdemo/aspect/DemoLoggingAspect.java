package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    //setup logger
    private Logger myLogger= Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut(" forControllerPackage() || forServicePackage() ||  forDaoPackage(){}")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint){
        //display method we are calling
        String theMethod=joinPoint.getSignature().toShortString();
        myLogger.info("====>> in @Before: calling method: "+theMethod);


        //display the arguments to the method

        //get the arguments

        //loop through and display args

        Object[] args=joinPoint.getArgs();
        for (Object tempArg:args
             ) {
            myLogger.info("====>>argument: "+tempArg);
        }

    }


    //add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()"
        , returning = "theResult")
    public void afterReturning(JoinPoint joinPoint,Object theResult){
        //display method we are returning from
        String theMethod=joinPoint.getSignature().toShortString();
        myLogger.info("=====>>in @AfterReturning: from method: "+theMethod);

        //display data returned
        myLogger.info("======>>result: "+theResult);



    }

}
