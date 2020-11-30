package com.example.luban.ioc.config;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 不具备顺序解析
 * 多属性支持 eg scope
 */

/**
 * 支持自动绑定 byType  getFields只能获取 public属性
 * 如果byType实现有两个 抛出异常
 * 支持property
 * 支持 construction
 *
 */
public class BeanFactory {

    Map<String,Object> map = new HashMap<String, Object>();
    public BeanFactory(String xmlPath) {
        parseXML(xmlPath);
    }

    private void parseXML(String xmlPath) {
        Document document = parse(xmlPath);
        Element root = document.getRootElement();
        Attribute defaultAutoWire = root.attribute("default-autowire");
        for (Iterator<Element> it = root.elementIterator("bean"); it.hasNext();) {
            Element firstElement = it.next();
            String id = firstElement.attribute("id").getValue();
            String className = firstElement.attribute("class").getValue();
            Object instance = null;
            Class<?> aClass = null;
            try {
                 aClass = Class.forName(className);

            for (Iterator<Element> its = firstElement.elementIterator(); its.hasNext();){

                    Element secondElement = its.next();

                    if (secondElement.getName().equals("property")) {//set方法
                        String propertyName = secondElement.attribute("name").getValue();
                        String injectValue = secondElement.attribute("ref").getValue();
                        Field field = aClass.getDeclaredField(propertyName);
                        field.setAccessible(true);
                        instance = aClass.newInstance();
                        field.set(instance,map.get(injectValue));
                    }else if (secondElement.getName().equals("constructor-arg")){//通过构造函数
                        String refValue = secondElement.attribute("ref").getValue();
                        Object o = map.get(refValue);
                        Constructor<?> constructor = aClass.getConstructor(o.getClass().getInterfaces()[0]);
                        instance = constructor.newInstance(o);
                    }
            }

            if (defaultAutoWire != null && instance == null){
                String value = defaultAutoWire.getValue();
                if (value.equals("byType")){
                    instance= aClass.newInstance();
                    Field[] fields = aClass.getFields();
                    for (Field field : fields) {
                        int count = 0;
                        Object o = null;
                        for (String key : map.keySet()){
                            String name = map.get(key).getClass().getInterfaces()[0].getName();
                            if (field.getType().getName().equals(name)){
                                 o = map.get(key);
                                count++;

                            }
                        }
                        if (count > 1){
                            throw new LuBanSpringException("except 1 but find "+count);
                        }else{
                            field.setAccessible(true);
                            field.set(instance,o);
                        }

                    }
                }
            }

            if (instance == null){
                instance = aClass.newInstance();
            }

            map.put(id,instance);

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    public Object getBean(String name){
        return map.get(name);
    }

    public Document parse(String xmlPath)  {
        String localPath = this.getClass().getResource("/").getPath()  + xmlPath;
        File file = new File(localPath);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            return document;

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
