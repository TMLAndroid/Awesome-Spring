package com.luban.app.test;

import java.util.concurrent.locks.*;

public class Test {
    static ReentrantLock reentrantLock = new ReentrantLock(true);
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Condition condition = reentrantLock.newCondition();
        condition.await(); condition.signalAll();
        reentrantLock.lock();
        reentrantLock.unlock();
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.writeLock().lock();
//        t1.start();
//        Thread.sleep(1000L);
//        t2.start();
//        Thread.sleep(3000L);
//        t1.interrupt();
//        LockSupport.unpark(t2);
//        t1.join();
//        t2.join();
        tt();
    }

    private static void tt(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("hhhh");
                    System.out.println(Thread.currentThread().isInterrupted());

                }
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("22222222222222");
        thread.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("111111111111111");
        thread.interrupt();
    }


}
