package space.functionalinterface;

import space.functionalinterface.funInterface.FunInterface1;
import space.functionalinterface.funInterface.impl.FunInterface1Impl;

/**
 * functional interface test demo
 */
public class FunctionalInterfaceDemo {
    public static void test(FunInterface1 funInterface1){
        funInterface1.say();
    }
    public static void main(String[] args) {
        //1. 参数：传函数式接口的实现类
        test(new FunInterface1Impl());

        //2. 参数：new一个实现类（不使用lambda）
        test(new FunInterface1() {
            @Override
            public void say() {
                System.out.println("use new");
                System.out.println("use new...");
            }
        });

        //3. 参数：new一个实现类（使用lambda）
        test(() -> {
            System.out.println("use lambda");
            System.out.println("use lambda...");
        });

        //4. 参数：使用简化的lambda(只适用于只有一条语句的情况)
        test(() -> System.out.println("use simplified lambda"));
    }
}
