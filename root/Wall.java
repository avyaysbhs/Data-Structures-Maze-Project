import java.awt.*;

public class Wall
{
    public final static int size = 48;
    private int r, c;

    public Wall(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public Rectangle getRect()
    {
        return createRect(r, c);
    }

    public static final Rectangle createRect(int r, int c)
    {
        return new Rectangle((c - 0) * size, (r - 0) * size, size, size);
    }
}