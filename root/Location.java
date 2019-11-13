public class Location
{
    private int r, c;

    public Location(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getRow()
    {
        return r;
    }

    public int getColumn()
    {
        return c;
    }

    public int r()
    {
        return getRow();
    }

    public int c()
    {
        return getColumn();
    }

    public boolean equals(Location other)
    {
        return r == other.r() && c == other.c();
    }

    private static int[] directional_motion_r = {0, -1, 0, 1, 0};
    private static int[] directional_motion_c = {0, 0, 1, 0, -1};

    public Location moveIn(int direction)
    {
        return new Location(r + directional_motion_r[direction], c + directional_motion_c[direction]);
    }

    public Location moveIn(int direction, int magnitude)
    {
        return new Location(r + directional_motion_r[direction] * magnitude, c + directional_motion_c[direction] * magnitude);
    }

    public boolean isInRange(int minR, int minC, int maxR, int maxC)
    {
        return r >= minR && r <= maxR && c >= minC && c <= maxC;
    }

    public String toString()
    {
        return "(" + c + ", " + r + ")";
    }
}