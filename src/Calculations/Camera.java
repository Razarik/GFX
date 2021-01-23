package Calculations;

import Statics.Globals;

public class Camera {
    private Point eye;
    private Vector direction;
    private Vector up;
    private Vector side;
    private double roll;
    private double distance;

    public Camera(Point eye, Point lookingAt, double roll, double distance) {
        this.eye = eye;
        this.direction = eye.subtract(lookingAt).normalise();
        this.roll = roll;
        this.distance = distance;
        this.up = direction.crossProduct(new Vector(0, 0, -1));
        if (this.up.norm() < Globals.ERROR) {
            this.up = direction.crossProduct(new Vector(0, 1, 0)).normalise();
        } else {
            this.up = this.up.normalise();
        }
        TransformationFactory tf = new TransformationFactory();
        this.up = tf.rotateAroundAxis(roll, this.direction).multiply(this.up).normalise();
        this.side = up.crossProduct(direction).normalise();
    }

    public Point getEye() {
        return eye;
    }

    public void setEye(Point eye) {
        this.eye = eye;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Vector getUp() {
        return up;
    }

    public void setUp(Vector up) {
        this.up = up;
    }

    public Vector getSide() {
        return side;
    }

    public void setSide(Vector side) {
        this.side = side;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
