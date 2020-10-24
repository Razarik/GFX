package Objects;

import Calculations.Intersection;
import Calculations.Point;
import Calculations.Ray;
import Calculations.Vector;
import Light.Material;

import java.util.ArrayList;

public class Cube extends Object {

    public Cube(Material material) {
        super(material);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        if (direction.getX() != -1) {
            double t = (-1 - origin.getX()) / direction.getX();
            double y = origin.getY() + t * direction.getY();
            double z = origin.getZ() + t * direction.getZ();
            if (y <= 1 && y >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), transformation.multiply(new Vector(-1, 0, 0)), this));
            }
        }
        if (direction.getX() != 1) {
            double t = (1 - origin.getX()) / direction.getX();
            double y = origin.getY() + t * direction.getY();
            double z = origin.getZ() + t * direction.getZ();
            if (y <= 1 && y >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), transformation.multiply(new Vector(1, 0, 0)), this));
            }
        }
        if (direction.getY() != -1) {
            double t = (-1 - origin.getY()) / direction.getY();
            double x = origin.getX() + t * direction.getX();
            double z = origin.getZ() + t * direction.getZ();
            if (x <= 1 && x >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), transformation.multiply(new Vector(0, -1, 0)), this));
            }
        }
        if (direction.getY() != 1) {
            double t = (1 - origin.getY()) / direction.getY();
            double x = origin.getX() + t * direction.getX();
            double z = origin.getZ() + t * direction.getZ();
            if (x <= 1 && x >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), transformation.multiply(new Vector(0, 1, 0)), this));
            }
        }
        if (direction.getZ() != -1) {
            double t = (-1 - origin.getZ()) / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (x <= 1 && x >= -1 && y <= 1 && y >= -1) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), transformation.multiply(new Vector(0, 0, -1)), this));
            }
        }
        if (direction.getZ() != 1) {
            double t = (1 - origin.getZ()) / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (x <= 1 && x >= -1 && y <= 1 && y >= -1) {
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), transformation.multiply(new Vector(0, 0, 1)), this));
            }
        }
    }
}
