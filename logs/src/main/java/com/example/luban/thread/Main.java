package com.example.luban.thread;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class Main {


    public static void main(String[] args) {
//        LockSupport.park();
//        LockSupport.unpark();
//        CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();
//        arrayList.add()

      final Account accountA = new Account();
        final Account accountB = new Account();
        final Account accountC = new Account();
        Thread threadA = new Thread(new Runnable() {
            public void run() {

                accountA.transfer(accountB);

            }
        });
        Thread threadB = new Thread(new Runnable() {
            public void run() {

                accountB.transfer(accountC);

            }
        });
        threadA.start();
        threadB.start();
    }

}
