package Calculations;

import Objects.Object;

public class Intersection {
    private double hitTime;
    private Point hitPoint;
    private Vector hitNormal;
    private Object hitObject;

    public Intersection(double hitTime, Point hitPoint, Vector hitNormal, Object object) {
        this.hitTime = hitTime;
        this.hitPoint = hitPoint;
        this.hitNormal = hitNormal;
        this.hitObject = object;
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

    public Vector getHitNormal() {
        return hitNormal;
    }

    public void setHitNormal(Vector hitNormal) {
        this.hitNormal = hitNormal;
    }

    public Object getHitObject() {
        return hitObject;
    }

    public void setHitObject(Object hitObject) {
        this.hitObject = hitObject;
    }
}
