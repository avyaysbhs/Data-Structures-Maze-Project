package grape.core.shapes;

import grape.core.physics.Vector2;

public class Rectangle2D extends Shape2D
{
    public Rectangle2D(
        Vector2 center,
        Vector2 size
    )
    {
        super(create(center, size));
    }

    public static Vector2[] create(Vector2 center, Vector2 size)
    {
        Vector2[] points = new Vector2[4];

        points[0] = center.add(size.scale(new Vector2(-.5, .5)));
        points[1] = center.add(size.scale(new Vector2(.5, .5)));
        points[2] = center.add(size.scale(new Vector2(-.5, -.5)));
        points[3] = center.add(size.scale(new Vector2(.5, -.5)));
        
        return points;
    }
}