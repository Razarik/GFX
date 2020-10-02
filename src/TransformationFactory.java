import static java.lang.Math.*;

public class TransformationFactory {
    public Matrix translate(double x, double y, double z) {
        Matrix result = new Matrix();
        result.setValue(0, 3, x);
        result.setValue(1, 3, y);
        result.setValue(2, 3, z);
        return result;
    }

    public Matrix scale(double x, double y, double z) {
        Matrix result = new Matrix();
        result.setValue(0, 0, x);
        result.setValue(1, 1, y);
        result.setValue(2, 2, z);
        return result;
    }

    public Matrix shearXByY(double f) {
        Matrix result = new Matrix();
        result.setValue(0, 1, f);
        return result;
    }

    public Matrix shearXByZ(double f) {
        Matrix result = new Matrix();
        result.setValue(0, 2, f);
        return result;
    }

    public Matrix shearYByX(double f) {
        Matrix result = new Matrix();
        result.setValue(1, 0, f);
        return result;
    }

    public Matrix shearYByZ(double f) {
        Matrix result = new Matrix();
        result.setValue(1, 2, f);
        return result;
    }

    public Matrix shearZByX(double f) {
        Matrix result = new Matrix();
        result.setValue(2, 0, f);
        return result;
    }

    public Matrix shearZByY(double f) {
        Matrix result = new Matrix();
        result.setValue(2, 1, f);
        return result;
    }

    public Matrix xRoll(double angle) {
        Matrix result = new Matrix();
        double c = cos(angle);
        double s = sin(angle);
        result.setValue(1, 1, c);
        result.setValue(1, 2, -s);
        result.setValue(2, 1, s);
        result.setValue(2, 2, c);
        return result;
    }

    public Matrix yRoll(double angle) {
        Matrix result = new Matrix();
        double c = cos(angle);
        double s = sin(angle);
        result.setValue(0, 0, c);
        result.setValue(0, 2, s);
        result.setValue(2, 0, -s);
        result.setValue(2, 2, c);
        return result;
    }

    public Matrix zRoll(double angle) {
        Matrix result = new Matrix();
        double c = cos(angle);
        double s = sin(angle);
        result.setValue(0, 0, c);
        result.setValue(0, 1, -s);
        result.setValue(1, 0, s);
        result.setValue(1, 1, c);
        return result;
    }

    public Matrix rotateAroundAxis(double angle, Vector axis) {
        double c = cos(angle);
        double s = sin(angle);
        double ux = axis.getValues()[0];
        double uy = axis.getValues()[1];
        double uz = axis.getValues()[2];
        double[][] values = {{c + (1 - c) * ux * ux, (1 - c) * uy * ux - s * uz, (1 - c) * uz * ux + s * uy, 0}, {(1 - c) * ux * uy + s * uz, c + (1 - c) * uy * uy, (1 - c) * uz * uy - s * ux, 0}, {(1 - c) * ux * uz - s * uy, (1 - c) * uy * uz + s * ux, c + (1 - c) * uz * uz, 0}, {0, 0, 0, 1}};
        return new Matrix(values);
    }
}
