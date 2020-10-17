package Objects;

import Calculations.Intersection;
import Calculations.Point;
import Calculations.Ray;
import Calculations.Vector;

import java.awt.*;
import java.util.ArrayList;

public class BoundedPlane extends Object {
    private double x1, x2, y1, y2;

    public BoundedPlane(Color color, double x1, double x2, double y1, double y2) {
        super(color);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        if (direction.getZ() != 0) {
            double t = -origin.getZ() / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (x > x1 && x < x2 && y > y1 && y < y2) {
                intersections.add(new Intersection(t, this, origin.add(direction.multiplyElement(t))));
            }
        }
    }
}
