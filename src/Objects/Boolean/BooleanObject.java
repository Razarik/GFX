package Objects.Boolean;

import Calculations.Intersection;
import Calculations.Ray;
import Objects.Object;

import java.util.ArrayList;

public class BooleanObject extends Object {
    protected Object left;
    protected Object right;

    public BooleanObject(){
        super(null);
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections){

    }

    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = right;
    }


}
