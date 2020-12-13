package functionalinterface;

import functionalinterface.funInterface.FunInterface1;
import functionalinterface.funInterface.impl.FunInterface1Impl;

/**
 * functional interface test demo
 */
public class FunctionalInterfaceDemo {
    public static void test(FunInterface1 funInterface1){
        funInterface1.say();
    }
    public static void main(String[] args) {
        test(new FunInterface1() {
            @Override
            public void say() {
                System.out.println("use new");
            }
        });
        test(new FunInterface1Impl());
        test(() -> {
            System.out.println("use lambda");
        });
        test(() -> System.out.println("use simplified lambda"));
    }
}
