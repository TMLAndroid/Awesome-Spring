  动态代理
    类的类对象
     byte 转换成Class native
      byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                    proxyName, interfaces, accessFlags);
                try {
                    return defineClass0(loader, proxyName,
                                        proxyClassFile, 0, proxyClassFile.length);
                } catch (ClassFormatError e) {                    
                    
  private static native Class<?> defineClass0(ClassLoader loader, String name,
                                                                    byte[] b, int off, int len);
    通过接口反射到字节码 native把字节码转换成Class
    asm cglib
    
    
    
    