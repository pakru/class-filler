package com.example.spring.vaadin.properties;

public enum VisionAbility {
    OK,
    LOW,
    VERY_LOW;

    @Override
    public String toString() {
        if (this == VisionAbility.OK)
            return "OK";
        if (this == VisionAbility.LOW)
            return "Low";
        if (this == VisionAbility.VERY_LOW)
            return "Very Low";
        return "";
    }
}
