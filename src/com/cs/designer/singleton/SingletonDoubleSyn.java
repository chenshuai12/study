package com.cs.designer.singleton;

import java.util.Objects;

/**
 * 双重锁方式
 */
public class SingletonDoubleSyn {
    private volatile static SingletonDoubleSyn singletonDoubleSyn;

    private SingletonDoubleSyn() {
    }

    public static SingletonDoubleSyn getInstance() {
        if (Objects.isNull(singletonDoubleSyn)) {
            synchronized (SingletonDoubleSyn.class){
                if (Objects.isNull(singletonDoubleSyn)){
                    singletonDoubleSyn = new SingletonDoubleSyn();
                }
            }
        }
        return singletonDoubleSyn;
    }
}

