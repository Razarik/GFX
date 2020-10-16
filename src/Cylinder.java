import java.awt.*;

public class Cylinder extends Object {
    public Cylinder(Color color) {
        super(color);
    }

    public Intersection getHit(Ray ray) {
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        double a = direction.getX() * direction.getX() + direction.getY() * direction.getY();
        double b = 2 * (origin.getX() * direction.getX() + origin.getY() * direction.getY());
        double c = (origin.getX() * origin.getX()) + (origin.getY() * origin.getY()) - 1;
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new Intersection(-1, this, null);
        } else {
            double t = (-b - Math.sqrt(discriminant)) / (2 * a);
            double z = origin.getZ() + t * direction.getZ();
            if (z > 1 || z < 0) {
                return new Intersection(-1, this, null);
            } else {
                return new Intersection(t, this, origin.add(direction.multiplyElement(t)));
            }
        }
    }
}
