package Objects.Boolean;

import Calculations.Intersection;
import Calculations.Ray;
import Objects.Object;

import java.util.ArrayList;
import java.util.Collections;

public class DifferenceBool extends BooleanObject {

    private boolean homogenous;

    public DifferenceBool(Object left, Object right) {
        super();
        this.left = left;
        this.right = right;
        this.homogenous = false;
    }

    public DifferenceBool(Object left, Object right, boolean homogenous) {
        super();
        this.left = left;
        this.right = right;
        this.homogenous = homogenous;
    }

    @Override
    public void getHit(Ray ray, ArrayList<Intersection> intersections) {
        ArrayList<Intersection> leftIntersections = new ArrayList<>();
        ArrayList<Intersection> rightIntersections = new ArrayList<>();
        if (left != null) {
            left.getHit(ray, leftIntersections);
        }
        if (right != null) {
            right.getHit(ray, rightIntersections);
        }
        if (!leftIntersections.isEmpty()) {
            Collections.sort(leftIntersections);
            Collections.sort(rightIntersections);
            boolean leftInside, rightInside;
            if (leftIntersections.isEmpty()) {
                leftInside = false;
            } else {
                leftInside = !leftIntersections.get(0).isEntering();
            }
            if (rightIntersections.isEmpty()) {
                rightInside = false;
            } else {
                rightInside = !rightIntersections.get(0).isEntering();
            }
            boolean combinedInside = leftInside && !rightInside;
            while (!leftIntersections.isEmpty() && !rightIntersections.isEmpty()) {
                Intersection leftIntersection = leftIntersections.get(0);
                Intersection rightIntersection = rightIntersections.get(0);
                Intersection toAdd;
                if (leftIntersection.getHitTime() <= rightIntersection.getHitTime()) {
                    leftInside = leftIntersection.isEntering();
                    toAdd = leftIntersection;
                    leftIntersections.remove(0);
                } else {
                    rightInside = rightIntersection.isEntering();
                    toAdd = rightIntersection;
                    if (homogenous) {
                        Object realHit = this.left;
                        while (realHit instanceof BooleanObject) {
                            realHit = ((BooleanObject) realHit).getLeft();
                        }
                        toAdd.setHitObject(realHit);
                    }
                    rightIntersections.remove(0);
                }
                if (combinedInside != (leftInside && !rightInside)) {
                    combinedInside = leftInside && !rightInside;
                    toAdd.setEntering(combinedInside);
                    intersections.add(toAdd);
                }
            }
            if (!leftIntersections.isEmpty()) {
                for (Intersection intersection : leftIntersections) {
                    if (combinedInside != (intersection.isEntering() && !rightInside)) {
                        combinedInside = intersection.isEntering();
                        intersections.add(intersection);
                    }
                }
            }
        }
    }
}
