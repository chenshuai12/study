package com.cs.lambda.custom;

@FunctionalInterface
interface Greet {
    void say(String toWho);

    default void dog(String toWho){
        System.out.print("I am a greet dog: ");
        say(toWho);
    }
    default void cat(String toWho){
        System.out.print("I am a greet cat: ");
        say(toWho);
    }
    static void pig(String toWho){
        System.out.println("I am a greet pig");
    }

    static void duck(String toWho) {
        System.out.println("I am a greet duct");
    }
}
