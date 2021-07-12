package com.space.functionalinterface.funInterface;

@FunctionalInterface
public interface FunInterface1 {
    void say();//函数式接口有且仅有一个抽象方法

    default void aaa() {

    }

    static void bbb() {

    }
}
