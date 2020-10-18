package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.Vector;
import Calculations.Point;

import java.awt.*;
import java.util.ArrayList;

public class Plane extends Object {
    public Plane(Color color) {
        super(color);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        if (direction.getZ() != 0) {
            double t = -origin.getZ() / direction.getZ();
            intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
        }
    }
}
