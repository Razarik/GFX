package Textures;

public class WoodGrain extends Texture {

    private double lowIntensity;
    private double extraIntensity;
    private double nerveThickness;
    private double wobbleIntensity;
    private double numberOfWobbles;
    private int nerveToWoodRatio;

    public WoodGrain(double lowIntensity, double extraIntensity, double nerveThickness, double wobbleIntensity, double numberOfWobbles, int nerveToWoodRatio) {
        this.lowIntensity = lowIntensity;
        this.extraIntensity = extraIntensity;
        this.nerveThickness = nerveThickness;
        this.wobbleIntensity = wobbleIntensity;
        this.numberOfWobbles = numberOfWobbles;
        this.nerveToWoodRatio = nerveToWoodRatio;
        worldTexture = false;
    }

    public WoodGrain(double lowIntensity, double extraIntensity, double nerveThickness, double wobbleIntensity, double numberOfWobbles, int nerveToWoodRatio, boolean worldTexture) {
        this.lowIntensity = lowIntensity;
        this.extraIntensity = extraIntensity;
        this.nerveThickness = nerveThickness;
        this.wobbleIntensity = wobbleIntensity;
        this.numberOfWobbles = numberOfWobbles;
        this.nerveToWoodRatio = nerveToWoodRatio;
        this.worldTexture = worldTexture;
    }

    @Override
    public double texture(double x, double y, double z) {
        double r = Math.sqrt(x * x + y * y);
        double N = 1 / numberOfWobbles;
        double theta = Math.atan2(y, x);
        double B = 10;
        double rings = (int) ((r / nerveThickness) + wobbleIntensity * Math.sin((theta / N) + B * z)) % nerveToWoodRatio;
        rings = (rings != 0) ? 1 : 0;
        return lowIntensity + extraIntensity * rings;
    }
}
