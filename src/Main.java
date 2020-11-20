import Calculations.*;
import Calculations.Point;
import Drawing.WindowFrame;
import Light.Colour;
import Light.LightSource;
import Light.Material;
import Objects.*;
import Objects.Object;

import java.util.ArrayList;

public class Main {

    public static Colour getShade(Ray ray, ArrayList<Object> objects, ArrayList<LightSource> lights, int depth, Point eye) {
        double epsilon = 0.0000001;
        ArrayList<Intersection> intersections = new ArrayList<>();
        Colour colour = new Colour(0, 0, 0);
        Intersection bestIntersection = null;
        for (Object o : objects) {
            o.getHit(ray, intersections);
        }
        for (Intersection intersection : intersections) {
            if ((bestIntersection == null && intersection.getHitTime() > epsilon) || (intersection.getHitTime() > epsilon && intersection.getHitTime() < bestIntersection.getHitTime())) {
                bestIntersection = intersection;
            }
        }
        if (bestIntersection != null) {
            Point hitPoint = bestIntersection.getHitPoint();
            Vector view = eye.subtract(hitPoint).normalise();
            Object object = bestIntersection.getHitObject();
            Vector normal = bestIntersection.getHitNormal().normalise();
            colour = object.getMaterial().getEmissive();
            if (ray.getDirection().multiplyElement(-1).dotProduct(normal) < 0) {
                normal = normal.multiplyElement(-1);
            }
            for (LightSource l : lights) {
                //Shadow
                Ray shadowRay = new Ray(hitPoint, l.getPoint(), null);
                ArrayList<Intersection> shadows = new ArrayList<>();
                for (Object o : objects) {
                    o.getHit(shadowRay, shadows);
                }
                boolean shaded = false;
                for (Intersection shadowIntersection : shadows) {
                    if (shadowIntersection.getHitTime() > epsilon && shadowIntersection.getHitTime() < 1) {
                        shaded = true;
                    }
                }
                //Ambient colour
                colour = colour.add(l.getAmbientContribution().multiply(object.getMaterial().getAmbient()));
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
            }
            if (depth <= 15) {
                boolean toReflect = false;
                if (object.getMaterial().getTransparency() >= 0.3) { // If object is transparent enough
                    if (ray.getMedium() == null) { // Ray was in air, going in object
                        double c1 = 1;
                        double c2 = object.getMaterial().getRelativeLightSpeed();
                        double cosTheta2Sqr = 1 - Math.pow(c2 / c1, 2) * (1 - Math.pow(normal.dotProduct(ray.getDirection()), 2));
                        if (cosTheta2Sqr < epsilon) {
                            toReflect = true;
                        } else {
                            double cosTheta2 = Math.sqrt(cosTheta2Sqr);
                            Vector t = ray.getDirection().multiplyElement(c2 / c1).add(normal.multiplyElement((c2 / c1) * normal.dotProduct(ray.getDirection()) - cosTheta2));
                            Ray refraction = new Ray(hitPoint, t, object.getMaterial());
                            colour = colour.add(getShade(refraction, objects, lights, depth + 1, eye).multiply(object.getMaterial().getTransparency()));
                        }
                    } else {
                        double c1 = ray.getMedium().getRelativeLightSpeed();
                        double c2 = 1;
                        double cosTheta2Sqr = 1 - Math.pow(c2 / c1, 2) * (1 - Math.pow(normal.dotProduct(ray.getDirection()), 2));
                        if (cosTheta2Sqr < epsilon) {
                            toReflect = true;
                        } else {
                            double cosTheta2 = Math.sqrt(cosTheta2Sqr);
                            Vector t = ray.getDirection().multiplyElement(c2 / c1).add(normal.multiplyElement((c2 / c1) * normal.dotProduct(ray.getDirection()) - cosTheta2));
                            Ray refraction = new Ray(hitPoint, t, null);
                            colour = colour.add(getShade(refraction, objects, lights, depth + 1, eye).multiply(object.getMaterial().getTransparency()));
                        }
                    }
                }
                if (object.getMaterial().getShininess() >= 0.3 || toReflect) {  // If object is shiny enough to reflect
                    Ray reflection = new Ray(hitPoint, ray.getDirection().add(normal.multiplyElement(ray.getDirection().dotProduct(normal) * -2)), ray.getMedium());
                    Colour reflectionColour = getShade(reflection, objects, lights, depth + 1, eye);
                    if (toReflect){
                        colour = colour.add(reflectionColour.multiply(object.getMaterial().getTransparency()));
                    }
                    colour = colour.add(reflectionColour.multiply(object.getMaterial().getShininess()));
                }
            }
        }
        return colour;
    }

