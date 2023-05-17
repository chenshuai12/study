package com.cs.designer.Factory.factoryMethod;


import com.cs.designer.Factory.Animal;
import com.cs.designer.Factory.Dog;

public class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal(String name) {
        Animal animal = new Dog();
        animal.setName(name);
        return animal;
    }
}
