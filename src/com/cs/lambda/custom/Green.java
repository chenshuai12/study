package com.cs.lambda.custom;



@FunctionalInterface
public interface Green {

    void say(String toWho);

    default void dog(String toWho){
        System.out.print("I am a green dog: ");
        say(toWho);
    }
    default void cat(String toWho){
        System.out.print("I am a green cat: ");
        say(toWho);
    }
    static void pig(String toWho){
        System.out.println("I am a green pig");
    }

    static void duck(String toWho) {
        System.out.println("I am a green duct");
    }

}
