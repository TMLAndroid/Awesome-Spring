
# spring 5新特性（日志）
1. jcl:相对于一个代理类 ，本身内部不包含打印日志逻辑 根据依赖包决定打印
 log4j>jul
2. slf4j 也是相当于一个代理 本身不打印日志逻辑 根据本生内的依赖包决定打印
4. spring 4 依赖了jcl(commons-logging.jar),依赖了log4j 就打印log4j
5.spring 5 依赖了spring-jcl(改了commons-logging.jar代码) 默认依赖的是log4j2(apach)
log4j2->jul

# myBatis日志
1. SqlSessionFactoryBean.setConfiguration传入Configuration （通过mybatis-config打开日志(不建议)）
2. mybatis 缓存 一级缓存不生效(因为spring执行sql语句会把session关闭)，二级缓存@CacheNameSpace 
要需要同一命名空间才可以生效，不然会出现更新之后，查询出来的是旧数据