    public static void main(String[] args) {
        // Setup window
        int width = 1920;
        int height = 1080;
        WindowFrame wf = new WindowFrame(width, height);

        // Make transformation factory
        TransformationFactory tf = new TransformationFactory();

        // Materials from http://devernay.free.fr/cours/opengl/materials.html
        // TODO: find realistic reflection, refraction and density
        Material emerald = new Material(new Colour(0, 0, 0), new Colour(0.07568, 0.61424, 0.07568), new Colour(0.633, 0.727811, 0.633), 76.8, new Colour(0.0215, 0.1745, 0.0215), 0, 0, 0);
        Material brass = new Material(new Colour(0, 0, 0), new Colour(0.780392, 0.568627, 0.113725), new Colour(0.992157, 0.941176, 0.807843), 27.8974, new Colour(0.329412, 0.223529, 0.027451), 0, 0, 0);
        Material jade = new Material(new Colour(0, 0, 0), new Colour(0.54, 0.89, 0.63), new Colour(0.316228, 0.316228, 0.316228), 12.8, new Colour(0.135, 0.2225, 0.1575), 0, 0, 0);
        Material obsidian = new Material(new Colour(0, 0, 0), new Colour(0.18275, 0.17, 0.22525), new Colour(0.332741, 0.328634, 0.346435), 38.4, new Colour(0.05375, 0.05, 0.06625), 0, 0, 0);
        Material pearl = new Material(new Colour(0, 0, 0), new Colour(1, 0.829, 0.829), new Colour(0.296648, 0.296648, 0.296648), 11.264, new Colour(0.25, 0.20725, 0.20725), 0, 0, 0);
        Material ruby = new Material(new Colour(0, 0, 0), new Colour(0.61424, 0.04136, 0.04136), new Colour(0.727811, 0.626959, 0.626959), 76.8, new Colour(0.1745, 0.01175, 0.01175), 0, 0, 0);
        Material turquoise = new Material(new Colour(0, 0, 0), new Colour(0.396, 0.74151, 0.69102), new Colour(0.297254, 0.30829, 0.306678), 12.8, new Colour(0.1, 0.18725, 0.1745), 0, 0, 0);
        Material bronze = new Material(new Colour(0, 0, 0), new Colour(0.714, 0.4284, 0.18144), new Colour(0.393548, 0.271906, 0.166721), 25.6, new Colour(0.2125, 0.1275, 0.054), 0, 0, 0);
        Material chrome = new Material(new Colour(0, 0, 0), new Colour(0.4, 0.4, 0.4), new Colour(0.774597, 0.774597, 0.774597), 76.8, new Colour(0.25, 0.25, 0.25), 0, 0, 0);
        Material copper = new Material(new Colour(0, 0, 0), new Colour(0.7038, 0.27048, 0.0828), new Colour(0.256777, 0.137622, 0.086014), 12.8, new Colour(0.19125, 0.0735, 0.0225), 0, 0, 0);
        Material gold = new Material(new Colour(0, 0, 0), new Colour(0.75164, 0.60648, 0.22648), new Colour(0.628281, 0.555802, 0.366065), 51.2, new Colour(0.24725, 0.1995, 0.0745), 0, 0, 0);
        Material silver = new Material(new Colour(0, 0, 0), new Colour(0.50754, 0.50754, 0.50754), new Colour(0.508273, 0.508273, 0.508273), 51.2, new Colour(0.19225, 0.19225, 0.19225), 0, 0, 0);
        Material blackPlastic = new Material(new Colour(0, 0, 0), new Colour(0.01, 0.01, 0.01), new Colour(0.50, 0.50, 0.50), 32, new Colour(0.0, 0.0, 0.0), 0, 0, 0);
        Material cyanPlastic = new Material(new Colour(0, 0, 0), new Colour(0.0, 0.50980392, 0.50980392), new Colour(0.50196078, 0.50196078, 0.50196078), 32, new Colour(0.0, 0.1, 0.06), 0, 0, 0);
        Material greenPlastic = new Material(new Colour(0, 0, 0), new Colour(0.1, 0.35, 0.1), new Colour(0.1, 0.45, 0.55), 32, new Colour(0.0, 0.0, 0.0), 0, 0, 0);
        Material bluePlastic = new Material(new Colour(0, 0, 0), new Colour(0.1, 0.1, 0.35), new Colour(0.6, 0.6, 0.7), 32, new Colour(0.0, 0.0, 0.0), 0, 0, 0);
        Material redPlastic = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.0, 0.0), new Colour(0.7, 0.6, 0.6), 32, new Colour(0.0, 0.0, 0.0), 0, 0, 0);
        Material whitePlastic = new Material(new Colour(0, 0, 0), new Colour(0.55, 0.55, 0.55), new Colour(0.70, 0.70, 0.70), 32, new Colour(0.0, 0.0, 0.0), 0, 0, 0);
        Material yellowPlastic = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.5, 0.0), new Colour(0.60, 0.60, 0.50), 32, new Colour(0.0, 0.0, 0.0), 0, 0, 0);
        Material blackRubber = new Material(new Colour(0, 0, 0), new Colour(0.01, 0.01, 0.01), new Colour(0.40, 0.40, 0.40), 10, new Colour(0.02, 0.02, 0.02), 0, 0, 0);
        Material cyanRubber = new Material(new Colour(0, 0, 0), new Colour(0.4, 0.5, 0.5), new Colour(0.04, 0.7, 0.7), 10, new Colour(0.0, 0.05, 0.05), 0, 0, 0);
        Material greenRubber = new Material(new Colour(0, 0, 0), new Colour(0.4, 0.5, 0.4), new Colour(0.04, 0.7, 0.04), 10, new Colour(0.0, 0.05, 0.0), 0, 0, 0);
        Material redRubber = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.4, 0.4), new Colour(0.7, 0.04, 0.04), 10, new Colour(0.05, 0.0, 0.0), 0, 0, 0);
        Material whiteRubber = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.5, 0.5), new Colour(0.7, 0.7, 0.7), 10, new Colour(0.05, 0.05, 0.05), 0, 0, 0);
        Material yellowRubber = new Material(new Colour(0, 0, 0), new Colour(0.5, 0.5, 0.4), new Colour(0.7, 0.7, 0.04), 10, new Colour(0.05, 0.05, 0.0), 0, 0, 0);

        Material mirrorMaterial = new Material(new Colour(0, 0, 0), new Colour(0, 0, 0), new Colour(0, 0, 0), 0, new Colour(0, 0, 0), 1, 0, 0);
        Material fineSpecular = new Material(new Colour(0, 0, 0), new Colour(0.61424, 0.04136, 0.04136), new Colour(0.727811, 0.626959, 0.626959), 500, new Colour(0.1745, 0.01175, 0.01175), 0, 0, 0);
        Material glass = new Material(new Colour(0, 0, 0), new Colour(0, 0, 0), new Colour(0, 0, 0), 0, new Colour(0, 0, 0), 0, 1, 0.55);

        // Build objects
        ArrayList<Object> objects = new ArrayList<>();

        //Sphere bigSphere = new Sphere(obsidian);
        //bigSphere.transform(tf.scale(30,30,30), tf.inverseScale(30,30,30));
        //objects.add(bigSphere);

        Cube bigCube = new Cube(copper);
        bigCube.transform(tf.translate(0, 0, 19.99).multiply(tf.scale(20, 20, 20)), tf.inverseScale(20, 20, 20).multiply(tf.inverseTranslate(0, 0, 19.99)));
        objects.add(bigCube);

        //Cylinder bigCylinder = new Cylinder(obsidian);
        //bigCylinder.transform(tf.scale(20, 20, 20), tf.inverseScale(20, 20, 20));
        //objects.add(bigCylinder);

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

        Cylinder cylinder3 = new Cylinder(silver, 1);
        cylinder3.transform(tf.scale(1, 1, 3).multiply(tf.translate(0, 5, 0)), tf.inverseTranslate(0, 5, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder3);

        Cube cube1 = new Cube(silver);
        cube1.transform(tf.scale(2, 2, 2).multiply(tf.translate(1, -1.5, 1)), tf.inverseTranslate(1, -1.5, 1).multiply(tf.inverseScale(2, 2, 2)));
        objects.add(cube1);

        Cylinder cylinder2 = new Cylinder(gold, 0.3);
        cylinder2.transform(tf.scale(1, 1, 3).multiply(tf.translate(5, 0, 0)), tf.inverseTranslate(5, 0, 0).multiply(tf.inverseScale(1, 1, 3)));
        objects.add(cylinder2);

        Square mirror = new Square(mirrorMaterial);
        mirror.transform(tf.yRoll(Math.toRadians(90)).multiply(tf.translate(-8, 0, -19.99).multiply(tf.scale(8, 8, 8))), tf.inverseScale(8, 8, 8).multiply(tf.inverseTranslate(-8, 0, -19.99).multiply(tf.inverseYRoll(Math.toRadians(90)))));
        objects.add(mirror);

        Cube glassPane = new Cube(glass);
        glassPane.transform(tf.translate(-10, 0, 4).multiply(tf.scale(0.2, 3, 3)), tf.inverseScale(0.2, 3, 3).multiply(tf.inverseTranslate(-10, 0, 4)));
        objects.add(glassPane);

        Sphere behindGlass = new Sphere(bluePlastic);
        behindGlass.transform(tf.translate(-12, 2.5, 2), tf.inverseTranslate(-12, 2.5, 2));
        objects.add(behindGlass);

        // Declare camera position
        Point eye = new Point(15, 10, 5);

        Camera camera = new Camera(eye, new Point(0, 0, 5), 0, 1000);

        // Build light sources
        ArrayList<LightSource> lights = new ArrayList<>();
        LightSource light1 = new LightSource(new Point(10, 0, 10), new Colour(1, 1, 1), new Colour(0.2, 0.2, 0.2));
        lights.add(light1);
        LightSource light2 = new LightSource(new Point(-10, -12, 10), new Colour(1, 1, 1), new Colour(0.05, 0.05, 0.05));
        lights.add(light2);
        LightSource light3 = new LightSource(new Point(-10, 0, 5), new Colour(1, 1, 1), new Colour(0.1, 0.1, 0.1));
        lights.add(light3);

        //Intersection loop
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Ray ray = new Ray(camera, width, height, row, column, null);
                wf.drawPoint(column, row, getShade(ray, objects, lights, 0, eye).getColor());
            }
        }
        wf.setVisible(true);
    }
}