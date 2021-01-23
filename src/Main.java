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

        //World waterWorld = Worlds.createWaterBox();

        World booleanWorld = Worlds.createBooleanWorld();

        Renderer renderer = new Renderer();
        renderer.render(booleanWorld);
    }
}