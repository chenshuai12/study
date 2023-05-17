package com.cs.lambda.custom;

public class main {

    public static void main(String[] args) {
        Integer age = 2;
        Greet greet = x -> {
            System.out.println(x);
            System.out.println(age);
        };
        greet.say("hello world");
        greet.dog("hello world");
        greet.cat("hello world");
        Greet.pig("hello world");
        Greet.duck("Hello world");

        Child child = new Child();
        child.say("hello world");
        child.cat("hello world");
        child.dog("hello world");


        Red red = new Red() {
            @Override
            public void say(String toWho) {
                System.out.println("Hello");
            }

            @Override
            public void go(String toWho) {
                System.out.println("world");
            }
        };
        red.say("ss");
        red.go("ss");

    }
}
