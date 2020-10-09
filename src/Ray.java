public class Ray {
    private Vector eye;
    private Vector direction;

    public Ray(Vector eye, double distance, int screenWidth, int screenHeight, int row, int column) {
        this.eye = eye;
        double H = screenHeight/2;
        double W = screenWidth/2;
        direction = new Vector(-distance, W * (((2.0 * column) / screenWidth) - 1), H * (((2.0 * row) / screenHeight) - 1));
    }

    /*public Vector getHitPoint(Object o){

    }*/
}
