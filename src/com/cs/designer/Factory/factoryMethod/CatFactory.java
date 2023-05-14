package com.cs.designer.Factory.factoryMethod;

import com.cs.designer.Factory.Animal;
import com.cs.designer.Factory.Cat;

public class CatFactory extends AnimalFactory{
    @Override
    public Animal createAnimal(String name) {
        Animal animal = new Cat();
        animal.setName(name);
        return animal;
    }
}
