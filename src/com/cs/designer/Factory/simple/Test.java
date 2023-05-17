package com.cs.designer.Factory.simple;

import com.cs.designer.Factory.Animal;

/**
 * 简单工厂模式
 * @Author cs
 */
public class Test {
    public static void main(String[] args) {
        Animal animal = AnimalFactory.createAnimal("dog");
        animal.behavior();
    }
}
