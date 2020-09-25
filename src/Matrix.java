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

    public double getValue(int x, int y){
        return values[x][y];
    }

    public void setValue(int x, int y, double value) {
        values[x][y] = value;
    }

    public void setToIdentity() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (x == y) {
                    values[x][y] = 1.0;
                } else {
                    values[x][y] = 0.0;
                }
            }
        }
    }

    public void setTo(double[][] values) {
        this.values = values;
    }

    public Matrix multiply(Matrix matrix) {
        Matrix result = new Matrix();
        for (int x=0; x<4; x++){
            for (int y=0; y<4; y++){
                double sum =0;
                for (int i=0; i<4; i++){
                    sum+=values[x][i]*matrix.getValue(i, y);
                }
                result.setValue(x,y, sum);
            }
        }
        return result;
    }

    public void print(){
        for (int x =0; x<4; x++){
            for (int y=0;y<4;y++){
                System.out.print(values[x][y]+" ");
            }
            System.out.println();
        }
    }
}
