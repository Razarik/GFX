package Objects;

import Calculations.Intersection;
import Calculations.Ray;
import Calculations.Vector;
import Calculations.Point;
import Light.Material;

import java.util.ArrayList;

public class Plane extends Object {
    public Plane(Material material) {
        super(material);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        if (direction.getZ() != 0) {
            double t = -origin.getZ() / direction.getZ();
            boolean entering = false;
            if (direction.multiplyElement(-1).dotProduct(new Vector(0, 0, 1)) >= 0) {
                entering = true;
            }
            intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), inverseTransformation.transpose().multiply(new Vector(0, 0, 1)), this, entering));
        }
    }
}
