package com.cs.designer.Factory.simple;


import java.util.Objects;

/**
 * 简单工厂方法，可以采用枚举进行改良
 * 缓一缓，工厂模式这几种还是有点蒙
 */
public class SimplePizzFactory {
    public Pizza createPizz(String type){
        Pizza pizz = null;
        if (Objects.equals(type,"cheese")){
//            pizz = new CheesePizz();
        }
        return pizz;
    }
}
