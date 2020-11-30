# select

mybatis sqlSession——>DefaultSqlSession——>DefaultSqlSession.select——>sql

Spring-mybatis sqlSession——>SqlSessionTemplate(在finally 会close Session 一级缓存会失效)——>SqlSessionTemplate.select——>DefaultSqlSession.select——> sql

```
spring-mybatis为什么要自动关，mybatis不自动关 因为spring-mybatis不好关(没有暴露Session) mybatis容易关
```

# mybatis 初始化

继承自 Spring的 InitializingBean 重写了afterPropertiesSet （在@PostConstruct之后执行）

自动装配三种类型 NO TYPE NAME  默认是NO 加上byType 的话通过set方法，不需要@AutoWired就可以了实现自动绑定 

