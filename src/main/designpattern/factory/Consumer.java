package factory;

import factory.factory.impl.TeslaFactory;
import factory.entity.Car;
import factory.factory.impl.XiaohuangcheFactory;

public class Consumer {
    public static void main(String[] args) {
        Car tesla = new TeslaFactory().getCar();
        tesla.getName();
        Car xiaohuang = new XiaohuangcheFactory().getCar();
        xiaohuang.getName();
    }
}
