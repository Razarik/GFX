package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.TransformationFactory;
import Calculations.Vector;
import Calculations.Point;
import Light.Colour;
import Light.Material;

import java.awt.*;
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
            intersections.add(new Intersection(t, transformation.multiply(p), transformation.multiply(p.subtract(new Point(0, 0, 0))), this));
        } else if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            Point p1 = origin.add(direction.multiplyElement(t1));
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            Point p2 = origin.add(direction.multiplyElement(t2));
            intersections.add(new Intersection(t1, transformation.multiply(p1), transformation.multiply(p1.subtract(new Point(0, 0, 0))), this));
            intersections.add(new Intersection(t2, transformation.multiply(p2), transformation.multiply(p2.subtract(new Point(0, 0, 0))), this));
        }
    }
}
