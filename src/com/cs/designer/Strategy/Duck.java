package com.cs.designer.Strategy;

public class Duck {
    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;
    public Duck(QuackBehavior quackBehavior, FlyBehavior flyBehavior){
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }
    public void performQuack(){
        quackBehavior.quack();
    }
    public void perforFly(){
        flyBehavior.fly();
    }
}
