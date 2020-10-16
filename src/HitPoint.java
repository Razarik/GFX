import java.awt.*;

public class HitPoint {
    private double hitTime;
    private Object object;
    private Point hitPoint;

    public HitPoint(double hitTime, Object object, Point hitPoint) {
        this.hitTime = hitTime;
        this.object = object;
        this.hitPoint = hitPoint;
    }

    public double getHitTime() {
        return hitTime;
    }

    public void setHitTime(double hitTime) {
        this.hitTime = hitTime;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
