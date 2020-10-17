import Calculations.*;
import Calculations.Point;
import Drawing.WindowFrame;
import Objects.*;
import Objects.Object;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        WindowFrame wf = new WindowFrame(width, height);
        TransformationFactory tf = new TransformationFactory();
        ArrayList<Object> objects = new ArrayList<>();
        /*objects.add(new Objects.Sphere(Color.pink, new Calculations.Point(-1.5, 1, 1), 1.2));
        objects.add(new Objects.Sphere(Color.lightGray, new Calculations.Point(-1, 0, 2), 1));
        objects.add(new Objects.Sphere(Color.white, new Calculations.Point(-1, 0, 0.5), 1.5));
        Objects.Sphere jens = new Objects.Sphere(Color.magenta, new Calculations.Point(0, 0, 0), 1);
        jens.transform(tf.translate(1, 2, 3), tf.inverseTranslate(1, 2, 3));
        objects.add(jens);*/
        Cylinder z = new Cylinder(Color.blue);
        z.transform(tf.scale(0.1, 0.1, 10), tf.inverseScale(0.1, 0.1, 10));
        objects.add(z);
        Cylinder x = new Cylinder(Color.red);
        x.transform(tf.scale(10, 0.1, 0.1).multiply(tf.yRoll(Math.toRadians(90))), tf.inverseYRoll(Math.toRadians(90)).multiply(tf.inverseScale(10, 0.1, 0.1)));
        objects.add(x);
        Cylinder y = new Cylinder(Color.green);
        y.transform(tf.scale(0.1, 10, 0.1).multiply(tf.xRoll(Math.toRadians(-90))), tf.inverseXRoll(Math.toRadians(-90)).multiply(tf.inverseScale(0.1, 10, 0.1)));
        objects.add(y);
        Sphere sphere1 = new Sphere(Color.magenta, new Calculations.Point(0, 0, 0), 1);
        sphere1.transform(tf.translate(0, 0, 5), tf.inverseTranslate(0, 0, 5));
        objects.add(sphere1);
        Cylinder cylinder1 = new Cylinder(Color.CYAN, 0);
        cylinder1.transform(tf.scale(1, 1, 3).multiply(tf.translate(3, 3, 0)), tf.inverseTranslate(3, 3, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder1);
        Cylinder cylinder2 = new Cylinder(Color.gray, 0.3);
        cylinder2.transform(tf.scale(1, 1, 3).multiply(tf.translate(5, 0, 0)), tf.inverseTranslate(5, 0, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder2);
        Cylinder cylinder3 = new Cylinder(Color.white, 1);
        cylinder3.transform(tf.scale(1, 1, 3).multiply(tf.translate(0, 5, 0)), tf.inverseTranslate(0, 5, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder3);
        Cube cube1 = new Cube(Color.orange);
        cube1.transform(tf.scale(3, 3, 3).multiply(tf.translate(1, -1.5, 0)), tf.inverseTranslate(1, -1.5, 0).multiply(tf.inverseScale(3, 3, 3)));
        objects.add(cube1);
        //objects.add(new BoundedPlane(Color.yellow, -10, 10, -10, 10));
        objects.add(new Plane(Color.yellow));
        Plane plane1 = new Plane(Color.red);
        plane1.transform(tf.translate(-10, 0, 0).multiply(tf.yRoll(Math.toRadians(90))), tf.inverseYRoll(Math.toRadians(90)).multiply(tf.inverseTranslate(-10, 0, 0)));
        objects.add(plane1);
        Plane plane2 = new Plane(Color.green);
        plane2.transform(tf.translate(0, -10, 0).multiply(tf.xRoll(Math.toRadians(90))), tf.inverseXRoll(Math.toRadians(90)).multiply(tf.inverseTranslate(0, -10, 0)));
        objects.add(plane2);
        Point eye = new Point(10, 10, 10);
        Vector viewDirection = new Vector(1, 1, 1);
        Vector v = new Vector(1, 1, -2);
        Vector u = new Vector(-1, 1, 0);
        /*Calculations.Point eye = new Calculations.Point(5, 0, 0);
        Calculations.Vector viewDirection = new Calculations.Vector(1,0,0);
        Calculations.Vector u = new Calculations.Vector(0,-1,0);
        Calculations.Vector v = new Calculations.Vector(0,0,-1);*/
        /*Objects.Sphere sphere1 = new Objects.Sphere(Color.blue, new Calculations.Point(0,0,0), 1);
        sphere1.transform(tf.translate(0,0,-2), tf.inverseTranslate(0,0,-2));
        objects.add(sphere1);
        Objects.Sphere sphere2 = new Objects.Sphere(Color.yellow, new Calculations.Point(0,0,0), 1);
        sphere2.transform(tf.translate(0,0,5), tf.inverseTranslate(0,0,5));
        objects.add(sphere2);*/
        ArrayList<Intersection> intersections = new ArrayList<>();
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Ray ray = new Ray(eye, viewDirection, u, v, 1000, width, height, row, column);
                Color toDraw = Color.black;
                intersections.clear();
                Intersection bestIntersection = null;
                for (Object o : objects) {
                    o.getHit(ray, intersections);
                }
                for (Intersection intersection : intersections) {
                    if (bestIntersection == null || (intersection.getHitTime() > 0 && intersection.getHitTime() < bestIntersection.getHitTime())) {
                        bestIntersection = intersection;
                    }
                }
                if (bestIntersection == null) {
                    wf.drawPoint(column, row, toDraw);
                } else {
                    wf.drawPoint(column, row, bestIntersection.getObject().getColor());
                }
            }
        }
        wf.setVisible(true);
    }
}
