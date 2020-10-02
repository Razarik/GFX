public class Vector {
    private int[] values = new int[4];

    public Vector(int x, int y, int z) {
        values[0] = x;
        values[1] = y;
        values[2] = z;
        values[3] = 0;
    }

    public Vector(int[] values) {
        this.values = values;
        values[3] = 0;
    }
}
