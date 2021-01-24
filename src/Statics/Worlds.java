package Statics;

import Calculations.Camera;
import Calculations.Point;
import Calculations.TransformationFactory;
import Drawing.World;
import Light.Colour;
import Light.LightSource;
import Light.Material;
import Objects.*;
import Objects.Boolean.DifferenceBool;
import Objects.Boolean.IntersectionBool;
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

        LightSource light = new LightSource(new Point(15, 15, 15), new Colour(0.7, 0.7, 0.7), new Colour(0.3, 0.3, 0.3));
        world.addLightSource(light);

        Water water = new Water(Materials.WATER);
        water.transform(tf.translate(0, 0, 5).multiply(tf.scale(20, 20, 1)), tf.inverseScale(20, 20, 1).multiply(tf.inverseTranslate(0, 0, 5)));
        water.setxWaves(10);
        water.setyWaves(5);
        water.setAmplitude(7);

        Cube floatingBox = new Cube(Materials.WOOD);
        floatingBox.transform(tf.translate(7, 5, 5)
                        .multiply(tf.xRoll(Math.toRadians(-10)))
                        .multiply(tf.scale(1.5, 1.5, 1.5)),
                tf.inverseScale(1.5, 1.5, 1.5)
                        .multiply(tf.inverseXRoll(Math.toRadians(-10)))
                        .multiply(tf.inverseTranslate(7, 5, 5)));
        world.addObject(floatingBox);

        Cylinder canoeBase = new Cylinder(Materials.WOOD);
        canoeBase.transform(tf.translate(0, 7.5, 5)
                        .multiply(tf.xRoll(Math.toRadians(90)))
                        .multiply(tf.scale(2.5, 2.5, 15)),
                tf.inverseScale(2.5, 2.5, 15)
                        .multiply(tf.inverseXRoll(Math.toRadians(90)))
                        .multiply(tf.inverseTranslate(0, 7.5, 5)));
        Cube top = new Cube(Materials.WOOD);
        top.transform(tf.translate(0, 0, 7.5)
                        .multiply(tf.scale(2, 7.6, 1)),
                tf.inverseScale(2, 7.6, 1)
                        .multiply(tf.inverseTranslate(0, 0, 7.5)));
        DifferenceBool canoeCut = new DifferenceBool(canoeBase, top);
        Cylinder inside = new Cylinder(Materials.WOOD);
        inside.transform(tf.translate(0, 6, 5)
                        .multiply(tf.xRoll(Math.toRadians(90)))
                        .multiply(tf.scale(2, 2, 12)),
                tf.inverseScale(2, 2, 12)
                        .multiply(tf.inverseXRoll(Math.toRadians(90)))
                        .multiply(tf.inverseTranslate(0, 6, 5)));
        DifferenceBool canoe = new DifferenceBool(canoeCut, inside);
        world.addObject(canoe);

        DifferenceBool waterWithCanoe = new DifferenceBool(water, canoeBase);
        world.addObject(waterWithCanoe);

        Camera camera = new Camera(new Point(19, 19, 19), new Point(0, 0, 0), 0, 1000);
        world.setCamera(camera);

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

        Cube tableTop = new Cube(Materials.WOOD);
        tableTop.transform(tf.translate(0, 0, -0.20001)
                        .multiply(tf.xRoll(Math.toRadians(90)))
                        .multiply(tf.scale(6.5, 0.2, 9)),
                tf.inverseScale(6.5, 0.2, 9)
                        .multiply(tf.inverseXRoll(Math.toRadians(90)))
                        .multiply(tf.inverseTranslate(0, 0, -0.20001)));
        Cylinder tableLeg1 = new Cylinder(Materials.WOOD);
        tableLeg1.transform(tf.translate(5.5, 8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(5.5, 8, -9)));
        Cylinder tableLeg2 = new Cylinder(Materials.WOOD);
        tableLeg2.transform(tf.translate(-5.5, 8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(-5.5, 8, -9)));
        Cylinder tableLeg3 = new Cylinder(Materials.WOOD);
        tableLeg3.transform(tf.translate(-5.5, -8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(-5.5, -8, -9)));
        Cylinder tableLeg4 = new Cylinder(Materials.WOOD);
        tableLeg4.transform(tf.translate(5.5, -8, -9)
                        .multiply(tf.scale(0.5, 0.5, 8.6)),
                tf.inverseScale(0.5, 0.5, 8.6)
                        .multiply(tf.inverseTranslate(5.5, -8, -9)));

        UnionBool oneLegTable = new UnionBool(tableTop, tableLeg1);
        UnionBool twoLegTable = new UnionBool(oneLegTable, tableLeg2);
        UnionBool threeLegTable = new UnionBool(twoLegTable, tableLeg3);
        UnionBool table = new UnionBool(threeLegTable, tableLeg4);

        world.addObject(table);

        world.setCamera(new Camera(new Point(18, -7.5, 15), new Point(0, 0, 2), 0, 1000));

        return world;
    }

    public static World reflectionRefraction() {
        World world = createBoxedWorld();
        TransformationFactory tf = new TransformationFactory();

        Cube testCube = new Cube(Materials.GOLD);
        testCube.transform(tf.scale(3, 3, 3), tf.inverseScale(3, 3, 3));
        world.addObject(testCube);

        Sphere toReflect = new Sphere(Materials.GREENRUBBER);
        toReflect.transform(tf.translate(5, 0, 0), tf.inverseTranslate(5, 0, 0));
        world.addObject(toReflect);

        return world;
    }

    public static World createCoverWorld() {
        Point eye = new Point(15, 15, 20);
        Camera camera = new Camera(eye, new Point(0, 0, 0), 0, 1000);
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<LightSource> lightSources = new ArrayList<>();
        TransformationFactory tf = new TransformationFactory();

        LightSource light1 = new LightSource(new Point(-10, 5, 20), new Colour(0.5, 0.5, 0.5), new Colour(0.4, 0.4, 0.4));
        lightSources.add(light1);

        LightSource light2 = new LightSource(new Point(-10, -5, 20), new Colour(0.4, 0.4, 0.4), new Colour(0.07, 0.07, 0.07));
        lightSources.add(light2);

        LightSource light3 = new LightSource(new Point(10, 10, 10), new Colour(0.3, 0.3, 0.3), new Colour(0.1, 0.1, 0.1));
        lightSources.add(light3);

        Cylinder border = new Cylinder(Materials.BLUEPLASTIC);
        border.transform(tf.translate(0, 0, -1)
                        .multiply(tf.scale(30, 30, 30)),
                tf.inverseScale(30, 30, 30)
                        .multiply(tf.inverseTranslate(0, 0, -1)));
        objects.add(border);

        Plane ground = new Plane(Materials.YELLOWPLASTIC);
        objects.add(ground);

        Sphere bigBall = new Sphere(Materials.CHROME);
        bigBall.transform(tf.translate(0, 0, 5)
                        .multiply(tf.scale(5, 5, 5)),
                tf.inverseScale(5, 5, 5)
                        .multiply(tf.inverseTranslate(0, 0, 5)));
        objects.add(bigBall);

        Sphere smallBall1 = new Sphere(Materials.SILVER);
        smallBall1.transform(tf.translate(0, 4.898979486, 10)
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 4.898979486, 10)));
        objects.add(smallBall1);

        Sphere smallBall2 = new Sphere(Materials.SILVER);
        smallBall2.transform(tf.zRoll(Math.toRadians(120))
                        .multiply(tf.translate(0, 4.898979486, 10))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 4.898979486, 10))
                        .multiply(tf.inverseZRoll(Math.toRadians(120))));
        objects.add(smallBall2);

        Sphere smallBall3 = new Sphere(Materials.SILVER);
        smallBall3.transform(tf.zRoll(Math.toRadians(240))
                        .multiply(tf.translate(0, 4.898979486, 10))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 4.898979486, 10))
                        .multiply(tf.inverseZRoll(Math.toRadians(240))));
        objects.add(smallBall3);

        Sphere smallBall4 = new Sphere(Materials.COPPER);
        smallBall4.transform(tf.zRoll(Math.toRadians(0))
                        .multiply(tf.translate(0, 6.32455532, 2))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 6.32455532, 2))
                        .multiply(tf.inverseZRoll(Math.toRadians(0))));
        objects.add(smallBall4);

        Sphere smallBall5 = new Sphere(Materials.COPPER);
        smallBall5.transform(tf.zRoll(Math.toRadians(60))
                        .multiply(tf.translate(0, 6.32455532, 2))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 6.32455532, 2))
                        .multiply(tf.inverseZRoll(Math.toRadians(60))));
        objects.add(smallBall5);

        Sphere smallBall6 = new Sphere(Materials.COPPER);
        smallBall6.transform(tf.zRoll(Math.toRadians(120))
                        .multiply(tf.translate(0, 6.32455532, 2))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 6.32455532, 2))
                        .multiply(tf.inverseZRoll(Math.toRadians(120))));
        objects.add(smallBall6);

        Sphere smallBall7 = new Sphere(Materials.COPPER);
        smallBall7.transform(tf.zRoll(Math.toRadians(180))
                        .multiply(tf.translate(0, 6.32455532, 2))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 6.32455532, 2))
                        .multiply(tf.inverseZRoll(Math.toRadians(180))));
        objects.add(smallBall7);

        Sphere smallBall8 = new Sphere(Materials.COPPER);
        smallBall8.transform(tf.zRoll(Math.toRadians(240))
                        .multiply(tf.translate(0, 6.32455532, 2))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 6.32455532, 2))
                        .multiply(tf.inverseZRoll(Math.toRadians(240))));
        objects.add(smallBall8);

        Sphere smallBall9 = new Sphere(Materials.COPPER);
        smallBall9.transform(tf.zRoll(Math.toRadians(300))
                        .multiply(tf.translate(0, 6.32455532, 2))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseTranslate(0, 6.32455532, 2))
                        .multiply(tf.inverseZRoll(Math.toRadians(300))));
        objects.add(smallBall9);

        return new World(camera, objects, lightSources);
    }

    public static World createTextureWorld() {
        World world = createBoxedWorld();
        TransformationFactory tf = new TransformationFactory();

        Cube box = new Cube(Materials.WOOD);
        box.transform(tf.translate(5, 0, 0)
                        .multiply(tf.scale(5, 5, 5)),
                tf.inverseScale(5, 5, 5)
                        .multiply(tf.inverseTranslate(5, 0, 0)));
        world.addObject(box);

        return world;
    }

    public static World createLensWorld() {
        TransformationFactory tf = new TransformationFactory();
        Point eye = new Point(10, 0, 0);
        Camera camera = new Camera(eye, new Point(0, 0, 0), 0, 1000);
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<LightSource> lightSources = new ArrayList<>();

        LightSource light = new LightSource(new Point(0, 0, 19), new Colour(0.8, 0.8, 0.8), new Colour(0.4, 0.4, 0.4));
        lightSources.add(light);

        Cube bigBox = new Cube(Materials.OBSIDIAN);
        bigBox.transform(tf.scale(30, 30, 30), tf.inverseScale(30, 30, 30));
        objects.add(bigBox);

        Sphere lensPart1 = new Sphere(Materials.GLASS);
        lensPart1.transform(tf.translate(-9, 0, 0)
                        .multiply(tf.scale(10, 10, 10)),
                tf.inverseScale(10, 10, 10)
                        .multiply(tf.inverseTranslate(-9, 0, 0)));
        Sphere lensPart2 = new Sphere(Materials.GLASS);
        lensPart2.transform(tf.translate(9, 0, 0)
                        .multiply(tf.scale(10, 10, 10)),
                tf.inverseScale(10, 10, 10)
                        .multiply(tf.inverseTranslate(9, 0, 0)));

        IntersectionBool lens = new IntersectionBool(lensPart1, lensPart2);
        objects.add(lens);

        Cylinder taperedCylinder = new Cylinder(Materials.GOLD, 0.5);
        taperedCylinder.transform(tf.translate(-28, 0, -0.5),
                tf.inverseTranslate(-28, 0, -0.5));
        objects.add(taperedCylinder);

        return new World(camera, objects, lightSources);
    }

    public static World createFullWorld() {
        TransformationFactory tf = new TransformationFactory();
        Point eye = new Point(20, 20, 20);
        Camera camera = new Camera(eye, new Point(0, 0, 0), 0, 1000);
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<LightSource> lightSources = new ArrayList<>();

        LightSource light1 = new LightSource(new Point(20, -20, 20), new Colour(0.8, 0.8, 0.8), new Colour(0.3, 0.3, 0.3));
        lightSources.add(light1);


        Cube bigCube = new Cube(Materials.YELLOWRUBBER);
        bigCube.transform(tf.scale(40, 40, 40), tf.inverseScale(40, 40, 40));
        objects.add(bigCube);

        Plane ground = new Plane(Materials.WHITERUBBER);
        objects.add(ground);

        Cube rubyCube = new Cube(Materials.RUBY);
        rubyCube.transform(tf.translate(5, -5, 4)
                        .multiply(tf.xRoll(Math.toRadians(35.26)))
                        .multiply(tf.yRoll(Math.toRadians(45)))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseYRoll(Math.toRadians(45)))
                        .multiply(tf.inverseXRoll(Math.toRadians(35.26)))
                        .multiply(tf.inverseTranslate(5, -5, 4)));
        objects.add(rubyCube);

        Cube box = new Cube(Materials.BRONZE);
        box.transform(tf.translate(-5, 5, 3)
                        .multiply(tf.zRoll(Math.toRadians(45)))
                        .multiply(tf.scale(3, 3, 3)),
                tf.inverseScale(3, 3, 3)
                        .multiply(tf.inverseZRoll(Math.toRadians(45)))
                        .multiply(tf.inverseTranslate(-5, 5, 3)));
        Sphere dome = new Sphere(Materials.BRONZE);
        dome.transform(tf.translate(-5, 5, 6)
                        .multiply(tf.scale(3, 3, 3)),
                tf.inverseScale(3, 3, 3)
                        .multiply(tf.inverseTranslate(-5, 5, 6)));
        UnionBool domedBox = new UnionBool(box, dome);
        objects.add(domedBox);

        Cube mirror = new Cube(Materials.MIRROR);
        mirror.transform(tf.translate(-39.79, 0, 15)
                        .multiply(tf.scale(0.1, 15, 15)),
                tf.inverseScale(0.1, 15, 15)
                        .multiply(tf.inverseTranslate(-39.79, 0, 15)));
        objects.add(mirror);

        World world = new World(camera, objects, lightSources);
        world.addAxes(40);

        return world;
    }

    public static World createGemWorld() {
        TransformationFactory tf = new TransformationFactory();
        Point eye = new Point(5, 10, 8);
        Camera camera = new Camera(eye, new Point(0, 0, 0), 0, 1000);
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<LightSource> lightSources = new ArrayList<>();

        LightSource light1 = new LightSource(new Point(7, -5, 8), new Colour(0.8, 0.8, 0.8), new Colour(0.3, 0.3, 0.3));
        lightSources.add(light1);


        Cube bigCube = new Cube(Materials.WHITERUBBER);
        bigCube.transform(tf.scale(11, 11, 11), tf.inverseScale(11, 11, 11));
        objects.add(bigCube);

        double xPosition = 0;
        double yPosition = 0;
        double zPosition = 0;
        Material gemMaterial = Materials.EMERALD;

        Cube gemPart1 = new Cube(gemMaterial);
        gemPart1.transform(tf.translate(xPosition, yPosition, zPosition)
                        .multiply(tf.xRoll(Math.toRadians(35.26)))
                        .multiply(tf.yRoll(Math.toRadians(45)))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseYRoll(Math.toRadians(45)))
                        .multiply(tf.inverseXRoll(Math.toRadians(35.26)))
                        .multiply(tf.inverseTranslate(xPosition, yPosition, zPosition)));

        Cube gemPart2 = new Cube(gemMaterial);
        gemPart2.transform(tf.translate(xPosition, yPosition, zPosition)
                        .multiply(tf.zRoll(Math.toRadians(90)))
                        .multiply(tf.xRoll(Math.toRadians(35.26)))
                        .multiply(tf.yRoll(Math.toRadians(45)))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseYRoll(Math.toRadians(45)))
                        .multiply(tf.inverseXRoll(Math.toRadians(35.26)))
                        .multiply(tf.inverseZRoll(Math.toRadians(90)))
                        .multiply(tf.inverseTranslate(xPosition, yPosition, zPosition)));


        Cube gemPart3 = new Cube(gemMaterial);
        gemPart3.transform(tf.translate(xPosition, yPosition, zPosition)
                        .multiply(tf.zRoll(Math.toRadians(180)))
                        .multiply(tf.xRoll(Math.toRadians(35.26)))
                        .multiply(tf.yRoll(Math.toRadians(45)))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseYRoll(Math.toRadians(45)))
                        .multiply(tf.inverseXRoll(Math.toRadians(35.26)))
                        .multiply(tf.inverseZRoll(Math.toRadians(180)))
                        .multiply(tf.inverseTranslate(xPosition, yPosition, zPosition)));

        Cube gemPart4 = new Cube(gemMaterial);
        gemPart4.transform(tf.translate(xPosition, yPosition, zPosition)
                        .multiply(tf.zRoll(Math.toRadians(270)))
                        .multiply(tf.xRoll(Math.toRadians(35.26)))
                        .multiply(tf.yRoll(Math.toRadians(45)))
                        .multiply(tf.scale(2, 2, 2)),
                tf.inverseScale(2, 2, 2)
                        .multiply(tf.inverseYRoll(Math.toRadians(45)))
                        .multiply(tf.inverseXRoll(Math.toRadians(35.26)))
                        .multiply(tf.inverseZRoll(Math.toRadians(270)))
                        .multiply(tf.inverseTranslate(xPosition, yPosition, zPosition)));

        IntersectionBool gemPart12 = new IntersectionBool(gemPart1, gemPart2);
        IntersectionBool gemPart34 = new IntersectionBool(gemPart3, gemPart4);
        IntersectionBool gemUncut = new IntersectionBool(gemPart12, gemPart34);

        Cube gemCutTop = new Cube(gemMaterial);
        gemCutTop.transform(tf.translate(xPosition, yPosition, zPosition + 5)
                        .multiply(tf.scale(4, 4, 4)),
                tf.inverseScale(4, 4, 4)
                        .multiply(tf.inverseTranslate(xPosition, yPosition, zPosition + 5)));

        DifferenceBool gem = new DifferenceBool(gemUncut, gemCutTop);

        Material ringMaterial = Materials.GOLD;

        Sphere uncutBand = new Sphere(ringMaterial);
        uncutBand.transform(tf.translate(0, 0, -4.41)
                        .multiply(tf.scale(3, 3, 3)),
                tf.inverseScale(3, 3, 3)
                        .multiply(tf.inverseTranslate(0, 0, -4.41)));

        UnionBool gemOnAnOrb = new UnionBool(gem, uncutBand);

        Cylinder hole = new Cylinder(ringMaterial);
        hole.transform(tf.translate(0, 3.5, -4.41)
                        .multiply(tf.xRoll(Math.toRadians(90)))
                        .multiply(tf.scale(2.7, 2.7, 7)),
                tf.inverseScale(2.7, 2.7, 7)
                        .multiply(tf.inverseXRoll(Math.toRadians(90)))
                        .multiply(tf.inverseTranslate(0, 3.5, -4.41)));

        DifferenceBool ring = new DifferenceBool(gemOnAnOrb, hole);

        objects.add(ring);


        return new World(camera, objects, lightSources);
    }
}
