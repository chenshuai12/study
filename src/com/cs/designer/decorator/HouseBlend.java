package com.cs.designer.decorator;

/**
 * 星巴兹咖啡
 */
public class HouseBlend extends Beverage{
    public HouseBlend(){
        description = "House Blend Coffee";
    }


    @Override
    public double cost() {
        return 2.0;
    }
}
