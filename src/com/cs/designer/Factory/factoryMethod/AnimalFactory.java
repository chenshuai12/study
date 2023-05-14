package com.cs.designer.Factory.factoryMethod;

import com.cs.designer.Factory.Animal;

public abstract class AnimalFactory {
    // 工厂方法
    public abstract Animal createAnimal(String name);
}
