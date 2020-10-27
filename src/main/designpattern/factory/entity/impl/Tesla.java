package factory.entity.impl;

import factory.entity.Car;

public class Tesla extends Car {
    @Override
    public void getName(){
        this.name = "tesla";
        System.out.println(name);
    }
}
