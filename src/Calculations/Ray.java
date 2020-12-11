package Calculations;

import Light.Material;
import Objects.Object;

import java.util.ArrayList;

public class Ray {
    private Point origin;
    private Vector direction;
    private ArrayList<Object> inside;

    public Ray(Point origin, Vector viewDirection, Vector u, Vector v, double distance, int screenWidth, int screenHeight, int row, int column) {
        this.origin = origin;
        Vector n = viewDirection.normalise().multiplyElement(-distance);
        double uMultiplier = (screenWidth / 2.0) * (2.0 * column / screenWidth - 1);
        u = u.normalise().multiplyElement(uMultiplier);
        double vMultiplier = (screenHeight / 2.0) * (2.0 * row / screenHeight - 1);
        v = v.normalise().multiplyElement(vMultiplier);
        this.direction = n.add(u).add(v).normalise();
        inside = new ArrayList<>();
    }

    public Ray(Camera camera, int screenWidth, int screenHeight, int row, int column) {
        this.origin = camera.getEye();
        Vector n = camera.getDirection().normalise().multiplyElement(-camera.getDistance());
        double uMultiplier = (screenWidth / 2.0) * (2.0 * column / screenWidth - 1);
        Vector u = camera.getUp().normalise().multiplyElement(uMultiplier);
        double vMultiplier = (screenHeight / 2.0) * (2.0 * row / screenHeight - 1);
        Vector v = camera.getSide().normalise().multiplyElement(vMultiplier);
        this.direction = n.add(u).add(v).normalise();
        inside = new ArrayList<>();
    }

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction.normalise();
        inside = new ArrayList<>();
    }

    public Ray(Point origin, Point destination) {
        this.origin = origin;
        this.direction = destination.subtract(origin);
        inside = new ArrayList<>();
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void print() {
        System.out.println("Eye at: (" + origin.getX() + ", " + origin.getY() + ", " + origin.getZ() + ")");
        System.out.println("Calculations.Ray direction: (" + direction.getX() + ", " + direction.getY() + ", " + direction.getZ() + ")");
    }

    public ArrayList<Object> getInside() {
        return inside;
    }

    public void setInside(ArrayList<Object> inside) {
        this.inside = inside;
    }

    public void addInside(Object o) {
        inside.add(o);
    }

    public void removeInside(Object o) {
        inside.remove(o);
    }

    public Object getHighestPriority() {
        Object highest = null;
        for (Object o : inside) {
            if (highest == null || o.getPriority() > highest.getPriority()) {
                highest = o;
            }
        }
        return highest;
    }
}
