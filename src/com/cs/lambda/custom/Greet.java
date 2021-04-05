package com.cs.lambda.custom;

@FunctionalInterface
interface Greet {
    void say(String toWho);
    default void dog(String toWho){
        System.out.print("I am a dog: ");
        say(toWho);
    }
    default void cat(String toWho){
        System.out.print("I am a cat: ");
        say(toWho);
    }
}
