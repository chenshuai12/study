package com.cs.lambda.function;

import java.util.function.Function;

public class main {


    public static void main(String[] args) {
        applyTest();
        andThenTest();
        composeTest();
        test();
    }
    /**
     * apply示例
     */
    private static void applyTest(){
        /**
         * 示例1：定义一个function，实现将String转化为Integer
         */
        Function<String,Integer> function = x -> Integer.parseInt(x);
        Integer a = function.apply("100");
        System.out.println(a + ":" + a.getClass());
    }

    /**
     * andThen示例
     */
    private static void andThenTest(){
        /**
         * 示例2：使用andThen()实现一个函数y = 10X + 10
         */
        Function<Integer,Integer> function = x -> x*10;
        function = function.andThen(x -> x + 10);
        System.out.println(function.apply(2));
    }

    /**
     * compose示例
     */
    private static void composeTest(){
        /**
         * 实现函数 y = （10+x）2；
         */
        Function<Integer,Integer> function = x -> x*2;
        function = function.compose(x -> 10 + x);
        System.out.println(function.apply(3));
    }

    private static void test(){
        /**
         * 实现y = (10+x)*2+10；
         */
        Function<Integer,Integer> function = x -> x*2;
        function = function.compose(x -> x+10);
        function = function.andThen(x -> x + 10);
        System.out.println(function.apply(3));
    }
}
