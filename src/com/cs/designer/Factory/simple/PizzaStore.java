package com.cs.designer.Factory.simple;

public class PizzaStore {
    SimplePizzFactory factory;
    public PizzaStore(SimplePizzFactory factory){
        this.factory = factory;
    }

    public Pizza orderPizza(String type){
        Pizza pizz;
        pizz = factory.createPizz(type);
        return pizz;
    }
}
