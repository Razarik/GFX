package Light;

import java.awt.*;

public class Colour {
    private double r;
    private double g;
    private double b;

    public Colour(double r, double g, double b) {
        this.r = Math.min(1, Math.max(0, r));
        this.g = Math.min(1, Math.max(0, g));
        this.b = Math.min(1, Math.max(0, b));
    }

    public Color getColor() {
        int newR = (int) (r * 255);
        int newG = (int) (g * 255);
        int newB = (int) (b * 255);
        return new Color(Math.min(Math.max(newR, 0), 255), Math.min(Math.max(newG, 0), 255), Math.min(Math.max(newB, 0), 255));
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public Colour add(Colour c) {
        return new Colour(r + c.getR(), g + c.getG(), b + c.getB());
    }

    public Colour multiply(Colour c) {
        return new Colour(r * c.getR(), g * c.getG(), b * c.getB());
    }

    public Colour multiply(double e) {

        return new Colour(r * e, g * e, b * e);
    }

    public String toString() {
        return (r + ", " + g + ", " + b);
    }
}
