package com.cs.lambda.custom;

public class Child implements Greet,Green {
    @Override
    public void say(String toWho) {
        System.out.println("I am a child");
    }

    @Override
    public void dog(String toWho) {
        Green.super.dog(toWho);
    }

    @Override
    public void cat(String toWho) {
        Greet.super.cat(toWho);
    }
}
