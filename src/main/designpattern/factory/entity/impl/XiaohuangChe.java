package factory.entity.impl;

import factory.entity.Car;

public class XiaohuangChe extends Car {
    @Override
    public void getName(){
        super.name = "xiaohuangche";
        System.out.println(name);
    }
}
