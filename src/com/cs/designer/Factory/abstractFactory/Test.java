package com.cs.designer.Factory.abstractFactory;

/**
 * 抽象工厂方法模式
 */
public class Test {
    public static void main(String[] args) {
        CatFactory catFactory = new CatFactory();
        AnimalInterface animalInterfaceColor = catFactory.createColor();
        AnimalInterface animalInterfaceVoice = catFactory.createVoice();
        animalInterfaceColor.get();
        animalInterfaceVoice.get();
    }
}
