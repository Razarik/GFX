public class Ray {
    private Point origin;
    private Vector direction;
    private double distance;
    private int screenWidth;
    private int screenHeight;


    public Ray(Point origin, Vector viewDirection, Vector u, Vector v, double distance, int screenWidth, int screenHeight, int row, int column) {
        this.origin = origin;
        this.distance = distance;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        Vector n = viewDirection.multiplyElement(-distance);
        double uMultiplier = (screenWidth / 2.0) * (2.0 * column / screenWidth - 1);
        u = u.multiplyElement(uMultiplier);
        double vMultiplier = (screenHeight / 2.0) * (2.0 * row / screenHeight - 1);
        v = v.multiplyElement(vMultiplier);
        this.direction = n.add(u).add(v);
    }

    public Ray(Point origin, Vector direction){
        this.origin = origin;
        this.direction = direction;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void print(){
        System.out.println("Eye at: ("+ origin.getValues()[0]+", "+ origin.getValues()[1]+", "+ origin.getValues()[2]+")");
        System.out.println("Ray direction: ("+direction.getValues()[0]+", "+direction.getValues()[1]+", "+direction.getValues()[2]+")");
    }
}
