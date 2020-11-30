package com.example.luban.aop.app;

import com.example.luban.aop.dao.Dao;
import com.example.luban.aop.dao.IndexDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Aspect("perthis(this(com.example.luban.aop.dao.IndexDao))")//指定切面是原型  EnableAspectJAutoProxy(proxyTargetClass = true) 开启
@Scope("prototype")
public class AspectJLuBan {



    @DeclareParents(value = "com.example.luban.aop.dao.*",defaultImpl = IndexDao.class)
    Dao dao;

    @Pointcut("within(com.example.luban.aop.dao.*)")
    public void cutDaoWithIn(){

    }

    @Pointcut("execution(private * query(..))")
    public void cutDaoExecution(){
    }

    @Before("cutDaoWithIn()")
    public void beforeCutDao(JoinPoint joinPoint){
        System.out.println("before: "+this.hashCode());

    }

    @After("cutDaoWithIn()")
    public void afterCutDao(JoinPoint joinPoint){
        System.out.println("after");
    }

    @Around("cutDaoExecution()")
    public void aroundCutDao(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("B");
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            args[i] += "add";
        }
        try {
            proceedingJoinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("A");
    }

}
