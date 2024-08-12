package com.qa.optionalmodels;

public class Station {

    public Station() {
        this.name = "Unnamed Station";
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                '}';
    }
}
