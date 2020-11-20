package Calculations;

import Light.Material;

public class Ray {
    private Point origin;
    private Vector direction;
    private Material medium;

    public Ray(Point origin, Vector viewDirection, Vector u, Vector v, double distance, int screenWidth, int screenHeight, int row, int column, Material medium) {
        this.origin = origin;
        Vector n = viewDirection.normalise().multiplyElement(-distance);
        double uMultiplier = (screenWidth / 2.0) * (2.0 * column / screenWidth - 1);
        u = u.normalise().multiplyElement(uMultiplier);
        double vMultiplier = (screenHeight / 2.0) * (2.0 * row / screenHeight - 1);
        v = v.normalise().multiplyElement(vMultiplier);
        this.direction = n.add(u).add(v).normalise();
        this.medium = medium;
    }

    public Ray(Camera camera, int screenWidth, int screenHeight, int row, int column, Material medium){
        this.origin = camera.getEye();
        Vector n = camera.getDirection().normalise().multiplyElement(-camera.getDistance());
        double uMultiplier = (screenWidth / 2.0) * (2.0 * column / screenWidth - 1);
        Vector u = camera.getUp().normalise().multiplyElement(uMultiplier);
        double vMultiplier = (screenHeight / 2.0) * (2.0 * row / screenHeight - 1);
        Vector v = camera.getSide().normalise().multiplyElement(vMultiplier);
        this.direction = n.add(u).add(v).normalise();
        this.medium = medium;
    }

    public Ray(Point origin, Vector direction, Material medium) {
        this.origin = origin;
        this.direction = direction.normalise();
        this.medium = medium;
    }

    public Ray(Point origin, Point destination, Material medium){
        this.origin = origin;
        this.direction = destination.subtract(origin);
        this.medium = medium;
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

    public Material getMedium() {
        return medium;
    }

    public void setMedium(Material medium) {
        this.medium = medium;
    }

    public void print() {
        System.out.println("Eye at: (" + origin.getX() + ", " + origin.getY() + ", " + origin.getZ() + ")");
        System.out.println("Calculations.Ray direction: (" + direction.getX() + ", " + direction.getY() + ", " + direction.getZ() + ")");
    }
}
