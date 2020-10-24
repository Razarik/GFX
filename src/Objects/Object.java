package Objects;

import Calculations.Intersection;
import Calculations.Matrix;
import Calculations.Ray;
import Light.Material;

import java.util.ArrayList;

public abstract class Object {
    protected Matrix transformation;
    protected Matrix inverseTransformation;
    private Material material;

    public Object(Material material) {
        this.material = material;
        transformation = new Matrix();
        inverseTransformation = new Matrix();
    }

    public abstract void getHit(Ray ray, ArrayList<Intersection> intersections);

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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void transform(Matrix transformation, Matrix inverseTransform) {
        this.transformation = transformation.multiply(this.transformation);
        this.inverseTransformation = this.inverseTransformation.multiply(inverseTransform);
    }
}
