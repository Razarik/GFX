import java.awt.*;

public class HitPoint {
    private double hitTime;
    private Object object;

    public HitPoint(double hitTime, Object object) {
        this.hitTime = hitTime;
        this.object = object;
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
