package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.TransformationFactory;
import Calculations.Vector;
import Calculations.Point;

import java.awt.*;
import java.util.ArrayList;

public class Sphere extends Object {

    public Sphere(Color color, Point center, double radius) {
        super(color);
        TransformationFactory tf = new TransformationFactory();
        this.transformation = tf.translate(center.getX(), center.getY(), center.getZ()).multiply(tf.scale(radius, radius, radius));
        this.inverseTransformation = tf.inverseScale(radius, radius, radius).multiply(tf.translate(center.getX(), center.getY(), center.getZ()));
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
            intersections.add(new Intersection(t, this, origin.add(direction.multiplyElement(t))));
        } else if (discriminant > 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            intersections.add(new Intersection(t1, this, origin.add(direction.multiplyElement(t1))));
            intersections.add(new Intersection(t2, this, origin.add(direction.multiplyElement(t2))));
        }
    }
}
