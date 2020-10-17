package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.Vector;
import Calculations.Point;

import java.awt.*;

public class Plane extends Object {
    public Plane(Color color) {
        super(color);
    }

    public Intersection getHit(Ray ray) {
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        if (direction.getZ() == 0) {
            return new Intersection(-1, this, null);
        } else {
            double t = -origin.getZ() / direction.getZ();
            return new Intersection(t, this, origin.add(direction.multiplyElement(t)));
        }
    }
}
