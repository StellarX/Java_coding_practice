package com.space.config;

import com.space.pojo.MyData;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author space
 * @Description 模拟框架，使用反射技术创建任意类的对象，并执行其中的方法
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        loadClass();
    }

    private static void loadClass() {
        //1.加载配置文件
        //1.1创建Properties对象
        Properties properties = new Properties();
        //1.2加载配置文件，转换为一个集合
        //1.2.1获取class目录下的配置文件
        ClassLoader classLoader = MyData.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("application.yml");

        try {
            properties.load(is);

            //2.获取配置文件中定义的数据
            String className = properties.getProperty("className");
            String methodName = properties.getProperty("methodName");

            //3.加载该类进内存
            Class<?> cls = Class.forName(className);
            //4.创建对象
            Object obj = cls.newInstance();
            //5.获取方法对象
            Method method = cls.getMethod(methodName);
            //6.执行方法
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
