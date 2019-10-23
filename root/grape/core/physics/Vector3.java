package grape.core.physics;

public final class Vector3
{
    public double x;
    public double y;
    public double z;

    private final static Vector3 Right = new Vector3(1, 0, 0);
    private final static Vector3 Up = new Vector3(0, 1, 0);
    private final static Vector3 Backward = new Vector3(0, 0, 1);

    public static enum Axis
    {
        X, Y, Z
    }

    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float[] point)
    {
        if (point.length != 3) { x = 0; y = 0; z = 0; return; }
        x = point[0];
        y = point[1];
        z = point[2];
    }

    public Vector3(Vector2 v)
    {
        this.x = v.x;
        this.y = v.y;
        this.z = 0;
    }

    public Vector3()
    {
        this(0, 0, 0);
    }

    public Vector3 add(Vector3 v)
    {
        return add(this, v);
    }

    public Vector3 subtract(Vector3 v)
    {
        return subtract(this, v);
    }

    public Vector3 scale(Vector2 v)
    {
        return scale(this, v);
    }

    public Vector3 scale(double magnitude)
    {
        return scale(this, magnitude);
    }

    public Vector3 scale(Vector3 v)
    {
        return scale(this, v);
    }

    public static Vector3 add(Vector3 a, Vector3 b)
    {
        return new Vector3(
            a.x + b.x,
            a.y + b.y,
            a.z + b.z
        );
    }

    public static Vector3 subtract(Vector3 a, Vector3 b)
    {
        //return add(a, scale(b, -1));
        return new Vector3(
            a.x - b.x,
            a.y - b.y,
            a.z - b.z
        );
    }

    public static Vector3 add(Vector3 a, double dx, double dy, double dz)
    {
        return new Vector3(
            a.x + dx,
            a.y + dy,
            a.z + dz
        );
    }

    public static Vector3 subtract(Vector3 a, double dx, double dy, double dz)
    {
        return new Vector3(
            a.x - dx,
            a.y - dy,
            a.z - dz
        );
    }

    public static Vector3 scale(Vector3 v, double scl)
    {
        return new Vector3(v.x * scl, v.y * scl, v.z * scl);
    }

    public static Vector3 scale(Vector3 a, Vector3 b)
    {
        return new Vector3(
            a.x * b.x,
            a.y * b.y,
            a.z * b.z
        );
    }

    public static Vector3 scale(Vector3 a, Vector2 b)
    {
        return new Vector3(
            a.x * b.x,
            a.y * b.y,
            a.z
        );
    }

    public static Vector3 from(Vector2 v)
    {
        return new Vector3(v.x, v.y, 0);
    }

    public static Vector3 from(Vector3.Axis axis)
    {
        switch (axis)
        {
            case X:
            {
                return Right;
            }
            case Y:
            {
                return Up;
            }
            case Z:
            {
                return Backward;
            }
            default:
            {
                return new Vector3();
            }
        }
    }

    public static float[][] toMatrix(Vector3 v)
    {
        return new float[][]
        {
            {(float) v.x},
            {(float) v.y},
            {(float) v.z}
        };
    }

    public String toString()
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}