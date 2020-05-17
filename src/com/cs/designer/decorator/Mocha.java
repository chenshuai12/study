package com.cs.designer.decorator;

/**
 * 摩卡咖啡
 * 装饰者
 */
public class Mocha extends CondimentDesorator{
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }


    @Override
    public String getDescription() {
        return beverage.description + ", Mocha";
    }

    @Override
    public double cost() {
        return 3.9 + beverage.cost();
    }
}
