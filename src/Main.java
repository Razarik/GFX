import Drawing.Renderer;
import Drawing.World;
import Statics.Worlds;

public class Main {
    public static void main(String[] args) {
        World boxedWorld = Worlds.createBoxedWorld();

        World waterWorld = Worlds.createWaterBox();

        World booleanWorld = Worlds.createBooleanWorld();

        World glassWorld = Worlds.createGlassCupWorld();

        World reflectionRefraction = Worlds.reflectionRefractionWorld();

        World coverWorld = Worlds.createCoverWorld();

        World textureWorld = Worlds.createTextureWorld();

        World lensWorld = Worlds.createLensWorld();

        World fullWorld = Worlds.createFullWorld();

        World gemWorld = Worlds.createGemWorld();

        World lightWorld = Worlds.createColoredLightWorld();

        Renderer renderer = new Renderer();
        renderer.render(lightWorld);
    }
}