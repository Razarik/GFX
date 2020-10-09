import javax.swing.*;
import java.awt.*;

public class Surface extends JPanel {
    private void doDrawing(Graphics g){
        Graphics2D g2= (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(100,100,100,100);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }
}
