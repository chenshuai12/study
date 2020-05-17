package com.cs.designer.order;

public class MyLight implements Light {
    @Override
    public void on() {
        System.out.println("打开我家点灯");
    }
}
