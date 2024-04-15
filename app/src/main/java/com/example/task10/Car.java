package com.example.task10;

public class Car {

    private String number;
    private String mark;
    private String model;
    private String color;

    public Car(String number, String mark, String model, String color) {
        this.number = number;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
