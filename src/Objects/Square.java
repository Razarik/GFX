package Objects;

import Calculations.Intersection;
import Calculations.Point;
import Calculations.Ray;
import Calculations.Vector;
import Light.Material;
import Statics.Globals;

import java.util.ArrayList;

public class Square extends Object {

    public Square(Material material) {
        super(material);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        if (direction.getZ() != 0) {
            double t = -origin.getZ() / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (Math.abs(x) < 1 + Globals.ERROR && Math.abs(y) < 1 + Globals.ERROR) {
                boolean entering = false;
                if (direction.multiplyElement(-1).dotProduct(new Vector(0, 0, 1)) >= 0) {
                    entering = true;
                }
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), inverseTransformation.transpose().multiply(new Vector(0, 0, 1)), this, entering));
            }
        }
    }
}
