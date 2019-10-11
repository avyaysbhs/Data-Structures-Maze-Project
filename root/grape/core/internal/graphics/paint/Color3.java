package grape.core.internal.graphics.paint;

import grape.core.internal.math.MathOps;

public final class Color3
{
    public double r;
    public double g;
    public double b;

    public Color3(double r, double g, double b)
    {
        set(r,g,b);
    }

    public int getRed()
    {
        return MathOps.clamp((int) (r * 255), 0, 255);
    }

    public int getGreen()
    {
        return MathOps.clamp((int) (g * 255), 0, 255);
    }

    public int getBlue()
    {
        return MathOps.clamp((int) (b * 255), 0, 255);
    }

    public Color3 lerp(Color3 target, float time)
    {
        return lerp(this, target, time);
    }

    public void set(double r, double g, double b)
    {
        this.r = r; this.g = g; this.b = b;
    }

    public void setRed(double r)
    {
        this.r = r;
    }

    public void setGreen(double g)
    {
        this.g = g;
    }

    public void setBlue(double b)
    {
        this.b = b;
    }

    public void set(int r, int g, int b)
    {
        set(
            ((double) r)/255,
            ((double) g)/255,
            ((double) b)/255
        );
    }

    public static Color3 lerp(Color3 s, Color3 e, double t)
    {
        return new Color3(
            MathOps.lerp(s.r, e.r, t),
            MathOps.lerp(s.g, e.g, t),
            MathOps.lerp(s.b, e.b, t)
        );
    }

    public static java.awt.Color toAWT(Color3 input)
    {
        java.awt.Color out = new java.awt.Color(input.getRed(), input.getGreen(), input.getBlue());
        return out;
    }

    public String toString()
    {
        return "Color3(" + r + ", " + g + ", " + b + ")";
    }
}