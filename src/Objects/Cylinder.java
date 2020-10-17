package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.Vector;
import Calculations.Point;

import java.awt.*;
import java.util.ArrayList;

public class Cylinder extends Object {

    private double topRadius;

    public Cylinder(Color color) {
        super(color);
        topRadius = 1;
    }

    public Cylinder(Color color, double topRadius) {
        super(color);
        this.topRadius = topRadius;
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        double a = direction.getX() * direction.getX() + direction.getY() * direction.getY() - direction.getZ() * direction.getZ() * (topRadius - 1) * (topRadius - 1);
        double b = 2 * (origin.getX() * direction.getX() + origin.getY() * direction.getY() - direction.getZ() * (origin.getZ() * (topRadius - 1) * (topRadius - 1) + topRadius - 1));
        double c = (origin.getX() * origin.getX()) + (origin.getY() * origin.getY()) - (origin.getZ() * (topRadius - 1) + 1) * (origin.getZ() * (topRadius - 1) + 1);
        double discriminant = b * b - 4 * a * c;
        if (direction.getZ() != 0) {
            double tBottom = -origin.getZ() / direction.getZ();
            addIfInCap(intersections, origin, direction, tBottom, 1);
        }
        if (direction.getZ() != 1) {
            double tTop = (1 - origin.getZ()) / direction.getZ();
            addIfInCap(intersections, origin, direction, tTop, topRadius);
        }
        if (discriminant == 0) {
            double t = -b / (2 * a);
            double z = origin.getZ() + t * direction.getZ();
            if (z <= 1 && z >= 0) {
                intersections.add(new Intersection(t, this, origin.add(direction.multiplyElement(t))));
            }
        } else if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double z1 = origin.getZ() + t1 * direction.getZ();
            double z2 = origin.getZ() + t2 * direction.getZ();
            if (z1 <= 1 && z1 >= 0) {
                intersections.add(new Intersection(t1, this, origin.add(direction.multiplyElement(t1))));
            }
            if (z2 <= 1 && z2 >= 0) {
                intersections.add(new Intersection(t2, this, origin.add(direction.multiplyElement(t2))));
            }
        }
    }

    private void addIfInCap(ArrayList<Intersection> intersections, Point origin, Vector direction, double t, double radius) {
        double x = origin.getX() + direction.getX() * t;
        double y = origin.getY() + direction.getY() * t;
        if (x * x + y * y < radius * radius) {
            intersections.add(new Intersection(t, this, origin.add(direction.multiplyElement(t))));
        }
    }
}
