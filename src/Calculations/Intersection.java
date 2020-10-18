package Calculations;

import Objects.Object;

import java.awt.*;

public class Intersection {
    private double hitTime;
    private Color color;
    private Point hitPoint;

    public Intersection(double hitTime, Color color, Point hitPoint) {
        this.hitTime = hitTime;
        this.color = color;
        this.hitPoint = hitPoint;
    }

    public double getHitTime() {
        return hitTime;
    }

    public void setHitTime(double hitTime) {
        this.hitTime = hitTime;
    }

    public Point getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(Point hitPoint) {
        this.hitPoint = hitPoint;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
