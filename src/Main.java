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

        // Materials from http://devernay.free.fr/cours/opengl/materials.html
        Material emerald = new Material(new Colour(0, 0, 0), new Colour(0.07568, 0.61424, 0.07568), new Colour(0.633, 0.727811, 0.633), 76.8, new Colour(0.0215, 0.1745, 0.0215));
        Material brass = new Material(new Colour(0, 0, 0), new Colour(0.780392, 0.568627, 0.113725), new Colour(0.992157, 0.941176, 0.807843), 27.8974, new Colour(0.329412, 0.223529, 0.027451));
        Material jade = new Material(new Colour(0, 0, 0), new Colour(0.54, 0.89, 0.63), new Colour(0.316228, 0.316228, 0.316228), 12.8, new Colour(0.135, 0.2225, 0.1575));
        Material obsidian = new Material(new Colour(0, 0, 0), new Colour(0.18275, 0.17, 0.22525), new Colour(0.332741, 0.328634, 0.346435), 38.4, new Colour(0.05375, 0.05, 0.06625));
        Material pearl = new Material(new Colour(0, 0, 0), new Colour(1, 0.829, 0.829), new Colour(0.296648, 0.296648, 0.296648), 11.264, new Colour(0.25, 0.20725, 0.20725));
        Material ruby = new Material(new Colour(0, 0, 0), new Colour(0.61424, 0.04136, 0.04136), new Colour(0.727811, 0.626959, 0.626959), 76.8, new Colour(0.1745, 0.01175, 0.01175));
        Material turquoise = new Material(new Colour(0, 0, 0), new Colour(0.396, 0.74151, 0.69102), new Colour(0.297254, 0.30829, 0.306678), 12.8, new Colour(0.1, 0.18725, 0.1745));
        Material bronze = new Material(new Colour(1, 0, 0), new Colour(0.714, 0.4284, 0.18144), new Colour(0.393548, 0.271906, 0.166721), 25.6, new Colour(0.2125, 0.1275, 0.054));
        Material chrome = new Material(new Colour(0, 0, 0), new Colour(0.4, 0.4, 0.4), new Colour(0.774597, 0.774597, 0.774597), 76.8, new Colour(0.25, 0.25, 0.25));
        Material copper = new Material(new Colour(0, 0, 0), new Colour(0.7038, 0.27048, 0.0828), new Colour(0.256777, 0.137622, 0.086014), 12.8, new Colour(0.19125, 0.0735, 0.0225));
        Material gold = new Material(new Colour(0, 0, 0), new Colour(0.75164, 0.60648, 0.22648), new Colour(0.628281, 0.555802, 0.366065), 51.2, new Colour(0.24725, 0.1995, 0.0745));
        Material silver = new Material(new Colour(0, 0, 0), new Colour(0.50754, 0.50754, 0.50754), new Colour(0.508273, 0.508273, 0.508273), 51.2, new Colour(0.19225, 0.19225, 0.19225));
        Material blackPlastic = new Material(new Colour(0, 0, 0), new Colour(0.01, 0.01, 0.01), new Colour(0.50, 0.50, 0.50), 32, new Colour(0.0, 0.0, 0.0));
        Material cyanPlastic = new Material(new Colour(0, 0, 0), new Colour(0.0, 0.50980392, 0.50980392), new Colour(0.50196078, 0.50196078, 0.50196078), 32, new Colour(0.0, 0.1, 0.06));
        Material greenPlastic = new Material(new Colour(0, 0, 0), new Colour(0.1, 0.35, 0.1), new Colour(0.1, 0.45, 0.55), 32, new Colour(0.0, 0.0, 0.0));
        Material bluePlastic = new Material(new Colour(0, 0, 0), new Colour(0.1, 0.1, 0.35), new Colour(0.6, 0.6, 0.7), 32, new Colour(0.0, 0.0, 0.0));
        Material redPlastic = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.0, 0.0), new Colour(0.7, 0.6, 0.6), 32, new Colour(0.0, 0.0, 0.0));
        Material whitePlastic = new Material(new Colour(0, 0, 0), new Colour(0.55, 0.55, 0.55), new Colour(0.70, 0.70, 0.70), 32, new Colour(0.0, 0.0, 0.0));
        Material yellowPlastic = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.5, 0.0), new Colour(0.60, 0.60, 0.50), 32, new Colour(0.0, 0.0, 0.0));
        Material blackRubber = new Material(new Colour(0, 0, 0), new Colour(0.01, 0.01, 0.01), new Colour(0.40, 0.40, 0.40), 10, new Colour(0.02, 0.02, 0.02));
        Material cyanRubber = new Material(new Colour(0, 0, 0), new Colour(0.4, 0.5, 0.5), new Colour(0.04, 0.7, 0.7), 10, new Colour(0.0, 0.05, 0.05));
        Material greenRubber = new Material(new Colour(0, 0, 0), new Colour(0.4, 0.5, 0.4), new Colour(0.04, 0.7, 0.04), 10, new Colour(0.0, 0.05, 0.0));
        Material redRubber = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.4, 0.4), new Colour(0.7, 0.04, 0.04), 10, new Colour(0.05, 0.0, 0.0));
        Material whiteRubber = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.5, 0.5), new Colour(0.7, 0.7, 0.7), 10, new Colour(0.05, 0.05, 0.05));
        Material yellowRubber = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.5, 0.4), new Colour(0.7, 0.7, 0.04), 10, new Colour(0.05, 0.05, 0.0));

        // Build objects
        ArrayList<Object> objects = new ArrayList<>();
        Cylinder z = new Cylinder(bluePlastic);
        z.transform(tf.scale(0.1, 0.1, 10), tf.inverseScale(0.1, 0.1, 10));
        objects.add(z);
        Cylinder x = new Cylinder(redPlastic);
        x.transform(tf.scale(10, 0.1, 0.1).multiply(tf.yRoll(Math.toRadians(90))), tf.inverseYRoll(Math.toRadians(90)).multiply(tf.inverseScale(10, 0.1, 0.1)));
        objects.add(x);
        Cylinder y = new Cylinder(greenPlastic);
        y.transform(tf.scale(0.1, 10, 0.1).multiply(tf.xRoll(Math.toRadians(-90))), tf.inverseXRoll(Math.toRadians(-90)).multiply(tf.inverseScale(0.1, 10, 0.1)));
        objects.add(y);
        Sphere sphere1 = new Sphere(emerald);
        sphere1.transform(tf.translate(0, 0, 2), tf.inverseTranslate(0, 0, 2));
        objects.add(sphere1);
        Sphere sphere2 = new Sphere(jade);
        sphere2.transform(tf.translate(5, 0, 10), tf.inverseTranslate(5, 0, 10));
        objects.add(sphere2);
        Sphere sphere3 = new Sphere(pearl);
        sphere3.transform(tf.translate(0, 5, 10), tf.inverseTranslate(0, 5, 10));
        objects.add(sphere3);
        Sphere sphere4 = new Sphere(ruby);
        sphere4.transform(tf.translate(10, -5, 5), tf.inverseTranslate(10, -5, 5));
        objects.add(sphere4);
        Cylinder cylinder1 = new Cylinder(brass, 0);
        cylinder1.transform(tf.scale(1, 1, 3).multiply(tf.translate(3, 3, 0)), tf.inverseTranslate(3, 3, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder1);
        Cylinder cylinder2 = new Cylinder(gold, 0.3);
        cylinder2.transform(tf.scale(1, 1, 3).multiply(tf.translate(5, 0, 0)), tf.inverseTranslate(5, 0, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder2);
        Cylinder cylinder3 = new Cylinder(silver, 1);
        cylinder3.transform(tf.scale(1, 1, 3).multiply(tf.translate(0, 5, 0)), tf.inverseTranslate(0, 5, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder3);
        Cube cube1 = new Cube(chrome);
        cube1.transform(tf.scale(2, 2, 2).multiply(tf.translate(1, -1.5, 1)), tf.inverseTranslate(1, -1.5, 1).multiply(tf.inverseScale(2, 2, 2)));
        objects.add(cube1);
        //objects.add(new Plane(copper));

        //Cube bigCube = new Cube(bronze);
        //bigCube.transform(tf.translate(0,0,30).multiply(tf.scale(30,30,30)), tf.inverseScale(30,30,30).multiply(tf.inverseTranslate(0,0,30)));
        //objects.add(bigCube);
        Sphere bigSphere = new Sphere(bronze);
        bigSphere.transform(tf.scale(30,30,30), tf.inverseScale(30,30,30));
        objects.add(bigSphere);

        /*Plane plane1 = new Plane(bronze);
        plane1.transform(tf.yRoll(Math.toRadians(90)).multiply(tf.translate(0, 0, -15)), tf.inverseTranslate(0, 0, -15).multiply(tf.inverseYRoll(Math.toRadians(90))));
        objects.add(plane1);
        Plane plane2 = new Plane(turquoise);
        plane2.transform(tf.xRoll(Math.toRadians(270)).multiply(tf.translate(0, 0, -15)), tf.inverseTranslate(0, 0, -15).multiply(tf.inverseXRoll(Math.toRadians(270))));
        objects.add(plane2);*/


        // Declare camera position
        Point eye = new Point(15, 15, 15);
        Vector viewDirection = new Vector(1, 1, 1);
        Vector v = new Vector(1, 1, -2);
        Vector u = new Vector(-1, 1, 0);

        // Build light sources
        ArrayList<LightSource> lights = new ArrayList<>();
        LightSource light1 = new LightSource(new Point(15, 10, 10), new Colour(1, 1, 1));
        lights.add(light1);
        LightSource light2 = new LightSource(new Point(-10, -13, 10), new Colour(1, 1, 1));
        lights.add(light2);
        Colour ambient = new Colour(0, 0, 0);

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
                    if (view.dotProduct(normal) < 0){
                        normal = normal.multiplyElement(-1);
                    }
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