package com.cs.designer.Factory.factoryMethod;

import com.cs.designer.Factory.Animal;

/**
 * 工厂方法模式
 * @author 陈帅
 */
public class Test {
    public static void main(String[] args) {
        CatFactory catFactory = new CatFactory();
        Animal animal = catFactory.createAnimal("胖花儿");
        animal.behavior();
    }
}
