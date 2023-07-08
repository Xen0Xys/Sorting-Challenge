package fr.xen0xys.models;

import java.awt.*;

public class Rod {

    private final int value;
    private Color color;

    public Rod(int value) {
        this.value = value;
        this.color = Color.WHITE;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }
    public Color getColor() {
        return color;
    }
}
