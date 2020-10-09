import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PointsComponent extends JComponent {

    private static class Point {
        final int x;
        final int y;
        final Color color;

        public Point(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    private final LinkedList<Point> points = new LinkedList<Point>();

    public void addPoint(int x, int y){
        addPoint(x, y, Color.black);
    }

    public void addPoint(int x, int y, Color color){
        points.add(new Point(x, y, color));
        repaint();
    }

    public void clearPoints(){
        points.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Point point : points){
            g.setColor(point.color);
            g.drawLine(point.x, point.y, point.x, point.y);
        }
    }
}
