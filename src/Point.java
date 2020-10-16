public class Point {
    private double[] values = new double[4];

    public Point(double x, double y, double z) {
        values[0] = x;
        values[1] = y;
        values[2] = z;
        values[3] = 1;
    }

    public Point(double[] values) {
        this.values = values;
        values[3] = 1;
    }

    public Point() {
        this.values = new double[]{0.0, 0.0, 0.0, 1.0};
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

    public Point add(Vector v) {
        return new Point(this.values[0] + v.getValues()[0], this.values[1] + v.getValues()[1], this.values[2] + v.getValues()[2]);
    }

    public Vector subtract(Point p) {
        return new Vector(this.values[0] - p.getValues()[0], this.values[1] - p.getValues()[1], this.values[2] - p.getValues()[2]);
    }

    public double norm() {
        return Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
    }
}
