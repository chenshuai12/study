package com.cs.designer.Factory.abstractFactory;

import com.cs.designer.Factory.simple.Pizza;

public abstract class PizzaStore {
    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza = createPizze(type);
        return pizza;
    }

    protected abstract Pizza createPizze(String type);
}
