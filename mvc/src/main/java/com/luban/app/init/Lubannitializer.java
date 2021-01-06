package com.luban.app.init;

import com.luban.app.init.inter.TestInter;
import com.luban.app.web.SpringServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;
@HandlesTypes(TestInter.class)
public class Lubannitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("hahhahah:"+c);
        ServletRegistration.Dynamic registration=ctx.addServlet("xx",new SpringServlet());
        registration.addMapping("*.do");
        registration.setLoadOnStartup(1);
    }
}
