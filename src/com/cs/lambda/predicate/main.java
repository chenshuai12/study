package com.cs.lambda.predicate;

import java.util.function.Predicate;

public class main {
    public static void main(String[] args) {
        Predicate<String> predicate = x -> x.equals("cs");
        System.out.println(predicate.test("cs"));
        System.out.println(predicate.test("Lambda"));
        System.out.println(predicate.negate().test("Lambda"));
        Predicate<String> predicate1 = x -> x.startsWith("sc");
        Predicate<String> predicate2 = x -> x.length() == 2;
        System.out.println(predicate.and(predicate2).test("cs") && predicate1.test("sc"));
    }
}
