package com.cs.lambda.supplier;

import java.util.Optional;
import java.util.function.Supplier;

public class main {
    public static void main(String[] args) {
        String s = null;
        String s1 = Optional.ofNullable(s).orElseGet(() ->"hello world");
        System.out.println(s1);

        Supplier supplier = () -> "hello world";
        String s2 = (String) supplier.get();
        System.out.println(s2);
    }
}
