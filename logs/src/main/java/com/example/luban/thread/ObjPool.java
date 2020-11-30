package com.example.luban.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 16. Semaphore 信号量线程池
 * @param <T>
 * @param <R>
 */
public class ObjPool<T,R> {
    final List<T> pool;
    final Semaphore sem;

    public ObjPool(int size,T t) {
        pool = new Vector<T>(){};
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    R exec(Function<T,R> func){
        T t = null;
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            t = pool.remove(0);
            return func.apply(t);
        }finally {
            pool.add(t);
            sem.release();
        }
    }

    public static void main(String[] args) {
        ObjPool<Long,String> pool = new ObjPool<Long,String>(10,2L);
        pool.exec(aLong -> {
            System.out.println(aLong);
            return aLong.toString();
        });


    }
}
