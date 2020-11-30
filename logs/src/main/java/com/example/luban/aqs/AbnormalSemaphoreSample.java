package com.example.luban.aqs;

import java.util.concurrent.Semaphore;
public class AbnormalSemaphoreSample {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for(int i=0;i<10;i++){
        Thread t = new Thread(new MyWorker(semaphore));
        t.start();
        }
        System.out.println("Action...GO!");
        semaphore.release(5);
        System.out.println("Wait for permits of");
        while (semaphore.availablePermits()!=0) {
            Thread.sleep(100L);
        }
        System.out.println("Action...GO again!");
        semaphore.release(5); }
}
