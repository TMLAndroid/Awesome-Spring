package com.luban.config;

import com.luban.plugin.MyPagePlugin;
import com.luban.register.LubanScanRegister;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.luban")
@MapperScan("com.luban.dao")
@Import(LubanScanRegister.class)
public class AppConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUsername("caizhigui");
        driverManagerDataSource.setPassword("czg@123A");
        driverManagerDataSource.setUrl("jdbc:mysql://47.98.174.139:3306/guns");
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("123");
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        return driverManagerDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        //一级缓存基于 SqlSessionFactoryBean 查询之后Spring就关闭了Session 就失效了
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //相当于 mybatis-config.xml 配置 打印mybatis日志
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(Log4jImpl.class);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setPlugins(new Interceptor[] {getMyPagePlugin()});
        return sqlSessionFactoryBean;
    }

    public Interceptor getMyPagePlugin(){
        MyPagePlugin myPagePlugin = new MyPagePlugin();
        myPagePlugin.setDatabaseType("mysql");
        myPagePlugin.setPageSqlId(".*ByPage$");
        return  myPagePlugin;
    }
}
