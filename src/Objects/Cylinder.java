package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.Vector;
import Calculations.Point;
import Light.Material;

import java.util.ArrayList;

public class Cylinder extends Object {

    private final double topRadius;

    public Cylinder(Material material) {
        super(material);
        topRadius = 1;
    }

    public Cylinder(Material material, double topRadius) {
        super(material);
        this.topRadius = topRadius;
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        double u = topRadius - 1;
        double a = direction.getX() * direction.getX() + direction.getY() * direction.getY() - direction.getZ() * direction.getZ() * u * u;
        double b = 2 * (origin.getX() * direction.getX() + origin.getY() * direction.getY() - direction.getZ() * (origin.getZ() * u * u + u));
        double c = (origin.getX() * origin.getX()) + (origin.getY() * origin.getY()) - (origin.getZ() * u + 1) * (origin.getZ() * u + 1);
        double discriminant = b * b - 4 * a * c;
        if (direction.getZ() != 0) {
            double tBottom = -origin.getZ() / direction.getZ();
            addIfInCap(intersections, origin, direction, tBottom, 1, false);
        }
        if (direction.getZ() != 1) {
            double tTop = (1 - origin.getZ()) / direction.getZ();
            addIfInCap(intersections, origin, direction, tTop, topRadius, true);
        }
        if (discriminant == 0) {
            double t = -b / (2 * a);
            double z = origin.getZ() + t * direction.getZ();
            if (z <= 1 && z >= 0) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), inverseTransformation.transpose().multiply(new Vector(origin.getX() + t * direction.getX(), origin.getY() + t * direction.getY(), -u * (1 + u * z))), this, false));
            }
        } else if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double z1 = origin.getZ() + t1 * direction.getZ();
            double z2 = origin.getZ() + t2 * direction.getZ();
            boolean entering = false;
            if (z1 <= 1 && z1 >= 0) {
                Vector normal = new Vector(origin.getX() + t1 * direction.getX(), origin.getY() + t1 * direction.getY(), -u * (1 + u * z1));
                if (direction.multiplyElement(-1).dotProduct(normal) >= 0) {
                    entering = true;
                }
                intersections.add(new Intersection(t1, transformation.multiply(origin.add(direction.multiplyElement(t1))), inverseTransformation.transpose().multiply(normal), this, entering));
            }
            if (z2 <= 1 && z2 >= 0) {
                Vector normal = new Vector(origin.getX() + t2 * direction.getX(), origin.getY() + t2 * direction.getY(), -u * (1 + u * z2));
                if (direction.multiplyElement(-1).dotProduct(normal) >= 0) {
                    entering = true;
                }
                intersections.add(new Intersection(t2, transformation.multiply(origin.add(direction.multiplyElement(t2))), inverseTransformation.transpose().multiply(normal), this, entering));
            }
        }
    }

    private void addIfInCap(ArrayList<Intersection> intersections, Point origin, Vector direction, double t, double radius, boolean isTop) {
        double x = origin.getX() + direction.getX() * t;
        double y = origin.getY() + direction.getY() * t;
        double epsilon = 0.00001;
        boolean entering = false;
        if (x * x + y * y < radius * radius + epsilon) {
            if (isTop) {
                if (direction.multiplyElement(-1).dotProduct(new Vector(0, 0, 1)) >= 0) {
                    entering = true;
                }
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), inverseTransformation.transpose().multiply(new Vector(0, 0, 1)), this, entering));
            } else {
                if (direction.multiplyElement(-1).dotProduct(new Vector(0, 0, -1)) >= 0) {
                    entering = true;
                }
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), inverseTransformation.transpose().multiply(new Vector(0, 0, -1)), this, entering));
            }
        }
    }
}
