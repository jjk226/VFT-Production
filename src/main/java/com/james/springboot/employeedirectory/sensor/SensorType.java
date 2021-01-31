package com.james.springboot.employeedirectory.sensor;

public enum SensorType {
    HP ("HP"),
    HT ("HT"),
    LP ("LP"),
    STD ("STD"),
    VLP ("VLP");

    private String name;

    SensorType (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
