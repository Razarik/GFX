package Statics;

import Light.Colour;
import Light.Material;
import Textures.WoodGrain;

public class Materials {

    // Materials from http://devernay.free.fr/cours/opengl/materials.html
    // TODO: find realistic reflection, refraction and density
    public static Material EMERALD = new Material(new Colour(0, 0, 0),
            new Colour(0.07568, 0.61424, 0.07568),
            new Colour(0.633, 0.727811, 0.633), 76.8,
            new Colour(0.0215, 0.1745, 0.0215),
            0.15, 0.4, 0.48);
    public static Material BRASS = new Material(new Colour(0, 0, 0),
            new Colour(0.780392, 0.568627, 0.113725),
            new Colour(0.992157, 0.941176, 0.807843), 27.8974,
            new Colour(0.329412, 0.223529, 0.027451),
            0.15, 0, 0);
    public static Material JADE = new Material(new Colour(0, 0, 0),
            new Colour(0.54, 0.89, 0.63),
            new Colour(0.316228, 0.316228, 0.316228), 12.8,
            new Colour(0.135, 0.2225, 0.1575),
            0.05, 0, 0);
    public static Material OBSIDIAN = new Material(new Colour(0, 0, 0),
            new Colour(0.18275, 0.17, 0.22525),
            new Colour(0.332741, 0.328634, 0.346435), 38.4,
            new Colour(0.05375, 0.05, 0.06625),
            0.01, 0, 0);
    public static Material PEARL = new Material(new Colour(0, 0, 0),
            new Colour(1, 0.829, 0.829),
            new Colour(0.296648, 0.296648, 0.296648), 11.264,
            new Colour(0.25, 0.20725, 0.20725),
            0.05, 0, 0);
    public static Material RUBY = new Material(new Colour(0, 0, 0),
            new Colour(0.61424, 0.04136, 0.04136),
            new Colour(0.727811, 0.626959, 0.626959), 76.8,
            new Colour(0.1745, 0.01175, 0.01175),
            0.2, 0.35, 0.52);
    public static Material TURQUOISE = new Material(new Colour(0, 0, 0),
            new Colour(0.396, 0.74151, 0.69102),
            new Colour(0.297254, 0.30829, 0.306678), 12.8,
            new Colour(0.1, 0.18725, 0.1745),
            0, 0, 0);
    public static Material BRONZE = new Material(new Colour(0, 0, 0),
            new Colour(0.714, 0.4284, 0.18144),
            new Colour(0.393548, 0.271906, 0.166721), 25.6,
            new Colour(0.2125, 0.1275, 0.054),
            0.1, 0, 0);
    public static Material CHROME = new Material(new Colour(0, 0, 0),
            new Colour(0.4, 0.4, 0.4),
            new Colour(0.774597, 0.774597, 0.774597), 76.8,
            new Colour(0.25, 0.25, 0.25),
            0.2, 0, 0);
    public static Material COPPER = new Material(new Colour(0, 0, 0),
            new Colour(0.7038, 0.27048, 0.0828),
            new Colour(0.256777, 0.137622, 0.086014), 12.8,
            new Colour(0.19125, 0.0735, 0.0225),
            0.05, 0, 0);
    public static Material GOLD = new Material(new Colour(0, 0, 0),
            new Colour(0.75164, 0.60648, 0.22648),
            new Colour(0.628281, 0.555802, 0.366065), 51.2,
            new Colour(0.24725, 0.1995, 0.0745),
            0.15, 0, 0);
    public static Material SILVER = new Material(new Colour(0, 0, 0),
            new Colour(0.50754, 0.50754, 0.50754),
            new Colour(0.508273, 0.508273, 0.508273), 51.2,
            new Colour(0.19225, 0.19225, 0.19225),
            0.13, 0, 0);
    public static Material BLACKPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.01, 0.01, 0.01),
            new Colour(0.50, 0.50, 0.50), 32,
            new Colour(0.0, 0.0, 0.0),
            0, 0, 0);
    public static Material CYANPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.0, 0.50980392, 0.50980392),
            new Colour(0.50196078, 0.50196078, 0.50196078), 32,
            new Colour(0.0, 0.1, 0.06),
            0, 0, 0);
    public static Material GREENPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.1, 0.35, 0.1),
            new Colour(0.1, 0.45, 0.55), 32,
            new Colour(0.0, 0.0, 0.0),
            0, 0, 0);
    public static Material BLUEPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.1, 0.1, 0.35),
            new Colour(0.6, 0.6, 0.7), 32,
            new Colour(0.0, 0.0, 0.0),
            0, 0, 0);
    public static Material REDPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.5, 0.0, 0.0),
            new Colour(0.7, 0.6, 0.6), 32,
            new Colour(0.0, 0.0, 0.0),
            0, 0, 0);
    public static Material WHITEPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.55, 0.55, 0.55),
            new Colour(0.70, 0.70, 0.70), 32,
            new Colour(0.0, 0.0, 0.0),
            0, 0, 0);
    public static Material YELLOWPLASTIC = new Material(new Colour(0, 0, 0),
            new Colour(0.5, 0.5, 0.0),
            new Colour(0.60, 0.60, 0.50), 32,
            new Colour(0.0, 0.0, 0.0),
            0, 0, 0);
    public static Material BLACKRUBBER = new Material(new Colour(0, 0, 0),
            new Colour(0.01, 0.01, 0.01),
            new Colour(0.40, 0.40, 0.40), 10,
            new Colour(0.02, 0.02, 0.02),
            0, 0, 0);
    public static Material CYANRUBBER = new Material(new Colour(0, 0, 0),
            new Colour(0.4, 0.5, 0.5),
            new Colour(0.04, 0.7, 0.7), 10,
            new Colour(0.0, 0.05, 0.05),
            0, 0, 0);
    public static Material GREENRUBBER = new Material(new Colour(0, 0, 0),
            new Colour(0.4, 0.5, 0.4),
            new Colour(0.04, 0.7, 0.04), 10,
            new Colour(0.0, 0.05, 0.0),
            0, 0, 0);
    public static Material REDRUBBER = new Material(new Colour(0, 0, 0),
            new Colour(0.5, 0.4, 0.4),
            new Colour(0.7, 0.04, 0.04), 10,
            new Colour(0.05, 0.0, 0.0),
            0, 0, 0);
    public static Material WHITERUBBER = new Material(new Colour(0, 0, 0),
            new Colour(0.5, 0.5, 0.5),
            new Colour(0.7, 0.7, 0.7), 10,
            new Colour(0.05, 0.05, 0.05),
            0, 0, 0);
    public static Material YELLOWRUBBER = new Material(new Colour(0, 0, 0),
            new Colour(0.5, 0.5, 0.4),
            new Colour(0.7, 0.7, 0.04), 10,
            new Colour(0.05, 0.05, 0.0),
            0, 0, 0);
    public static Material MIRROR = new Material(new Colour(0, 0, 0),
            new Colour(0, 0, 0),
            new Colour(0, 0, 0), 0,
            new Colour(0, 0, 0),
            0.9, 0, 0);
    public static Material GLASS = new Material(new Colour(0, 0, 0),
            new Colour(0, 0, 0),
            new Colour(0, 0, 0), 100,
            new Colour(0, 0, 0),
            0.05, 0.9, 0.55);
    public static Material WATER = new Material(new Colour(0, 0, 0),
            new Colour(0.05, 0.1, 0.4),
            new Colour(0.08, 0.05, 0.05), 100,
            new Colour(0.01, 0.01, 0.01),
            0.025, 0.9, 0.75);
    public static Material GREENAXIS = new Material(new Colour(0, 1, 0),
            new Colour(0, 0, 0),
            new Colour(0, 0, 0), 0,
            new Colour(0, 0, 0),
            0, 0, 0);
    public static Material BLUEAXIS = new Material(new Colour(0, 0, 1),
            new Colour(0, 0, 0),
            new Colour(0, 0, 0), 0,
            new Colour(0, 0, 0),
            0, 0, 0);
    public static Material REDAXIS = new Material(new Colour(1, 0, 0),
            new Colour(0, 0, 0),
            new Colour(0, 0, 0), 0,
            new Colour(0, 0, 0),
            0, 0, 0);
    public static Material WOOD = new Material(new Colour(0,0,0),
            new Colour(0.365234375, 0.251953125, 0.126953125),
            new Colour(0.1, 0.1, 0.1), 200,
            new Colour(0.187, 0.129, 0.065),
            0,0,0,
            new WoodGrain(0.8,0.2,0.015, 0.2, 10, 8));

}
