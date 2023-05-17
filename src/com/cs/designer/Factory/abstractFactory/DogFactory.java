package com.cs.designer.Factory.abstractFactory;

public class DogFactory implements AnimalIntertfaceFactory{
    @Override
    public AnimalInterface createColor() {
        return new DogColor();
    }

    @Override
    public AnimalInterface createVoice() {
        return new DogVoice();
    }
}
