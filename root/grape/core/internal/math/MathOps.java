package grape.core.internal.math;

public final class MathOps
{
    public static int clamp(int n, int min, int max)
    {
        return n > max ? max : ( n < min ? min : n );
    }

    public static double clamp(double n, double min, double max)
    {
        return n > max ? max : ( n < min ? min : n );
    }

    public static float clamp(float n, float min, float max)
    {
        return n > max ? max : ( n < min ? min : n );
    }

    public static double lerp(double s, double e, double t)
    {
        t = clamp(t, 0, 1);
        return s+((e-s)*t);
    }

    public static float lerp(float s, float e, float t)
    {
        t = clamp(t, 0, 1);
        return s+((e-s)*t);
    }
}