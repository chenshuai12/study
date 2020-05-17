package com.cs.designer.decorator;

/**
 * 组件
 * 浓缩咖啡
 */
public class Espresso extends Beverage{
    public Espresso(){
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
