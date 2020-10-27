package factory.factory.impl;

import factory.entity.Car;
import factory.entity.impl.XiaohuangChe;
import factory.factory.CarFactory;

public class XiaohuangcheFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new XiaohuangChe();
    }
}
