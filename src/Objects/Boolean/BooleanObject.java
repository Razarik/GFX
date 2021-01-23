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
}
