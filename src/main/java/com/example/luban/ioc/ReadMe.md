1. AutoWired 默认根据属性名(byName) 再根据ByType 自动绑定(xml)
2. Qualifier 指定名称去获取
3. Scope指定范围 prototype singletion 实现原型可以两种方式
    - @LookUp 抽象方法
    - context.getBean去获取
4. Primary 设计主的实现类
5. 生命周期 @PostConstruct @PreDestroy
6. DependOn 依赖某类先初始化完成
7. 自定义注解
    - @Retention
    - @Target
    - getClass().getAnnotation() 
8. 循环引用