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
}
