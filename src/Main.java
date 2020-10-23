import Calculations.*;
import Calculations.Point;
import Drawing.WindowFrame;
import Light.Colour;
import Light.LightSource;
import Light.Material;
import Objects.*;
import Objects.Object;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Setup window
        int width = 1920;
        int height = 1080;
        WindowFrame wf = new WindowFrame(width, height);

        // Make transformation factory
        TransformationFactory tf = new TransformationFactory();

        // Build objects
        ArrayList<Object> objects = new ArrayList<>();
        Material aluminium = new Material(new Colour(0,0,0), new Colour(0.4242, 0.4798, 0.4798), new Colour(0.9, 0, 0), 16, new Colour(0.1, 0.1,0.1));
        Cylinder z = new Cylinder(aluminium);
        z.transform(tf.scale(0.1, 0.1, 10), tf.inverseScale(0.1, 0.1, 10));
        objects.add(z);
        Cylinder x = new Cylinder(aluminium);
        x.transform(tf.scale(10, 0.1, 0.1).multiply(tf.yRoll(Math.toRadians(90))), tf.inverseYRoll(Math.toRadians(90)).multiply(tf.inverseScale(10, 0.1, 0.1)));
        objects.add(x);
        Cylinder y = new Cylinder(aluminium);
        y.transform(tf.scale(0.1, 10, 0.1).multiply(tf.xRoll(Math.toRadians(-90))), tf.inverseXRoll(Math.toRadians(-90)).multiply(tf.inverseScale(0.1, 10, 0.1)));
        objects.add(y);
        Sphere sphere1 = new Sphere(aluminium);
        sphere1.transform(tf.translate(0, 0, 2), tf.inverseTranslate(0, 0, 2));
        objects.add(sphere1);
        Cylinder cylinder1 = new Cylinder(aluminium, 0);
        cylinder1.transform(tf.scale(1, 1, 3).multiply(tf.translate(3, 3, 0)), tf.inverseTranslate(3, 3, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder1);
        Cylinder cylinder2 = new Cylinder(aluminium, 0.3);
        cylinder2.transform(tf.scale(1, 1, 3).multiply(tf.translate(5, 0, 0)), tf.inverseTranslate(5, 0, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder2);
        Cylinder cylinder3 = new Cylinder(aluminium, 1);
        cylinder3.transform(tf.scale(1, 1, 3).multiply(tf.translate(0, 5, 0)), tf.inverseTranslate(0, 5, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder3);
        Cube cube1 = new Cube(aluminium);
        cube1.transform(tf.scale(2, 2, 2).multiply(tf.translate(1, -1.5, 1)), tf.inverseTranslate(1, -1.5, 1).multiply(tf.inverseScale(2, 2, 2)));
        objects.add(cube1);
        objects.add(new Plane(aluminium));

        // Declare camera position
        Point eye = new Point(15, 15, 15);
        Vector viewDirection = new Vector(1, 1, 1);
        Vector v = new Vector(1, 1, -2);
        Vector u = new Vector(-1, 1, 0);

        // Build light sources
        ArrayList<LightSource> lights = new ArrayList<>();
        LightSource light1 = new LightSource(new Point(10, 0, 10), new Colour(1, 1, 1));
        lights.add(light1);
        LightSource light2 = new LightSource(new Point(0,0,10), new Colour(1,1,1));
        lights.add(light2);
        Colour ambient = new Colour(0.2, 0.2, 0.2);

        //Intersection loop
        ArrayList<Intersection> intersections = new ArrayList<>();
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Ray ray = new Ray(eye, viewDirection, u, v, 1000, width, height, row, column);
                Color toDraw = new Color(0, 0, 0);
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
                if (bestIntersection != null) {
                    Point hitPoint = bestIntersection.getHitPoint();
                    Vector view = ray.getDirection().multiplyElement(-1).normalise();
                    Object object = bestIntersection.getHitObject();
                    Vector normal = bestIntersection.getHitNormal().normalise();
                    Colour colour = object.getMaterial().getEmissive().add(ambient.multiply(object.getMaterial().getAmbient()));
                    for (LightSource l : lights) {
                        //Shadow
                        Ray shadowRay = new Ray(hitPoint, l.getPoint());
                        ArrayList<Intersection> shadows = new ArrayList<>();
                        for (Object o : objects) {
                            if (!object.equals(o)) {
                                o.getHit(shadowRay, shadows);
                            }
                        }
                        boolean shaded = false;
                        for (Intersection shadowIntersection : shadows) {
                            if (shadowIntersection.getHitTime() > 0 && shadowIntersection.getHitTime() < 1) {
                                shaded = true;
                                break;
                            }
                        }
                        if (!shaded) {
                            // Diffuse colour
                            Vector s = l.getPoint().subtract(hitPoint).normalise();
                            double mDotS = s.dotProduct(normal);
                            if (mDotS > 0.0) {
                                Colour diffuseColour = object.getMaterial().getDiffuse().multiply(mDotS).multiply(l.getColour());
                                colour = colour.add(diffuseColour);
                            }

                            // Specular colour
                            Vector h = view.add(s).normalise();
                            double mDotH = h.dotProduct(normal);
                            if (mDotH > 0.0) {
                                double phong = Math.pow(mDotH, object.getMaterial().getSpecularExponent());
                                Colour specularColour = object.getMaterial().getSpecular().multiply(phong).multiply(l.getColour());
                                colour = colour.add(specularColour);
                            }
                        }
                        toDraw = colour.getColor();
                    }
                }
                wf.drawPoint(column, row, toDraw);
            }
        }
        wf.setVisible(true);
    }
}