package factory.factory.impl;

import factory.entity.Car;
import factory.entity.impl.Tesla;
import factory.factory.CarFactory;

public class TeslaFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new Tesla();
    }
}
