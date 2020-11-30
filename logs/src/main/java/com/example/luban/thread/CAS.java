package com.example.luban.thread;

import com.example.luban.thread.Account;
import com.sun.xml.internal.ws.api.pipe.Fiber;
import javafx.util.Pair;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.security.GuardedObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CAS {

    private static AtomicStampedReference a = new AtomicStampedReference(1,0);
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("线程名字：" + Thread.currentThread() + ", 当前 value = " + a.getReference());
        int stamp = a.getStamp();
        // 计数器阻塞，直到计数器为0，才执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名字：" + Thread.currentThread() + ",CAS操作结果: " + a.compareAndSet(1, 2, stamp, stamp + 1));
    }
}).start();
        // 线程2
        new Thread(() -> {
            // 将 value 值改成 2
            a.compareAndSet(1, 2, a.getStamp(), a.getStamp() + 1);
            System.out.println("线程名字" + Thread.currentThread() + "value = " + a.getReference());
            // 将 value 值又改成 1
            a.compareAndSet(2, 1, a.getStamp(), a.getStamp() + 1);
            System.out.println("线程名字" + Thread.currentThread() + "value = " + a.getReference());
            // 线程计数器
            countDownLatch.countDown();
        }, "线程2").start();

    }


}
