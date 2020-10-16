import java.awt.*;

public class Sphere extends Object {
    private Point center;

    public Sphere(Color color, Point center, double radius) {
        super(color);
        TransformationFactory tf = new TransformationFactory();
        this.transformation = tf.translate(center.getX(), center.getY(), center.getZ()).multiply(tf.scale(radius, radius, radius));
        this.inverseTransformation = tf.inverseScale(radius, radius, radius).multiply(tf.translate(center.getX(), center.getY(), center.getZ()));
        this.center = center;
    }

    public Intersection getHit(Ray ray) {
        // norm(direction)^2*t^2 + 2(eye*direction)*t + (norm(Eye)^2-1)=0
        Point origin = inverseTransformation.multiply(ray.getOrigin());
        Vector direction = inverseTransformation.multiply(ray.getDirection());
        //Point origin = ray.getOrigin();
        //Vector direction = ray.getDirection();
        double normDir = direction.norm();
        double normOrigin = origin.norm();
        double a = normDir * normDir;
        double b = 2 * (direction.multiply(origin));
        double c = normOrigin * normOrigin - 1;
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new Intersection(-1, this, null);
        } else {
            double t = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new Intersection(t, this, origin.add(direction.multiplyElement(t)));
        }
    }
}
