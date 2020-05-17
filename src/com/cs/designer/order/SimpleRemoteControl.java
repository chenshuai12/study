package com.cs.designer.order;

public class SimpleRemoteControl {
    Command command;
    public SimpleRemoteControl(){
    }
    public void setCommand(Command command){
        this.command = command;
    }
    public void buttonWasPressed(){
        command.execute();
    }
}
