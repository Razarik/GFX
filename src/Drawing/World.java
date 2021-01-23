package Drawing;

import Calculations.Camera;
import Calculations.Intersection;
import Calculations.Ray;
import Calculations.TransformationFactory;
import Light.Colour;
import Light.LightSource;
import Objects.Cylinder;
import Objects.Object;
import Statics.Globals;
import Statics.Materials;

import java.util.ArrayList;

public class World {
    private Camera camera;
    private ArrayList<Object> objects;
    private ArrayList<LightSource> lightSources;
    private Colour ambient;

    public World(Camera camera, ArrayList<Object> objects, ArrayList<LightSource> lightSources) {
        this.camera = camera;
        this.objects = objects;
        this.lightSources = lightSources;
        this.ambient = new Colour(0, 0, 0);
        for (LightSource light : this.lightSources) {
            this.ambient = this.ambient.add(light.getAmbientContribution());
        }
    }

    public World() {
        this.camera = null;
        this.objects = new ArrayList<>();
        this.lightSources = new ArrayList<>();
        this.ambient = new Colour(0, 0, 0);
    }

    public void addObject(Object object) {
        this.objects.add(object);
    }

    public void addLightSource(LightSource lightSource) {
        this.lightSources.add(lightSource);
        this.ambient = this.ambient.add(lightSource.getAmbientContribution());
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return camera;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public ArrayList<LightSource> getLightSources() {
        return lightSources;
    }

    public Colour getAmbient() {
        return ambient;
    }

    public ArrayList<Intersection> getHits(Ray ray) {
        ArrayList<Intersection> hits = new ArrayList<>();
        for (Object object : objects) {
            object.getHit(ray, hits);
        }
        return hits;
    }

    public Intersection bestIntersection(Ray ray) {
        ArrayList<Intersection> hits = new ArrayList<>();
        for (Object object : objects) {
            object.getHit(ray, hits);
        }
        Intersection bestIntersection = null;
        for (Intersection intersection : hits) {
            if ((bestIntersection == null && intersection.getHitTime() > Globals.ERROR) || (intersection.getHitTime() > Globals.ERROR && intersection.getHitTime() < bestIntersection.getHitTime())) {
                bestIntersection = intersection;
            }
        }
        return bestIntersection;
    }

    public void addAxes(double length){
        TransformationFactory tf = new TransformationFactory();
        Cylinder z = new Cylinder(Materials.BLUEAXIS);
        z.transform(tf.scale(0.1, 0.1, length), tf.inverseScale(0.1, 0.1, length));
        objects.add(z);
        Cylinder x = new Cylinder(Materials.REDAXIS);
        x.transform(tf.scale(length, 0.1, 0.1).multiply(tf.yRoll(Math.toRadians(90))), tf.inverseYRoll(Math.toRadians(90)).multiply(tf.inverseScale(length, 0.1, 0.1)));
        objects.add(x);
        Cylinder y = new Cylinder(Materials.GREENAXIS);
        y.transform(tf.scale(0.1, length, 0.1).multiply(tf.xRoll(Math.toRadians(-90))), tf.inverseXRoll(Math.toRadians(-90)).multiply(tf.inverseScale(0.1, length, 0.1)));
        objects.add(y);
    }

}
