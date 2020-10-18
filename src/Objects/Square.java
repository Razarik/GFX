package Objects;

import Calculations.Intersection;
import Calculations.Point;
import Calculations.Ray;
import Calculations.Vector;

import java.awt.*;
import java.util.ArrayList;

public class Square extends Object {

    public Square(Color color) {
        super(color);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        if (direction.getZ() != 0) {
            double t = -origin.getZ() / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (x > -1 && x < 1 && y > -1 && y < 1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
    }
}
