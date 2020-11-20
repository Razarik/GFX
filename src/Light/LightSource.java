package Light;

import Calculations.Point;

public class LightSource {
    private Point point;
    private Colour colour;
    private Colour ambientContribution;

    public LightSource(Point point, Colour colour, Colour ambient) {
        this.point = point;
        this.colour = colour;
        this.ambientContribution = ambient;
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

    public Colour getAmbientContribution() {
        return ambientContribution;
    }

    public void setAmbientContribution(Colour ambientContribution) {
        this.ambientContribution = ambientContribution;
    }
}
