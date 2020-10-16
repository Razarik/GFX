import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*double[][] values = {{7, 4, 12, 2},
                {4, 8, 6, 3},
                {6, 9, 7, 3},
                {7, 5, 6, 3}};
        Matrix a = new Matrix(values);
        values = new double[][]{{5, 6, 7, 7},
                {6, 3, 1, 4},
                {3, 6, 5, 8},
                {4, 7, 6, 9}};
        Matrix b = new Matrix(values);
        a.print();
        System.out.println("Multiplied by");
        b.print();
        Matrix c = a.multiply(b);
        System.out.println("Gives");
        c.print();
        Matrix d = b.multiply(a);
        System.out.println();
        d.print();
        Vector v = new Vector(3, 2, -5);
        Vector result = a.multiply(v);
        System.out.println();
        result.print();*/


        int width = 640;
        int height = 480;
        WindowFrame wf = new WindowFrame(width, height);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Sphere(Color.red, new Point(0, 0, 0), 300));
        Point eye = new Point(5, 0, 0);
        Vector viewDirection = new Vector(1, 0, 0);
        Vector v = new Vector(0, 0, 1);
        Vector u = new Vector(0, 1, 0);
        /*Point eye = new Point(10, 5, 3);
        Vector direction = new Vector(-9.5, -5, -3.5);
        Ray ray = new Ray(eye, direction);
        ray.print();
        objects.get(0).getHit(ray);*/
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Ray ray = new Ray(eye, viewDirection, u, v, 100, width, height, row, column);
                ray.print();
                Color toDraw = Color.white;
                double earliestHitTime = 0;
                for (Object o : objects) {
                    HitPoint hp = o.getHit(ray);
                    if (hp.getHitTime() >= 0 && hp.getHitTime() < earliestHitTime) {
                        earliestHitTime = hp.getHitTime();
                        toDraw = hp.getObject().getColor();
                    }
                }
                wf.drawPoint(column, row, Color.red);
            }
        }
        wf.setVisible(true);



        /*Vector v = new Vector(10, -2, 8);
        v.normalise().print();
        System.out.println(v.normalise().norm());*/

        TransformationFactory tf = new TransformationFactory();
        /*Vector axis = new Vector(7, 3, 0.2);
        Matrix A = tf.rotateAroundAxis(1.3, axis);
        Matrix B = tf.inverseRotateAroundAxis(1.3, axis);
        Matrix A = tf.yRoll(1.3);
        Matrix B = tf.inverseYRoll(1.3);
        A.multiply(B).print();*/
    }
}
