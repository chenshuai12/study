package com.cs.designer.Strategy;

public class QuackWithGua implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
