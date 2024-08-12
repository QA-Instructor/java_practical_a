package com.qa.optionalmodels;


import java.util.Optional;

public class Car {
    public Car(String make, String model, String colour) {
        this.make = make;
        this.model = model;
        this.colour = colour;
    }

    private String make;
    private String model;
    private String colour;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Optional<Radio> getRadio() {
        return radio;
    }

    public void setRadio(Optional<Radio> radio) {
        this.radio = radio;
    }

    private Optional<Radio> radio;

    public Optional<String> getEngine() {
        return engine;
    }

    public void setEngine(Optional<String> engine) {
        this.engine = engine;
    }

    private Optional<String> engine;

}

