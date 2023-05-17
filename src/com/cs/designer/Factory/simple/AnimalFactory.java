package com.cs.designer.Factory.simple;

import com.cs.designer.Factory.Animal;
import com.cs.designer.Factory.Cat;
import com.cs.designer.Factory.Dog;

public class AnimalFactory {
    public static Animal createAnimal(String name){
        Animal animal = null;
        if ("cat".equals(name)){
            animal = new Cat();
            animal.setName(name);
        }
        if ("dog".equals(name)){
            animal = new Dog();
            animal.setName(name);
        }
        return animal;
    }
}
