package com.cs.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class failFast {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = Collections.synchronizedList(list);
        synchronized (list2){
            /**
             * 遍历读取
             */
        }
    }
}
