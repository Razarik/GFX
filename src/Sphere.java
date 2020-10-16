import java.awt.*;

public class Sphere extends Object {
    private Point center;

    public Sphere(Color color, Point center, double radius) {
        super(color);
        TransformationFactory tf = new TransformationFactory();
        this.transformation = tf.scale(radius, radius, radius);
        this.inverseTransformation = tf.inverseScale(radius, radius, radius);
        this.center = center;
    }

    public HitPoint getHit(Ray ray) {
        // norm(direction)^2*t^2 + 2(eye*direction)*t + (norm(Eye)^2-1)=0
        //Point origin = transformation.multiply(ray.getOrigin());
        //Vector direction = transformation.multiply(ray.getDirection());
        Point origin = ray.getOrigin();
        Vector direction = ray.getDirection();
        double normDir = direction.norm();
        double normOrigin = origin.norm();
        double a = normDir * normDir;
        double b = 2 * (ray.getDirection().multiply(ray.getOrigin()));
        double c = normOrigin * normOrigin - 1;
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new HitPoint(-1, this, null);
        } else if (discriminant == 0) {
            double t = (-b) / (2 * a);
            return new HitPoint(t, this, origin.add(direction.multiplyElement(t)));
        } else {
            double t = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new HitPoint(t, this, origin.add(direction.multiplyElement(t)));
        }
    }
}
