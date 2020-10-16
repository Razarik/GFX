public class Vector {
    private double[] values = new double[4];

    public Vector(double x, double y, double z) {
        values[0] = x;
        values[1] = y;
        values[2] = z;
        values[3] = 0;
    }

    public Vector(double[] values) {
        this.values = values;
        values[3] = 0;
    }

    public Vector() {
        this.values = new double[]{0.0, 0.0, 0.0, 0.0};
    }

    public Vector(Point p1, Point p2) {
        values[0] = p1.getValues()[0] - p2.getValues()[0];
        values[1] = p1.getValues()[1] - p2.getValues()[1];
        values[2] = p1.getValues()[2] - p2.getValues()[2];
        values[3] = 0;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public void setValue(int index, double value) {
        values[index] = value;
    }

    public void print() {
        for (int i = 0; i < 4; i++) {
            System.out.println(values[i]);
        }
    }

    public Vector add(Vector v) {
        return new Vector(this.values[0] + v.getValues()[0], this.values[1] + v.getValues()[1], this.values[2] + v.getValues()[2]);
    }

    public Vector multiplyElement(double e) {
        return new Vector(this.values[0] * e, this.values[1] * e, this.values[2] * e);
    }

    public Vector crossProduct(Vector v) {
        return new Vector(this.values[1] * v.getValues()[2] - this.values[2] * v.getValues()[1], this.values[2] * v.getValues()[0] - this.values[0] * v.getValues()[2], this.values[0] * v.getValues()[1] - this.values[1] * v.getValues()[0]);
    }

    public double norm() {
        return Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
    }

    public Vector normalise() {
        return new Vector(values[0] / norm(), values[1] / norm(), values[2] / norm());
    }

    public double multiply(Point p){
        return values[0]*p.getValues()[0]+values[1]*p.getValues()[1]+values[2]*p.getValues()[2];
    }
}
