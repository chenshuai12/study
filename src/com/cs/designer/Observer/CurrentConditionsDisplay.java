package com.cs.designer.Observer;


public class CurrentConditionsDisplay implements Observer, DisplayElement  {
    private float temp;
    private float humidity;
    private Subject weatherDate;

    public CurrentConditionsDisplay(Subject weatherDate){
        this.weatherDate = weatherDate;
        weatherDate.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.println("Current conditions: " + temp + "F degress and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }
}
