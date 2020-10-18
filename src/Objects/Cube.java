package Objects;

import Calculations.Intersection;
import Calculations.Point;
import Calculations.Ray;
import Calculations.Vector;

import java.awt.*;
import java.util.ArrayList;

public class Cube extends Object {

    public Cube(Color color) {
        super(color);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        if (direction.getX() != -1) {
            double t = (-1 - origin.getX()) / direction.getX();
            double y = origin.getY() + t * direction.getY();
            double z = origin.getZ() + t * direction.getZ();
            if (y <= 1 && y >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
        if (direction.getX() != 1) {
            double t = (1 - origin.getX()) / direction.getX();
            double y = origin.getY() + t * direction.getY();
            double z = origin.getZ() + t * direction.getZ();
            if (y <= 1 && y >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
        if (direction.getY() != -1) {
            double t = (-1 - origin.getY()) / direction.getY();
            double x = origin.getX() + t * direction.getX();
            double z = origin.getZ() + t * direction.getZ();
            if (x <= 1 && x >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
        if (direction.getY() != 1) {
            double t = (1 - origin.getY()) / direction.getY();
            double x = origin.getX() + t * direction.getX();
            double z = origin.getZ() + t * direction.getZ();
            if (x <= 1 && x >= -1 && z <= 1 && z >= -1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
        if (direction.getZ() != -1) {
            double t = (-1 - origin.getZ()) / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (x <= 1 && x >= -1 && y <= 1 && y >= -1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
        if (direction.getZ() != 1) {
            double t = (1 - origin.getZ()) / direction.getZ();
            double x = origin.getX() + t * direction.getX();
            double y = origin.getY() + t * direction.getY();
            if (x <= 1 && x >= -1 && y <= 1 && y >= -1) {
                intersections.add(new Intersection(t, color, origin.add(direction.multiplyElement(t))));
            }
        }
    }
}
