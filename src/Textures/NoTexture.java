package Textures;


public class NoTexture extends Texture {

    public NoTexture() {
        worldTexture = false;
    }

    @Override
    public double texture(double x, double y, double z) {
        return 1;
    }
}
