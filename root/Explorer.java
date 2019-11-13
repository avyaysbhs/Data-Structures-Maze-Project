import java.awt.Rectangle;

public class Explorer
{
    private int r, c, d;

    public Explorer(int r, int c)
    {
        this.r = r;
        this.c = c;
        this.d = 1;
    }

    public Explorer(int r, int c, int d)
    {
        this(r, c);
        if (d != -1)
            this.d = d;
    }

    public void Turn(int delta)
    {
        d+=delta;
        d = d > 4 ? 1 : (d < 1 ? 4 : d);

        System.out.println(d);
    }

    public Location Move(int delta)
    {
        int r = this.r;
        int c = this.c;
        switch (d)
        {
            case 1:
            {
                r-=delta;
                break;
            }
            case 2:
            {
                c+=delta;
                break;
            }
            case 3:
            {
                r+=delta;
                break;
            }
            case 4:
            {
                c-=delta;
                break;
            }
        }
        return new Location(r, c);
    }

    public void confirm(Location loc)
    {
        this.r = loc.r();
        this.c = loc.c();
    }

    public boolean test(Location forward, Wall[][] walls)
    {
        int r = forward.r();
        int c = forward.c();
        return (r < 15 && c < 15 && c >= 0 && r >= 0 && walls[r][c] == null);
    }

    public Location loc()
    {
        return new Location(r, c);
    }

    public int d()
    {
        return d;
    }

    public Rectangle getRect()
    {
        return new Rectangle((c - 0) * Wall.size, (r - 0) * Wall.size, Wall.size, Wall.size);
    }
}