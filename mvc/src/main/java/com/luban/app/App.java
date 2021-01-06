package com.luban.app;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) throws Exception{
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8070);
        //只会初始化一个context资源目录 并不会去加载web生命周期
        //目前无法加载静态资源 第二个参数
        Context context = tomcat.addContext("/", System.getProperty("java.io.tmpdir"));
//        tomcat.addWebapp("webapp绝对地址");
        context.addLifecycleListener((LifecycleListener) Class.forName(tomcat.getHost().getConfigClass()).newInstance());
        tomcat.start();
        tomcat.getServer().await();

    }
}
