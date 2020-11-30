package com.example.luban.aqs;

import java.util.concurrent.CountDownLatch;
public class LatchSample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for(int i=0;i<5;i++){
            Thread t = new Thread(new FirsBatchWorker(latch));
            t.start();
        }
        for(int i=0;i<5;i++){
            Thread t = new Thread(new SecondBatchWorker(latch));
            t.start();
        }
// 注意这里也是演示目的的逻辑，并不是推荐的协调方式
     while ( latch.getCount() != 1 ){
         System.out.println("数量："+latch.getCount());
            Thread.sleep(100L);
     }
    System.out.println("Wait for frs batch fnish");
    latch.countDown();
}
static class FirsBatchWorker implements Runnable {
    private CountDownLatch latch;
    public FirsBatchWorker(CountDownLatch latch) {
        this.latch = latch; }
    @Override
    public void run() {
        System.out.println("Firs batch executed!");
        latch.countDown(); }
}
static class SecondBatchWorker implements Runnable {
    private CountDownLatch latch;
    public SecondBatchWorker(CountDownLatch latch) {
        this.latch = latch; }
    @Override
    public void run() {
        try { latch.await();
            System.out.println("Second batch executed!"); } catch (InterruptedException e) {
            e.printStackTrace(); }
    } }
}