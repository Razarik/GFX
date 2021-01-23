package Calculations;

import Objects.Object;

public class Intersection implements Comparable<Intersection> {
    private double hitTime;
    private Point hitPoint;
    private Vector hitNormal;
    private Object hitObject;
    private boolean isEntering;

    public Intersection(double hitTime, Point hitPoint, Vector hitNormal, Object object, boolean isEntering) {
        this.hitTime = hitTime;
        this.hitPoint = hitPoint;
        this.hitNormal = hitNormal;
        this.hitObject = object;
        this.isEntering = isEntering;
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

    public boolean isEntering() {
        return isEntering;
    }

    public void setEntering(boolean entering) {
        isEntering = entering;
    }

    public int compareTo(Intersection otherIntersection) {
        double difference = hitTime - otherIntersection.getHitTime();
        if (difference > 0) {
            return 1;
        } else if (difference == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
