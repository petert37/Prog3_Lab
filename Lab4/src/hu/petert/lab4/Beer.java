package hu.petert.lab4;

import java.io.Serializable;

public class Beer implements Serializable {

    private String name;
    private String style;
    private Double strength;

    public Beer(String name, String style, Double strength) {
        this.name = name;
        this.style = style;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Style: " + style + ", Strength: " + strength;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public Double getStrength() {
        return strength;
    }
}