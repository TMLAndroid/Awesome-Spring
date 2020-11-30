package com.example.luban.thread;

import java.util.ArrayList;
import java.util.List;

public class Allocator {
    private List<Object> als = new ArrayList<Object>();

    private Allocator() {
    }

    private static class Holder{
        static volatile Allocator instance = new Allocator();
    }

    public static Allocator getInstance(){
        return Holder.instance;
    }



    synchronized void apply(Object from, Object to){
        while (als.contains(from) || als.contains(to)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            als.add(from);
            als.add(to);
        }
    }

    synchronized void free(Object from,Object to){
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
