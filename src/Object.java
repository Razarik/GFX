import java.awt.*;

public class Object {
    private Color color;
    protected Matrix transformation;
    protected Matrix inverseTransformation;

    public Object(Color color) {
        this.color = color;
        transformation = new Matrix();
        inverseTransformation = new Matrix();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Intersection getHit(Ray ray) {
        return null;
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public Matrix getInverseTransformation() {
        return inverseTransformation;
    }

    public void setInverseTransformation(Matrix inverseTransformation) {
        this.inverseTransformation = inverseTransformation;
    }

    public void transform(Matrix transformation, Matrix inverseTransform) {
        this.transformation = transformation.multiply(this.transformation);
        this.inverseTransformation = this.inverseTransformation.multiply(inverseTransform);
    }
}
