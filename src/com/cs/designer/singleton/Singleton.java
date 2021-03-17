package com.cs.designer.singleton;

public enum Singleton {
    INSTANCE;
    public void doSomething(){
        System.out.println("我是枚举单例");
    }
}
