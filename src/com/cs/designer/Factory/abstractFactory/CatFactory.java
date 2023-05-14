package com.cs.designer.Factory.abstractFactory;

public class CatFactory implements AnimalIntertfaceFactory{
    @Override
    public AnimalInterface createColor() {
        return new CatColor();
    }

    @Override
    public AnimalInterface createVoice() {
        return new CatVoice();
    }
}
