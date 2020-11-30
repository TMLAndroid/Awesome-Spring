package com.example.luban.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorHelper {
    public ExecutorHelper() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Integer> f1 = executor.submit(()->getPriceByS1());
        Future<Integer> f2 = executor.submit(()->getPriceByS2());
        Future<Integer> f3 = executor.submit(()->getPriceByS3());
        executor.execute(()-> {
            try {
                save(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                save(f2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                save(f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        new ExecutorHelper();
    }

    private void save(int num){
        System.out.println("保存："+num);
    }

    private int getPriceByS3() {
        System.out.println("getPriceByS3");
        return 99;
    }

    private int getPriceByS2() {
        System.out.println("getPriceByS2");
        return 101;
    }

    private int getPriceByS1() {

        System.out.println("getPriceByS1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100;
    }
}
