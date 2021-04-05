package com.cs.lambda.custom;

public class main {

    public static void main(String[] args) {
        Greet greet = x -> System.out.println(x);
//        greet.say("hello world");
        greet.dog("hello world");
        greet.cat("hello world");
    }
}
