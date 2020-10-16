import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int width = 640;
        int height = 480;
        WindowFrame wf = new WindowFrame(width, height);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Sphere(Color.green, new Point(0, 0, 0), 300));
        Point eye = new Point(10, 0, 0);
        Vector viewDirection = new Vector(1, 0, 0);
        Vector v = new Vector(0, 0, 1);
        Vector u = new Vector(0, 1, 0);
        for (int column = 0; column < width; column++) {
            for (int row = 0; row < height; row++) {
                Ray ray = new Ray(eye, viewDirection, u, v, 100, width, height, row, column);
                Color toDraw = Color.white;
                double earliestHitTime = Double.POSITIVE_INFINITY;
                for (Object o : objects) {
                    HitPoint hp = o.getHit(ray);
                    if (hp.getHitTime() >= 0 && hp.getHitTime() < earliestHitTime) {
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
