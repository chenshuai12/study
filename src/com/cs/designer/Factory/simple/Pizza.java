package com.cs.designer.Factory.simple;

import java.util.ArrayList;

public abstract class Pizza {
    public String name;
    public String dough;
    public String sauce;
    public ArrayList toppings = new ArrayList();
    public void prepare(){
        System.out.println("Prepare: " + name);
        System.out.println("Tossing dough...");
        System.out.println("Add sauce...");
        System.out.println("Adding toppings: ");
        toppings.forEach(v -> System.out.println(" " + v));
    }
    public void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }
    public void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }
    public void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }
    public String getName(){
        return name;
    }
}
