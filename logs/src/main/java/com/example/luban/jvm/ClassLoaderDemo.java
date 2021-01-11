package com.example.luban.jvm;

import com.sun.crypto.provider.DESKeyFactory;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class ClassLoaderDemo {
    public static void main(String[] args) {

        System.out.println(String.class.getClassLoader());//null BootStrap ClassLoader
        System.out.println(DESKeyFactory.class.getClassLoader().getClass().getName());//sun.misc.Launcher$ExtClassLoader
        System.out.println(ClassLoaderDemo.class.getClassLoader().getClass().getName());//sun.misc.Launcher$AppClassLoader
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());//sun.misc.Launcher$AppClassLoader
//        Consumer consumer = (t) -> {
//            System.out.println(t);
//        };
        BiConsumer consumer1 = (t,d) -> {
            System.out.println(t);
        };
        Consumer consumer = System.out::println;//一个语句,一个参数 实例对象::方法名
//        Function<Long,Long> f = (t)->Math.abs(t);
        Function<Long,Long> f = Math::abs; //类名::静态方法名
        f.apply(1000L);
        //BiPredicate<String,String> predicate = (x,y)->x.equals(y);
        BiPredicate<String,String> predicate = String::equals;//类名::方法名  传入是值是String的实例

        //Function<Integer,StringBuffer> function = (t)->new StringBuffer(t);
        Function<Integer,StringBuffer> function = StringBuffer::new;//构造函数
        StringBuffer apply = function.apply(11);


    }

    protected static void test(Consumer consumer){
        consumer.accept("a");
    }

    protected static void test1(String s){
        Optional<String> optional = Optional.of(s);//避免空指针 减少if else判断
        String s1 = Optional.ofNullable(s).orElse("123");
        String s2 = optional.orElse("aaa");
        //optional.orElseThrow(()-> {throw new NullPointerException();});
    }
}
