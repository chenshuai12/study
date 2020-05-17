package com.cs.designer.Strategy;

public class test {
    public static void main(String[] args) {
        Duck simpleDuck = new Duck(new QuackWithGua(),new FlyWithWings());
        simpleDuck.performQuack();
        simpleDuck.perforFly();
    }
}
