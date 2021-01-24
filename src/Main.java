import Calculations.*;
import Drawing.Renderer;
import Drawing.World;
import Statics.Worlds;

public class Main {
    public static void main(String[] args) {
        // Make transformation factory
        TransformationFactory tf = new TransformationFactory();

        // World boxedWorld = Worlds.createBoxedWorld();
        //boxedWorld.addAxes(15);

        World waterWorld = Worlds.createWaterBox();

        //World booleanWorld = Worlds.createBooleanWorld();

        World glassWorld = Worlds.createGlassCup();

        World reflectionRefraction = Worlds.reflectionRefraction();

        World coverWorld = Worlds.createCoverWorld();

        World textureWorld = Worlds.createTextureWorld();

        World lensWorld = Worlds.createLensWorld();

        Renderer renderer = new Renderer();
        renderer.render(lensWorld);
    }
}