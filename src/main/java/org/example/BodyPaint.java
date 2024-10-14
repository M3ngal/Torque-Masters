package org.example;

public class BodyPaint {
    private String bodyPaintColor;

    BodyPaint(String bodyPaintColor) {
        this.bodyPaintColor = bodyPaintColor;
    }

    public double setCost() {
        return 1700;
    }

    public String toString() {
        return String.format("\n6 - Body Paint Color: %s", bodyPaintColor);
    }
}
