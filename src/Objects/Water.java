package Objects;

import Calculations.Intersection;
import Calculations.Point;
import Calculations.Ray;
import Calculations.Vector;
import Light.Material;
import Statics.Globals;

import java.util.ArrayList;

public class Water extends Object {
    private double xWaves;
    private double yWaves;
    private double amplitude;

    public Water(Material material) {
        super(material);
        xWaves = 1;
        yWaves = 1;
        amplitude = 1;
    }

    public double getxWaves() {
        return xWaves;
    }

    public void setxWaves(double xWaves) {
        this.xWaves = xWaves;
    }

    public double getyWaves() {
        return yWaves;
    }

    public void setyWaves(double yWaves) {
        this.yWaves = yWaves;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
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
                Vector normal = new Vector(Math.sin(x * xWaves * Math.PI) * amplitude, Math.sin(y * yWaves * Math.PI) * amplitude, 1).normalise();
                intersections.add(new Intersection(t, transformation.multiply(origin.add(direction.multiplyElement(t))), inverseTransformation.transpose().multiply(normal), this, entering));
            }
        }
    }
}
