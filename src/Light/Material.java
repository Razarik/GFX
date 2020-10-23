package Light;

public class Material {
    private Colour emissive;
    private Colour diffuse;
    private Colour specular;
    private double specularExponent;
    private Colour ambient;

    public Material(Colour emissive, Colour diffuse, Colour specular, double specularExponent, Colour ambient) {
        this.emissive = emissive;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularExponent = specularExponent;
        this.ambient = ambient;
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
}
