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
        Cube bigCube = new Cube(Materials.WHITERUBBER);
        bigCube.transform(tf.translate(0, 0, 10).multiply(tf.scale(20, 20, 20)), tf.inverseScale(20, 20, 20).multiply(tf.inverseTranslate(0, 0, 10)));
        objects.add(bigCube);
        Plane groundplane = new Plane(Materials.REDRUBBER);
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
        world.setCamera(new Camera(new Point(15, 12, 15), new Point(0, 0, 0), 0, 1000));
        world.addObject(holeThroughBlock);
        world.addLightSource(new LightSource(new Point(10, 0, 10), new Colour(0.7, 0.7, 0.7), new Colour(0.1, 0.1, 0.1)));
        return world;
    }

    public static World createGlassCup() {
        World world = createBoxedWorld();
        TransformationFactory tf = new TransformationFactory();

        world.addLightSource(new LightSource(new Point(10, 0, 10), new Colour(0.7, 0.7, 0.7), new Colour(0.1, 0.1, 0.1)));

        Cylinder outerGlass = new Cylinder(Materials.GLASS);
        outerGlass.transform(tf.scale(2, 2, 5), tf.inverseScale(2, 2, 5));
        Cylinder innerGlass = new Cylinder(Materials.GLASS);
        innerGlass.transform(tf.translate(0, 0, 0.3).multiply(tf.scale(1.7, 1.7, 5)), tf.inverseScale(1.7, 1.7, 5).multiply(tf.inverseTranslate(0, 0, 0.3)));
        DifferenceBool glass = new DifferenceBool(outerGlass, innerGlass);
        world.addObject(glass);

        Cylinder water = new Cylinder(Materials.WATER);
        water.transform(tf.translate(0, 0, 0.300000001).multiply(tf.scale(1.699999999, 1.699999999, 4)), tf.inverseScale(1.699999999, 1.699999999, 4).multiply(tf.inverseTranslate(0, 0, 0.300000001)));
        world.addObject(water);

        Cylinder straw = new Cylinder(Materials.GREENPLASTIC);
        straw.transform(tf.translate(0, 0, 0.301)
                        .multiply(tf.xRoll(Math.toRadians(15)))
                        .multiply(tf.scale(0.15, 0.15, 6)),
                tf.inverseScale(0.15, 0.15, 6)
                        .multiply(tf.inverseXRoll(Math.toRadians(15))
                                .multiply(tf.inverseTranslate(0, 0, 0.301)))
        );
        world.addObject(straw);

        Cube tableTop = new Cube(Materials.YELLOWPLASTIC);
        tableTop.transform(tf.translate(0, 0, -0.20001)
                        .multiply(tf.scale(6.5, 9, 0.2)),
                tf.inverseScale(6.5, 9, 0.2)
                        .multiply(tf.inverseTranslate(0, 0, -0.20001)));
        Cylinder tableLeg1 = new Cylinder(Materials.YELLOWPLASTIC);
        tableLeg1.transform(tf.translate(5.5, 8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(5.5, 8, -9)));
        Cylinder tableLeg2 = new Cylinder(Materials.YELLOWPLASTIC);
        tableLeg2.transform(tf.translate(-5.5, 8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(-5.5, 8, -9)));
        Cylinder tableLeg3 = new Cylinder(Materials.YELLOWPLASTIC);
        tableLeg3.transform(tf.translate(-5.5, -8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(-5.5, -8, -9)));
        Cylinder tableLeg4 = new Cylinder(Materials.YELLOWPLASTIC);
        tableLeg4.transform(tf.translate(5.5, -8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(5.5, -8, -9)));

        UnionBool oneLegTable = new UnionBool(tableTop, tableLeg1);
        UnionBool twoLegTable = new UnionBool(oneLegTable, tableLeg2);
        UnionBool threeLegTable = new UnionBool(twoLegTable, tableLeg3);
        UnionBool table = new UnionBool(threeLegTable, tableLeg4);

        world.addObject(table);

        world.setCamera(new Camera(new Point(15, 15, 8), new Point(0, 0, 2), 0, 1000));

        return world;
    }

    public static World reflectionRefraction(){
        World world = createBoxedWorld();
        TransformationFactory tf = new TransformationFactory();

        Cube testCube = new Cube(Materials.GOLD);
        testCube.transform(tf.scale(3,3,3), tf.inverseScale(3,3,3));
        world.addObject(testCube);

        Sphere toReflect = new Sphere(Materials.GREENRUBBER);
        toReflect.transform(tf.translate(5,0,0), tf.inverseTranslate(5,0,0));
        world.addObject(toReflect);

        return world;
    }
}
