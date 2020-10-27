package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Lazy loading demo1: 由于反射可以破解单例模式，所以需要在构造方法里加锁；
 * 但还是有漏洞：如果2次都使用反射创建对象，则singletonDemo1会一直等于null (crackDemo2)
 * @author space
 * @date 2020/10/5 11:16
 */
public class SingletonDemo1 {
    private volatile static SingletonDemo1 singletonDemo1;
    private SingletonDemo1(){
        synchronized (SingletonDemo1.class){
            if (singletonDemo1 != null) {
                throw new RuntimeException("do not try to use reflection to crack");
            }
        }
    }

    // double check lazy
    public static SingletonDemo1 getInstance(){
        if(singletonDemo1 == null){
            synchronized (SingletonDemo1.class){
                if(singletonDemo1 == null){
                    singletonDemo1 = new SingletonDemo1();
                }
            }
        }
        return singletonDemo1;
    }

    // demo: use reflection to crack
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        crackDemo1(); //
        crackDemo2();

    }

    /**
     * use reflection to create two instance
     */
    private static void crackDemo2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<SingletonDemo1> demo1Constructor = SingletonDemo1.class.getDeclaredConstructor(null);
        demo1Constructor.setAccessible(true);
        SingletonDemo1 instance1 = demo1Constructor.newInstance();
        SingletonDemo1 instance2 = demo1Constructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2); // we successfully crack the singleton
    }

    /**
     *  first use getInstance() create object, then use reflection
     */
    public static void crackDemo1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingletonDemo1 instance = SingletonDemo1.getInstance();

        Constructor<SingletonDemo1> demo1Constructor = SingletonDemo1.class.getDeclaredConstructor(null);
        demo1Constructor.setAccessible(true);
        SingletonDemo1 instance2 = demo1Constructor.newInstance();
        System.out.println(instance);
        System.out.println(instance2);
    }
}
