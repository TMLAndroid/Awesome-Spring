package com.example.luban;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.security.GuardedObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class Main {
    public static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        if (log.isDebugEnabled()){
            Logger.getLogger("xxx").info("aa");
        }
        AbstractApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        annotationConfigApplicationContext.start();
//        Logger.getLogger("xxx").info("aa");
        Thread thd1 = new Thread(new Runnable() {
            @Override
            public void run() {
                DateFormat dateFormat = SafeDateFormat.get();
                System.out.println("thd1:"+dateFormat.toString());
                DateFormat dateFormat1 = SafeDateFormat.get();
                System.out.println("thd1:"+dateFormat1.toString());
            }
        });
        Thread thd2 = new Thread(new Runnable() {
            @Override
            public void run() {
                DateFormat dateFormat = SafeDateFormat.get();
                System.out.println("thd2:"+dateFormat.toString());
                DateFormat dateFormat1 = SafeDateFormat.get();
                System.out.println("thd2:"+dateFormat1.toString());
            }
        });
        thd1.setName("AAA");
        thd2.setName("BBB");

        thd1.start();
        thd2.start();


    }

    static class ThreadId {
        static final AtomicLong nextId=new AtomicLong(0); //定义ThreadLocal变量
        static final ThreadLocal<Long> tl=ThreadLocal.withInitial(
                nextId::getAndIncrement); //此方法会为每个线程分配一个唯一的Id static long get(){
        static long get(){
            return tl.get(); } }


    static class SafeDateFormat { //定义ThreadLocal变量
        static final ThreadLocal<DateFormat> tl=ThreadLocal.withInitial(
                ()-> new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"));
        static DateFormat get(){ return tl.get();
        } }
}
