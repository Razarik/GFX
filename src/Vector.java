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
        values[0] = p1.getX() - p2.getX();
        values[1] = p1.getY() - p2.getY();
        values[2] = p1.getZ() - p2.getZ();
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
        return new Vector(this.values[0] + v.getX(), this.values[1] + v.getY(), this.values[2] + v.getZ());
    }

    public Vector multiplyElement(double e) {
        return new Vector(this.values[0] * e, this.values[1] * e, this.values[2] * e);
    }

    public Vector crossProduct(Vector v) {
        return new Vector(this.values[1] * v.getZ() - this.values[2] * v.getY(), this.values[2] * v.getX() - this.values[0] * v.getZ(), this.values[0] * v.getY() - this.values[1] * v.getX());
    }

    public double norm() {
        return Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
    }

    public Vector normalise() {
        return new Vector(values[0] / norm(), values[1] / norm(), values[2] / norm());
    }

    public double multiply(Point p){
        return values[0]*p.getX()+values[1]*p.getY()+values[2]*p.getZ();
    }

    public double getX() {
        return values[0];
    }

    public double getY() {
        return values[1];
    }

    public double getZ() {
        return values[2];
    }
}
