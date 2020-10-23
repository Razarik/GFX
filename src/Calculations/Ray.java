package Calculations;

public class Ray {
    private Point origin;
    private Vector direction;


    public Ray(Point origin, Vector viewDirection, Vector u, Vector v, double distance, int screenWidth, int screenHeight, int row, int column) {
        this.origin = origin;
        Vector n = viewDirection.normalise().multiplyElement(-distance);
        double uMultiplier = (screenWidth / 2.0) * (2.0 * column / screenWidth - 1);
        u = u.normalise().multiplyElement(uMultiplier);
        double vMultiplier = (screenHeight / 2.0) * (2.0 * row / screenHeight - 1);
        v = v.normalise().multiplyElement(vMultiplier);
        this.direction = n.add(u).add(v).normalise();
    }

    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction.normalise();
    }

    public Ray(Point origin, Point destination){
        this.origin = origin;
        this.direction = destination.subtract(origin);
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
}
