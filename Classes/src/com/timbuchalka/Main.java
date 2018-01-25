package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        Car porsche = new Car();
        Car holden = new Car();
        porsche.setColour("Red");
        System.out.println("Car's colour is "+ porsche.getColour());
    }
}
