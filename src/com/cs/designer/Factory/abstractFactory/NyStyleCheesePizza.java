package com.cs.designer.Factory.abstractFactory;

import com.cs.designer.Factory.simple.Pizza;

public class NyStyleCheesePizza extends Pizza {
    public NyStyleCheesePizza(){
        name = "NY Style Sauce and cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Grated Reggiano Cheese");
    }
}
