import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        double[][] values = {{5, 7, 9, 10}, {2, 3, 3, 8}, {8, 10, 2, 3}, {3, 3, 4, 8}};
        Matrix a = new Matrix(values);
        values = new double[][]{{3, 10, 12, 18}, {12, 1, 4, 9}, {9, 10, 12, 2}, {3, 12, 4, 10}};
        Matrix b = new Matrix(values);
        a.print();
        System.out.println("Multiplied by");
        b.print();
        Matrix c = a.multiply(b);
        System.out.println("Gives");
        c.print();
        /*Matrix d = b.multiply(a);
        System.out.println();
        d.print();
        Vector v = new Vector(3, 2, -5);
        Vector result = a.multiply(v);
        System.out.println();
        result.print();*/


        /*TestDrawPoint point = new TestDrawPoint();
        JFrame frame = new JFrame("Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(point);
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/


    }

}
