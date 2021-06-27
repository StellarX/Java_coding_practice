package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ö�ٱ�����ǵ�����  demo�����ƽ�ö�ٵ���
 * todo �˽�ö�ٵ���ģʽ https://blog.csdn.net/weixin_36586120/article/details/105522491
 * @author space
 * @date 2020/10/5 16:10
 */
public enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        EnumSingle instance2 = EnumSingle.INSTANCE;
        System.out.println(instance1);
        System.out.println(instance2);
        tryToCrack();
    }

    /**
     * try to crack enum singleton using reflection
     */
    public static void tryToCrack() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class
                .getDeclaredConstructor(String.class, int.class); // �вι���
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);// java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        System.out.println(instance2);
    }
}
