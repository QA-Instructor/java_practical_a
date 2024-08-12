package com.qa.optionalmodels;


import java.util.Optional;

public class Radio {

    public Radio() {
        this.setType("Factory-fitted radio");
    }

    public Radio(String type) {
        this.type = type;
    }

    public Optional<Station> getStation() {
        return station;
    }

    public void setStation(Optional<Station> station) {
        this.station = station;
    }

    private Optional<Station> station;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return "I am a Radio\n";
    }
}
