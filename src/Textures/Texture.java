package Textures;

public abstract class Texture {

    protected boolean worldTexture;

    public abstract double texture(double x, double y, double z);

    public boolean isWorldTexture() {
        return worldTexture;
    }
}
