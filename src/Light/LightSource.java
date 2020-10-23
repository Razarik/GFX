package Light;

import Calculations.Point;

import java.awt.*;

public class LightSource {
    private Point point;
    private Colour colour;

    public LightSource(Point point, Colour colour) {
        this.point = point;
        this.colour = colour;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
