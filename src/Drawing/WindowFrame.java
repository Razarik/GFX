package Drawing;


import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {

    private final PointsComponent comp;

    public WindowFrame(int width, int height) {
        comp = new PointsComponent();
        initUI(width, height);
        getContentPane().add(comp);
    }

    public void initUI(int width, int height) {
        setTitle("Calculations.Ray-tracer");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void drawPoint(int x, int y, Color color) {
        comp.addPoint(x, y, color);
    }

    public void drawPoint(int x, int y) {
        comp.addPoint(x, y);
    }

    public void clear() {
        comp.clearPoints();
    }
}
