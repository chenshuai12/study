package com.cs.designer.order;

public class test {
    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        simpleRemoteControl.setCommand(new LightOnCommand(new MyLight()));

        simpleRemoteControl.buttonWasPressed();
    }
}
