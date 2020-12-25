package Objects.Boolean;

import Calculations.Intersection;
import Calculations.Ray;

import java.util.ArrayList;

public class DifferenceBool extends BooleanObject{
    public DifferenceBool(){
        super();
    }

    @Override
    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        super.getHit(ray, intersections);
    }
}
