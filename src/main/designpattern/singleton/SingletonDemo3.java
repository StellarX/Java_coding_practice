package singleton;

/**
 * 使用静态内部类实现单例
 */
public class SingletonDemo3 {
    private SingletonDemo3(){
    }
    public static SingletonDemo3 getInstance(){
        return InnerClass.single;
    }
    public static class InnerClass{
        private static final SingletonDemo3 single = new SingletonDemo3();
    }
}
