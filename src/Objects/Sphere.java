package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.Vector;
import Calculations.Point;
import Light.Material;

import java.util.ArrayList;

public class Sphere extends Object {

    public Sphere(Material material) {
        super(material);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        double normDir = direction.norm();
        double normOrigin = origin.norm();
        double a = normDir * normDir;
        double b = 2 * (direction.multiply(origin));
        double c = normOrigin * normOrigin - 1;
        double discriminant = b * b - 4 * a * c;
        if (discriminant == 0) {
            double t = -b / (2 * a);
            Point p = origin.add(direction.multiplyElement(t));
            intersections.add(new Intersection(t, transformation.multiply(p), transformation.multiply(p.subtract(new Point(0, 0, 0))), this, false));
        } else if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            Point p1 = origin.add(direction.multiplyElement(t1));
            Vector normal1 = p1.subtract(new Point(0, 0, 0));
            boolean entering1 = false;
            if (direction.multiplyElement(-1).dotProduct(normal1) >= 0) {
                entering1 = true;
            }
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            Point p2 = origin.add(direction.multiplyElement(t2));
            Vector normal2 = p2.subtract(new Point(0, 0, 0));
            boolean entering2 = false;
            if (direction.multiplyElement(-1).dotProduct(normal2) >= 0) {
                entering2 = true;
            }
            intersections.add(new Intersection(t1, transformation.multiply(p1), transformation.multiply(normal1), this, entering1));
            intersections.add(new Intersection(t2, transformation.multiply(p2), transformation.multiply(normal2), this, entering2));
        }
    }
}
