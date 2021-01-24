package Light;

import Textures.NoTexture;
import Textures.Texture;

public class Material {
    private Colour emissive;
    private Colour diffuse;
    private Colour specular;
    private double specularExponent;
    private Colour ambient;
    private double shininess;
    private double transparency;
    private double relativeLightSpeed;
    private Texture texture;

    public Material(Colour emissive, Colour diffuse, Colour specular, double specularExponent, Colour ambient, double shininess, double transparency, double relativeLightSpeed) {
        this.emissive = emissive;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularExponent = specularExponent;
        this.ambient = ambient;
        this.shininess = shininess;
        this.transparency = transparency;
        this.relativeLightSpeed = relativeLightSpeed;
        this.texture = new NoTexture();
    }

    public Material(Colour emissive, Colour diffuse, Colour specular, double specularExponent, Colour ambient, double shininess, double transparency, double relativeLightSpeed, Texture texture) {
        this.emissive = emissive;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularExponent = specularExponent;
        this.ambient = ambient;
        this.shininess = shininess;
        this.transparency = transparency;
        this.relativeLightSpeed = relativeLightSpeed;
        this.texture = texture;
    }

    public Colour getEmissive() {
        return emissive;
    }

    public void setEmissive(Colour emissive) {
        this.emissive = emissive;
    }

    public Colour getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Colour diffuse) {
        this.diffuse = diffuse;
    }

    public Colour getSpecular() {
        return specular;
    }

    public void setSpecular(Colour specular) {
        this.specular = specular;
    }

    public double getSpecularExponent() {
        return specularExponent;
    }

    public void setSpecularExponent(double specularExponent) {
        this.specularExponent = specularExponent;
    }

    public Colour getAmbient() {
        return ambient;
    }

    public void setAmbient(Colour ambient) {
        this.ambient = ambient;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public double getRelativeLightSpeed() {
        return relativeLightSpeed;
    }

    public void setRelativeLightSpeed(double relativeLightSpeed) {
        this.relativeLightSpeed = relativeLightSpeed;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
