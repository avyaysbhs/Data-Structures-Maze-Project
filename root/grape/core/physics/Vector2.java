package grape.core.physics;

public final class Vector2
{
    public double x;
    public double y;

    private final static Vector2 Right = new Vector2(1, 0);
    private final static Vector2 Up = new Vector2(0, 1);

    public Vector2(double x, double y)
    {
        this.x = x; this.y = y;
    }

    public Vector2(Vector3 in)
    {
        x = in.x;
        y = in.y;
    }

    public Vector2()
    {
        this(0, 0);
    }

    public Vector2 add(Vector2 v)
    {
        return add(this, v);
    }

    public Vector2 add(double dx, double dy)
    {
        return add(this, dx, dy);
    }

    public Vector2 subtract(Vector2 v)
    {
        return subtract(this, v);
    }

    public Vector2 subtract(double dx, double dy)
    {
        return subtract(this, dx, dy);
    }

    public Vector2 scale(double magnitude)
    {
        return scale(this, magnitude);
    }

    public Vector2 scale(Vector2 b)
    {
        return scale(this, b);
    }

    public static Vector2 add(Vector2 a, Vector2 b)
    {
        return new Vector2(
            a.x + b.x,
            a.y + b.y
        );
    }

    public static Vector2 add(Vector2 a, double dx, double dy)
    {
        return new Vector2(
            a.x + dx,
            a.y + dy
        );
    }

    public static Vector2 subtract(Vector2 a, Vector2 b)
    {
        return new Vector2(
            a.x - b.x,
            a.y - b.y
        );
    }

    public static Vector2 subtract(Vector2 v, double dx, double dy)
    {
        return new Vector2(v.x - dx, v.y - dy);
    }

    public static Vector2 scale(Vector2 a, double magnitude)
    {
        return new Vector2(a.x * magnitude, a.y * magnitude);
    }

    public static Vector2 scale(Vector2 a, Vector2 b)
    {
        return new Vector2(
            a.x * b.x,
            a.y * b.y
        );
    }

    public static Vector2 scale(Vector2 a, Vector3 b)
    {
        return scale(a, from(b));
    }

    public static Vector2 fromCoordinate(Coordinate c)
    {
        return new Vector2(c.X(), c.Y());
    };

    public static Vector2 from(Vector3 v)
    {
        return new Vector2(v.x, v.y);
    }

    public static Vector2 from(Vector3.Axis v)
    {
        switch (v)
        {
            case X:
            {
                return Right;
            }
            case Y:
            {
                return Up;
            }
            default:
            {
                return new Vector2();
            }
        }
    }

    public static float[][] toMatrix(Vector2 v)
    {
        return new float[][]
        {
            {(float) v.x},
            {(float) v.y}
        };
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}