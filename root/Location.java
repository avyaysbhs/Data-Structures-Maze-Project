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
}