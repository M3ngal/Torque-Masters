package org.example.CarComponents;

public class BodyPaint {
    private String bodyPaintColor;

    public BodyPaint(String bodyPaintColor) {
        this.bodyPaintColor = bodyPaintColor;
    }

    public double setCost() {
        return 1700;
    }

    public String toString() {
        return String.format("\n6 - Body Paint Color: %s", bodyPaintColor);
    }
}
