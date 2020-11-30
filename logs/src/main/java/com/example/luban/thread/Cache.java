package com.example.luban.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K,V> {
    final Map<K,V> m = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock r =rwl.readLock();
    final  Lock w = rwl.writeLock();
    V get(K key){
        V v =null;
        r.lock();
        try{
            v = m.get(key);
        }finally {
            r.unlock();
        }
        if (v != null){
            return v;
        }

        w.lock();
        try {
            v = m.get(key);
            if (v == null){
                //查询数据库
                m.put(key,v);
            }
        }finally {
            w.unlock();
        }
        return v;
    }
}
