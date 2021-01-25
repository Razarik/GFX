package Drawing;

import Calculations.*;
import Light.Colour;
import Light.LightSource;
import Objects.Object;
import Objects.Water;
import Statics.Globals;

import java.util.ArrayList;

public class Renderer {
    private WindowFrame windowFrame;

    public Renderer() {
        this.windowFrame = new WindowFrame((Globals.WIDTH), Globals.HEIGHT);
    }

    private static Colour getShade(Ray ray, int depth, World world) {
        Colour colour = new Colour(0, 0, 0);
        Intersection bestIntersection = world.bestIntersection(ray);
        if (bestIntersection != null) {
            Point hitPoint = bestIntersection.getHitPoint();
            Vector view = world.getCamera().getEye().subtract(hitPoint).normalise();
            Object object = bestIntersection.getHitObject();
            Vector normal = bestIntersection.getHitNormal().normalise();
            colour = object.getMaterial().getEmissive();
            if (ray.getDirection().multiplyElement(-1).dotProduct(normal) < 0 && object.getClass() != Water.class) {
                normal = normal.multiplyElement(-1);
            }

            // Check if texture is using world coordinates or generic coordinates
            Point texturePoint = hitPoint;
            if (!object.getMaterial().getTexture().isWorldTexture()) {
                texturePoint = object.getInverseTransformation().multiply(hitPoint);
            }

            // Ambient colour
            colour = colour.add(world.getAmbient().multiply(object.getMaterial().getAmbient()).multiply(object.getMaterial().getTexture().texture(texturePoint.getX(), texturePoint.getY(), texturePoint.getZ())));

            for (LightSource lightSource : world.getLightSources()) {
                //Shadow
                Ray shadowRay = new Ray(hitPoint, lightSource.getPoint());
                ArrayList<Intersection> shadows = world.getHits(shadowRay);
                double shadeFactor = 0;
                for (Intersection shadowIntersection : shadows) {
                    if (shadowIntersection.getHitTime() > Globals.ERROR && shadowIntersection.getHitTime() < 1) {
                        if (shadowIntersection.isEntering()) {
                            shadeFactor = shadeFactor + (1 - shadowIntersection.getHitObject().getMaterial().getTransparency());
                        }
                    }
                }
                if (shadeFactor > 1) {
                    shadeFactor = 1;
                }
                // Diffuse colour
                Vector s = lightSource.getPoint().subtract(hitPoint).normalise();
                double mDotS = s.dotProduct(normal);
                if (mDotS > Globals.ERROR) {
                    Colour diffuseColour = object.getMaterial().getDiffuse().multiply(mDotS).multiply(lightSource.getColour()).multiply(1 - shadeFactor).multiply(object.getMaterial().getTexture().texture(texturePoint.getX(), texturePoint.getY(), texturePoint.getZ()));
                    colour = colour.add(diffuseColour);
                }

                // Specular colour
                Vector h = view.add(s).normalise();
                double mDotH = h.dotProduct(normal);
                if (mDotH > Globals.ERROR) {
                    double phong = Math.pow(mDotH, object.getMaterial().getSpecularExponent());
                    Colour specularColour = object.getMaterial().getSpecular().multiply(phong).multiply(lightSource.getColour()).multiply(1 - shadeFactor);
                    colour = colour.add(specularColour);
                }
            }

            if (depth <= Globals.DEPTH) {

                // Refraction logic
                boolean toReflect = false;
                if (object.getMaterial().getTransparency() >= Globals.ERROR) { // If object is transparent enough
                    Vector dir = ray.getDirection().multiplyElement(-1).normalise();
                    double c1, c2;
                    Object inside = ray.getHighestPriority();
                    if (bestIntersection.isEntering()) {
                        if (inside == null) {
                            c1 = 1;
                            c2 = object.getMaterial().getRelativeLightSpeed();
                        } else {
                            c1 = inside.getMaterial().getRelativeLightSpeed();
                            if (object.getPriority() > inside.getPriority()) {
                                c2 = object.getMaterial().getRelativeLightSpeed();
                            } else {
                                c2 = c1;
                            }
                        }
                        ray.addInside(object);
                    } else {
                        if (inside == null) {
                            c1 = 1;
                            c2 = 1;
                        } else {
                            c1 = inside.getMaterial().getRelativeLightSpeed();
                            ray.removeInside(inside);
                            Object second = ray.getHighestPriority();
                            if (second == null) {
                                c2 = 1;
                            } else {
                                c2 = second.getMaterial().getRelativeLightSpeed();
                            }
                        }
                    }
                    double ratio = c2 / c1;
                    double mDotDir = normal.dotProduct(dir);
                    double cosTheta2Sqr = 1 - ((ratio * ratio) * (1 - (mDotDir * mDotDir)));
                    if (cosTheta2Sqr <= Globals.ERROR) {
                        toReflect = true;
                        if (bestIntersection.isEntering()) {
                            ray.removeInside(object);
                        } else {
                            ray.addInside(inside);
                        }
                    } else {
                        double cosTheta2 = Math.sqrt(cosTheta2Sqr);
                        double mFactor = (ratio * mDotDir) - cosTheta2;
                        Vector dirMultiplied = dir.multiplyElement(ratio);
                        Vector mMultiplied = normal.multiplyElement(mFactor);
                        Vector t = mMultiplied.add(dirMultiplied.multiplyElement(-1));
                        Ray refraction = new Ray(hitPoint, t);
                        refraction.setInside(ray.getInside());
                        colour = colour.add(getShade(refraction, depth + 1, world).multiply(object.getMaterial().getTransparency()));
                    }
                }

                // Reflection logic
                if (object.getMaterial().getShininess() >= Globals.ERROR || toReflect) {  // If object is shiny enough to reflect, or total internal reflection
                    Ray reflection = new Ray(hitPoint, ray.getDirection().add(normal.multiplyElement(ray.getDirection().dotProduct(normal) * -2)));
                    reflection.setInside(ray.getInside());
                    Colour reflectionColour = getShade(reflection, depth + 1, world);
                    if (toReflect) {
                        colour = colour.add(reflectionColour.multiply(object.getMaterial().getTransparency()));
                    }
                    colour = colour.add(reflectionColour.multiply(object.getMaterial().getShininess()));
                }
            }
        }
        return colour;
    }

    public void render(World world) {
        for (int column = 0; column < Globals.WIDTH; column++) {
            for (int row = 0; row < Globals.HEIGHT; row++) {
                Ray ray = new Ray(world.getCamera(), Globals.WIDTH, Globals.HEIGHT, row, column);
                windowFrame.drawPoint(column, row, getShade(ray, 0, world).getColor());
            }
        }
        windowFrame.setVisible(true);
    }
}
