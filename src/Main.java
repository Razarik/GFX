public class Main {

    public static void main(String[] args) {
        double[][] values ={{1,0,4, -4}, {-1,1,2,6},{0,-1,0.5, -7},{0,0,0,0}};
        Matrix a = new Matrix(values);
        values= new double[][]{{1, 5, 3, 0}, {-0.2, 3, 9, -17}, {0.8, 0.01, 2, -5}, {3, 1, 1, 1}};
        Matrix b = new Matrix(values);
        a.print();
        b.print();
        Matrix c = a.multiply(b);
        System.out.println();
        c.print();
        Matrix d = b.multiply(a);
        System.out.println();
        d.print();
    }

}
