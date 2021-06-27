package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Lazy loading demo2: ���demo1��©�������һ�����;  �������ֿ���ͨ������ȥ�޸�������
 * @author space
 * @date 2020/10/5 12:51
 */
public class SingletonDemo2 {
    private volatile static SingletonDemo2 singletonDemo2;
    private static boolean flag = false;

    private SingletonDemo2(){
        if(flag == false) flag = true;
        else{
            throw new RuntimeException("do not try to use reflection to crack");
        }
    }
    public static SingletonDemo2 getInstance(){
        if(singletonDemo2 == null){
            synchronized (SingletonDemo2.class){
                if(singletonDemo2 == null){
                    singletonDemo2 = new SingletonDemo2();
                }
            }
        }
        return singletonDemo2;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        Constructor<SingletonDemo2> demo1Constructor = SingletonDemo2.class.getDeclaredConstructor(null);
//        demo1Constructor.setAccessible(true);
//        SingletonDemo2 instance1 = demo1Constructor.newInstance();
//        SingletonDemo2 instance2 = demo1Constructor.newInstance();
//        System.out.println(instance1);
//        System.out.println(instance2); // exception

        crack();
    }

    /**
     * ͨ������ȥ�޸ı���ֵ
     */
    public static void crack() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<SingletonDemo2> demo1Constructor = SingletonDemo2.class.getDeclaredConstructor(null);
        demo1Constructor.setAccessible(true);
        SingletonDemo2 instance1 = demo1Constructor.newInstance();

        Field flag = SingletonDemo2.class.getDeclaredField("flag"); // use reflection change value of flag
        flag.setAccessible(true);
        flag.set(instance1, false);
        SingletonDemo2 instance2 = demo1Constructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2); // we successfully crack the singleton
    }
}
