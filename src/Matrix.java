public class Matrix {
    private double[][] values = new double[4][4];

    public Matrix() {
        setToIdentity();
    }

    public Matrix(double[][] values) {
        this.values = values;
    }

    public double[][] getValues() {
        return values;
    }

    public double getValue(int row, int column) {
        return values[row][column];
    }

    public void setValue(int row, int column, double value) {
        values[row][column] = value;
    }

    public void setToIdentity() {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (row == column) {
                    values[row][column] = 1.0;
                } else {
                    values[row][column] = 0.0;
                }
            }
        }
    }

    public void setTo(double[][] values) {
        this.values = values;
    }

    public Matrix multiply(Matrix matrix) {
        Matrix result = new Matrix();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0;
                for (int i = 0; i < 4; i++) {
                    sum += values[row][i] * matrix.getValue(i, column);
                }
                result.setValue(row, column, sum);
            }
        }
        return result;
    }

    public Matrix add(Matrix matrix){
        Matrix result = new Matrix();
        for (int row=0; row<4; row++){
            for (int column =0; column<4; column++){
                result.setValue(row, column, values[row][column]+matrix.getValue(row, column));
            }
        }
        return result;
    }

    public Vector multiply(Vector vector) {
        Vector result = new Vector();
        for (int row = 0; row < 4; row++) {
            double sum = 0;
            for (int i = 0; i < 4; i++) {
                sum += values[row][i] * vector.getValues()[i];
            }
            result.setValue(row, sum);
        }
        return result;
    }

    public void print() {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                System.out.print(values[row][column] + " ");
            }
            System.out.println();
        }
    }
}
