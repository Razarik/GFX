import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        WindowFrame wf = new WindowFrame(width, height);
        TransformationFactory tf = new TransformationFactory();
        ArrayList<Object> objects = new ArrayList<>();
        /*objects.add(new Sphere(Color.pink, new Point(-1.5, 1, 1), 1.2));
        objects.add(new Sphere(Color.lightGray, new Point(-1, 0, 2), 1));
        objects.add(new Sphere(Color.white, new Point(-1, 0, 0.5), 1.5));
        Sphere jens = new Sphere(Color.magenta, new Point(0, 0, 0), 1);
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
        Sphere sphere1 = new Sphere(Color.blue, new Point(0, 0, 0), 1);
        sphere1.transform(tf.translate(0, 5, 0), tf.inverseTranslate(0, 5, 0));
        objects.add(sphere1);
        Sphere sphere2 = new Sphere(Color.red, new Point(0, 0, 0), 1);
        objects.add(sphere2);
        Sphere sphere3 = new Sphere(Color.magenta, new Point(0, 0, 0), 1);
        sphere3.transform(tf.translate(0, 0, 5), tf.inverseTranslate(0, 0, 5));
        objects.add(sphere3);
        Sphere sphere4 = new Sphere(Color.yellow, new Point(0, 0, 0), 1);
        sphere4.transform(tf.translate(5, 0, 0), tf.inverseTranslate(5, 0, 0));
        objects.add(sphere4);
        //objects.add(new Plane(Color.yellow));
        Point eye = new Point(10, 10, 10);
        Vector viewDirection = new Vector(1, 1, 1);
        Vector v = new Vector(1, 1, -2);
        Vector u = new Vector(-1, 1, 0);
        /*Point eye = new Point(5, 0, 0);
        Vector viewDirection = new Vector(1,0,0);
        Vector u = new Vector(0,-1,0);
        Vector v = new Vector(0,0,-1);*/
        /*Sphere sphere1 = new Sphere(Color.blue, new Point(0,0,0), 1);
        sphere1.transform(tf.translate(0,0,-2), tf.inverseTranslate(0,0,-2));
        objects.add(sphere1);
        Sphere sphere2 = new Sphere(Color.yellow, new Point(0,0,0), 1);
        sphere2.transform(tf.translate(0,0,5), tf.inverseTranslate(0,0,5));
        objects.add(sphere2);*/
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Ray ray = new Ray(eye, viewDirection, u, v, 1000, width, height, row, column);
                Color toDraw = Color.black;
                double earliestHitTime = Double.POSITIVE_INFINITY;
                for (Object o : objects) {
                    Intersection hp = o.getHit(ray);
                    if (hp.getHitTime() > 0 && hp.getHitTime() < earliestHitTime) {
                        earliestHitTime = hp.getHitTime();
                        toDraw = hp.getObject().getColor();
                    }
                }
                wf.drawPoint(column, row, toDraw);
            }
        }
        wf.setVisible(true);
    }
}
