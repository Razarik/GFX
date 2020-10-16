import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        WindowFrame wf = new WindowFrame(width, height);
        TransformationFactory tf = new TransformationFactory();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Sphere(Color.red, new Point(-1.5, 1, 1), 1.2));
        objects.add(new Sphere(Color.green, new Point(-1, 0, 2), 1));
        objects.add(new Sphere(Color.blue, new Point(-1, 0, 0.5), 1.5));
        Cylinder cylinder = new Cylinder(Color.yellow);
        cylinder.transform(tf.scale(1, 1, 3), tf.inverseScale(1, 1, 3));
        objects.add(cylinder);
        //objects.add(new Plane(Color.yellow));
        Point eye = new Point(10, 10, 10);
        Vector viewDirection = new Vector(1, 1, 1);
        Vector v = new Vector(1, 1, -2);
        Vector u = new Vector(-1, 1, 0);
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
