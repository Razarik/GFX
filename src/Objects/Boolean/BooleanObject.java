package Objects.Boolean;

import Calculations.Intersection;
import Calculations.Ray;
import Objects.Object;

import java.util.ArrayList;

public class BooleanObject extends Object {
    private Object left;
    private Object right;

    public BooleanObject(){
        super(null);
        left = null;
        right = null;
    }

    public void getHit(Ray ray, ArrayList<Intersection> intersections){

    }
}
