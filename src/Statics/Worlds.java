package Statics;

import Calculations.Camera;
import Calculations.Point;
import Calculations.TransformationFactory;
import Drawing.World;
import Light.Colour;
import Light.LightSource;
import Objects.*;
import Objects.Boolean.DifferenceBool;
import Objects.Boolean.UnionBool;
import Objects.Object;

import java.util.ArrayList;

public class Worlds {
    public static World createBoxedWorld() {
        Point eye = new Point(15, 15, 15);
        Camera camera = new Camera(eye, new Point(0, 0, 0), 0, 1000);
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<LightSource> lightSources = new ArrayList<>();
        TransformationFactory tf = new TransformationFactory();

        //Lights
        lightSources.add(new LightSource(new Point(10, -10, 15), new Colour(0.7, 0.7, 0.7), new Colour(0.3, 0.3, 0.3)));

        //Objects
        Cube bigCube = new Cube(Materials.COPPER);
        bigCube.transform(tf.translate(0, 0, 10).multiply(tf.scale(20, 20, 20)), tf.inverseScale(20, 20, 20).multiply(tf.inverseTranslate(0, 0, 10)));
        objects.add(bigCube);
        Plane groundplane = new Plane(Materials.OBSIDIAN);
        groundplane.transform(tf.translate(0, 0, -9), tf.inverseTranslate(0, 0, -9));
        objects.add(groundplane);

        return new World(camera, objects, lightSources);
    }

    public static World createWaterBox() {
        World world = createBoxedWorld();
        TransformationFactory tf = new TransformationFactory();

        Water water = new Water(Materials.WATER);
        water.transform(tf.translate(0, 0, 5).multiply(tf.scale(20, 20, 1)), tf.inverseScale(20, 20, 1).multiply(tf.inverseTranslate(0, 0, 5)));
        water.setxWaves(10);
        water.setyWaves(5);
        water.setAmplitude(7);
        world.addObject(water);

        Cube floatingBox = new Cube(Materials.JADE);
        floatingBox.transform(tf.translate(0, 0, 5).multiply(tf.scale(3, 3, 3)), tf.inverseScale(3, 3, 3).multiply(tf.inverseTranslate(0, 0, 5)));
        world.addObject(floatingBox);

        return world;
    }

    public static World createBooleanWorld() {
        World world = createBoxedWorld();
        TransformationFactory tf = new TransformationFactory();

        Sphere dome = new Sphere(Materials.GOLD);
        dome.transform(tf.translate(0, 0, 5).multiply(tf.scale(3, 3, 3)), tf.inverseScale(3, 3, 3).multiply(tf.inverseTranslate(0, 0, 5)));
        Cube block = new Cube(Materials.BRASS);
        block.transform(tf.translate(0, 0, 2).multiply(tf.scale(3, 3, 3)), tf.inverseScale(3, 3, 3).multiply(tf.inverseTranslate(0, 0, 2)));
        UnionBool domedBlock = new UnionBool(dome, block);

        Cylinder hole = new Cylinder(Materials.CHROME);
        hole.transform(
                tf.yRoll(Math.toRadians(45))
                        .multiply(tf.translate(0, 0, -7.5))
                        .multiply(tf.scale(1, 1, 15)),
                tf.inverseScale(1, 1, 15)
                        .multiply(tf.inverseTranslate(0, 0, -7.5))
                        .multiply(tf.inverseYRoll(Math.toRadians(45))));

        DifferenceBool holeThroughBlock = new DifferenceBool(domedBlock, hole);
        world.setCamera(new Camera(new Point(15,12,15), new Point(0,0,0), 0, 1000));
        world.addObject(holeThroughBlock);
        world.addLightSource(new LightSource(new Point(10,0,10), new Colour(0.7,0.7,0.7), new Colour(0.1,0.1,0.1)));
        return world;
    }
}
